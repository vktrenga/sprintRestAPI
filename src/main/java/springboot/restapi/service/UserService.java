package springboot.restapi.service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.restapi.config.ConstantVariables;
import springboot.restapi.model.User;
import springboot.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import java.util.*;

import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@Transactional

public class UserService implements  UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    public org.springframework.security.core.userdetails.User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> getUser(Long id) {
        Optional<User> user= userRepository.findById(id);
        return user;
    }

    public User saveUser(User userDetails) {
        User user = userRepository.save(userDetails);
        return user;
    }

    public User updateUser(Long id,User userDetails) {
        User user = userRepository.findById(id).get();
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setDob(userDetails.getDob());
        user.setMaritalStatus(userDetails.getMaritalStatus());
        final User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public Map<String, Boolean> deleteUser(Long id) {
        Optional<User> user = getUser(id);
        Map<String, Boolean> response = new HashMap<>();
        if (user!= null) {
            userRepository.delete(user.get());
            response.put(ConstantVariables.DELETE_KEY, Boolean.TRUE);
        } else {
            response.put(ConstantVariables.DELETE_KEY, Boolean.FALSE);
        }
        return response;
    }




}
