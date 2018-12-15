package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Course;
import com.northeastern.pkotak.webdev.models.Role;
import com.northeastern.pkotak.webdev.models.RoleClone;
import com.northeastern.pkotak.webdev.models.User;
import com.northeastern.pkotak.webdev.repositories.CourseRepository;
import com.northeastern.pkotak.webdev.repositories.RoleRepository;
import com.northeastern.pkotak.webdev.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/user/roles")
    public List<RoleClone> findUserRoles(){
        List<RoleClone> userRoles = new ArrayList<>();
        List<User> userList = (List<User>) userRepository.findAll();
        for(User user : userList){
            for(Role role : user.getRole()){
                RoleClone rc = new RoleClone();
                rc.setId(role.getId());
                rc.setCourseId(role.getCourse().getId());
                rc.setUserId(role.getUser().getId());
                rc.setRoleType(role.getRoleType());
                userRoles.add(rc);
            }
        }
        return userRoles;
    }


    @PostMapping("/api/course/{cid}/role")
    public Role createRoleForCourse(@RequestBody Role role,
                                    HttpSession session,
                                    @PathVariable("cid") int courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        role.setCourse(course.get());
        User user = (User) session.getAttribute("user");
        if(user != null) {
            role.setUser((User) session.getAttribute("user"));
            return roleRepository.save(role);
        }

        return null;
    }
}
