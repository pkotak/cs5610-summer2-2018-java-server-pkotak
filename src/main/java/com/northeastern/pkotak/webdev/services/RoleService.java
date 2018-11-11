package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Course;
import com.northeastern.pkotak.webdev.models.Role;
import com.northeastern.pkotak.webdev.models.User;
import com.northeastern.pkotak.webdev.repositories.CourseRepository;
import com.northeastern.pkotak.webdev.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CourseRepository courseRepository;

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
