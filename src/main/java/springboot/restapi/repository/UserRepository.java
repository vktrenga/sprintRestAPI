package springboot.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.restapi.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findAllById(Long id);
    User findByEmail(String email);

}