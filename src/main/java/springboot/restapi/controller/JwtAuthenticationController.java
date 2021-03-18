package springboot.restapi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.restapi.config.JwtTokenUtil;
import springboot.restapi.model.JwtRequest;
import springboot.restapi.model.JwtResponse;
import springboot.restapi.repository.UserRepository;
import springboot.restapi.service.UserService;
import springboot.restapi.config.ConstantVariables;
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private static final long OTP_VALID_DURATION = 5 * 60 * 1000;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = ConstantVariables.AUTHENTICATE, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		springboot.restapi.model.User user = userRepository.findByEmail(authenticationRequest.getEmail());
		Map<String, String> response = new HashMap<>();
		if (user != null) {
			if (isOtpValid(user) ==true ) {
				authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

				final User userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());

				final String token = jwtTokenUtil.generateToken(userDetails);

				return ResponseEntity.ok(new JwtResponse(token));
			} else {
				response.put(ConstantVariables.MESSAGE_KEY, ConstantVariables.OTP_EXPIRED );
				return new ResponseEntity<Map<String, String>>( response, HttpStatus.UNAUTHORIZED);
			}
		} else {
			response.put(ConstantVariables.MESSAGE_KEY, ConstantVariables.INVALID_EMAIL);
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.UNAUTHORIZED);
		}

	}

	public boolean isOtpValid(springboot.restapi.model.User user ) {
		long currentTimeInMillis = new Date().getTime();
		long otpEndTimeInMillis = user.getOtpSendAt().getTime();

		if (otpEndTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
			return false;
		}
		return true;
	}


	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new 	UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
