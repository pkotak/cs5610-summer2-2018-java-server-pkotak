package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Method to find user by username
     * @param username
     * @return User
     */
    @Query("SELECT u FROM User u WHERE u.username=:username")
    Optional<User> findUserByUsername(@Param("username") String username);

    /**
     * Method to find user by credentials
     * @param username
     * @param password
     * @return Person
     */
    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    Optional<User> findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
