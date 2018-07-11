package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.northeastern.pkotak.webdev.models.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
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

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session){
        Optional<User> optionalUser = userRepository.findUserByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            session.setAttribute("user", user);
            return optionalUser.get();
        }
        else{
            session.setAttribute("user", user);
            return userRepository.save(user);
        }
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
     * @param newUser new User object containing updated values
     * @return updated user object
     */
    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int id, @RequestBody User newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.set(newUser);
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

    /**
     * Method to get the user object from an active session
     * @param session holding the user object
     * @return the user object obtained from the session
     */
    @GetMapping("/api/profile")
    public User profile(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user;
    }

    /**
     * Method to update an exisiting user details
     * @param updatedUser new user object
     * @param session session containing old user object
     * @return updated user object
     *
     **/
    @PutMapping("/api/profile")
    public User updateProfile(@RequestBody User updatedUser, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        currentUser.set(updatedUser);
        session.setAttribute("user", currentUser);
        return userRepository.save(currentUser);
    }

    @PostMapping("/api/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }
}
