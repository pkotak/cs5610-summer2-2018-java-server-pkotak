package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.User;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class UserService {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session){
        Optional<User> optionalUser = userRepository.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        if (optionalUser.isPresent()){
            session.setAttribute("user", optionalUser.get());
            return optionalUser.get();
        }

        return null;
    }
    /**
     * Method to find all the users
     * @return List of Users
     */
    @GetMapping("/api/user")
    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    /**
     * Method to find a user by Id
     * @param id user Id
     * @return A user object
     */
    @GetMapping("/api/user/{userId}")
    public Optional<User> findUserById(@PathVariable("userId") int id){
        return userRepository.findById(id);
    }

    /**
     * Updates an existing user
     * @param id User id of user to be updated
     * @param newuser new User object containing updated values
     * @return updated user object
     */
    @PutMapping("/api/user/{userId}")
    public User updateuser(@PathVariable("userId") int id, @RequestBody User newuser) {
        Optional<User> optionaluser = userRepository.findById(id);
        if (optionaluser.isPresent()) {
            User user = optionaluser.get();
            user.set(newuser);
            return userRepository.save(user);
        } else
            return null;
    }

    /**
     * Deletes a User
     * @param id User Id of user to be deleted
     */
    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userRepository.deleteById(id);
    }
}
