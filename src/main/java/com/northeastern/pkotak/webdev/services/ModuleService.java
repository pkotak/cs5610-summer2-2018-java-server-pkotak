package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Course;
import com.northeastern.pkotak.webdev.models.Module;
import com.northeastern.pkotak.webdev.repositories.CourseRepository;
import com.northeastern.pkotak.webdev.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Method to create a new module
     * @param module the module object to be created
     * @param courseId course id parameter
     * @return the module stored in the database
     */
    @PostMapping("/api/course/{cid}/module")
    public Module createModule(@RequestBody Module module, @PathVariable("cid") int courseId){
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            module.setCourse(course);
            return moduleRepository.save(module);
        }
        return null;
    }

    /**
     * Method to delete a module
     * @param moduleId module id
     */
    @DeleteMapping("/api/module/{id}")
    public void deleteModule(@PathVariable("id") int moduleId){
        moduleRepository.deleteById(moduleId);
    }

    /**
     * Method to find all the modules
     * @return list of all the modules
     */
    @GetMapping("/api/module")
    public List<Module> findAllModules(){
        return (List<Module>) moduleRepository.findAll();
    }

    /**
     * Method to find a specific module
     * @param id module id
     * @return the module if found by id, else null
     */
    @GetMapping("/api/module/{id}")
    public Optional<Module> findModuleById(@PathVariable("id") int id){
        return moduleRepository.findById(id);
    }

    /**
     * Method to find all the modules for a course
     * @param courseId course id
     * @return list of modules belonging to a course
     */
    @GetMapping("/api/course/{courseId}/module")
    public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId){
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return course.getModules();
        }
        return null;
    }

    /**
     * Method to update an existing module
     * @param updatedModule updated module to be stored
     * @param id module id
     * @return updated module
     */
    @PutMapping("/api/module/{id}")
    public Module updateModule(@RequestBody Module updatedModule, @PathVariable("id") int id){
        Optional<Module> optionalModule = moduleRepository.findById(id);
        if(optionalModule.isPresent()){
            Module module = optionalModule.get();
            module.set(updatedModule);
            return moduleRepository.save(module);
        }
        return null;
    }
}
