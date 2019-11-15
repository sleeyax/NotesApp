package be.thomasmore.userservice.repository;

import be.thomasmore.userservice.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(@Param("email") String email);
    User findUserById(@Param("id") int id);
    void deleteById(@Param("id") int id);
}