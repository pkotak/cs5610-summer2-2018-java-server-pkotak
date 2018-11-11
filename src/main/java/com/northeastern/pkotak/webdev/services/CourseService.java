package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Course;
import com.northeastern.pkotak.webdev.models.Role;
import com.northeastern.pkotak.webdev.models.User;
import com.northeastern.pkotak.webdev.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/api/course")
    public Course createCourse(@RequestBody Course course, HttpSession session){
        Role userRole = new Role();
        userRole.setCourse(course);
        userRole.setRoleType("FACULTY");
        userRole.setUser((User) session.getAttribute("user"));
        List<Role> roleList = (course.getRoles() != null) ? course.getRoles() : new ArrayList<>();
        roleList.add(userRole);
        course.setRoles(roleList);
        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{id}")
    public void deleteCourse(@PathVariable("id") int id){
        courseRepository.deleteById(id);
    }

    @GetMapping("/api/course")
    public List<Course> findAllCourses(){
        return (List<Course>) courseRepository.findAll();
    }

    @GetMapping("/api/course/{id}")
    public Course findCourseById(@PathVariable("id") int id){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return course;
        }
        return null;
    }

    @PutMapping("/api/course/{id}")
    public Course updateCourse(@RequestBody Course updatedCourse, @PathVariable("id") int id){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.setId(updatedCourse.getId());
            course.setTitle(updatedCourse.getTitle());
            course.setCreated(updatedCourse.getCreated());
            course.setModified(updatedCourse.getModified());
            return course;
        }
        return null;
    }
}
