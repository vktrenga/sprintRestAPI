package springboot.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import springboot.restapi.model.User;
import springboot.restapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import springboot.restapi.exception.ResourceNotFoundException;
import springboot.restapi.service.UserService;

import java.util.*;
import springboot.restapi.config.ConstantVariables;

import javax.validation.Valid;


@RestController
@RequestMapping(ConstantVariables.API)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @GetMapping(ConstantVariables.USERS)
    public List<User> getUsers()  {
        return userService.getAllUsers();
    }

    @GetMapping(ConstantVariables.GET_USER)
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<User> user =  userService.getUser(id);
        if (user.isPresent()) return ResponseEntity.ok().body(user);
        else throw new ResourceNotFoundException("Data Not found");
    }


    @PostMapping(ConstantVariables.USERS)
    public  ResponseEntity<Optional<User>> add(@Valid @RequestBody User user) throws Exception {
        Optional<User> userSaved = Optional.ofNullable(userService.saveUser(user));
        if (userSaved.isPresent()) return ResponseEntity.ok().body(userSaved);
        else throw new ResourceNotFoundException("Data Not Valid");
    }

    @PutMapping(ConstantVariables.GET_USER)
    public ResponseEntity<User>  update(@Valid @PathVariable Long id, @RequestBody User userDetails) throws ResourceNotFoundException {
        User userUpdated  =userService.updateUser(id, userDetails);
        return ResponseEntity.ok().body(userUpdated);
    }

    @DeleteMapping(ConstantVariables.GET_USER)
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PostMapping(ConstantVariables.OTP_SEND)
    public Map<String, String> sendEmail(@RequestBody Map<String, String> json) {
        String email = json.get("email");
        //verify is
        User user = userRepository.findByEmail(email);
        Map<String, String> response = new HashMap<>();
        if (user != null) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(email);
            msg.setSubject(ConstantVariables.OTP_SUBJECT);
            Random rand = new Random();
            int otp = rand.nextInt(100000);

            //save otp at user model
            user.setPassword(bcryptEncoder.encode(String.valueOf(otp)));
            user.setOtpSendAt(new Date());
            userRepository.save(user);

            //send otp to user
            msg.setText(ConstantVariables.EMAIL_CONTENT + String.valueOf(otp));
            javaMailSender.send(msg);
            response.put(ConstantVariables.MESSAGE_KEY, ConstantVariables.OTP_SEND_MESSAGE);
            return response;
        }
        response.put(ConstantVariables.MESSAGE_KEY, ConstantVariables.INVALID_EMAIL);
        return response;
    }


}
