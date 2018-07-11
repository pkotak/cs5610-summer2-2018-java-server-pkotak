package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.repositories.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    JavaMailSender javaMailSender;

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
     * Method to check if username is available
     * @param username
     * @return true iff the username is present in the database
     */
    @GetMapping("/api/username/{uname}")
    public Boolean checkUsernamePresent(@PathVariable("uname") String username){
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        return optionalUser.isPresent();
    }

    /**
     *
     * @param user
     * @param session
     * @return
     */
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

    /**
     * Method to remove any active session variable
     * @param session session variable to be invalidated
     */
    @PostMapping("/api/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }

    /**
     * Method to send a reset link to user via email
     * @param user object containing email
     * @return true iff email was sent successfully
     */
    @PostMapping("/api/resetpassword")
    public Boolean forgotPassword(@RequestBody User user){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("team212updates@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Reset Password");
        mail.setText("Please click on http://localhost:8080/jquery/components/reset-password/reset-password.template.client.html to reset password");

        try {
            javaMailSender.send(mail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't send message");
            return false;
        }
    }
}
