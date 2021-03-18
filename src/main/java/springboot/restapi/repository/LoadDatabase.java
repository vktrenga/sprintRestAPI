package springboot.restapi.repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.restapi.model.User;
import springboot.restapi.repository.UserRepository;

import java.util.Date;

@Configuration
public class LoadDatabase {
//    private UserRepository userRepository;
    @Autowired
    private static final Logger log =  LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        if ( userRepository.count() == 0) {
            return args -> {
                log.info("Preloading " + userRepository.save(new User("Tony", "Stark", "test123@yopmail.com",  new Date(), false)));
                log.info("Preloading " + userRepository.save(new User("Mohamed", "taher", "test124@yopmail.com", new Date(), false)));
                log.info("Preloading " + userRepository.save(new User("Chris", "Evans", "test125@yopmail.com", new Date(), true)));
                log.info("Preloading " + userRepository.save(new User("Tom", "Holland", "test126@yopmail.com", new Date(), true)));
                log.info("Preloading " + userRepository.save(new User("Mark", "Ruffallo", "test127@yopmail.com", new Date(), false)));
            };
        }
        return  null;

       }

    }

