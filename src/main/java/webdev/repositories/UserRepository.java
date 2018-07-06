package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Method to find user by credentials
     * @param username
     * @param password
     * @return Person
     */
    @Query("SELECT p FROM Person p WHERE p.username=:username AND p.password=:password")
    Optional<User> findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
