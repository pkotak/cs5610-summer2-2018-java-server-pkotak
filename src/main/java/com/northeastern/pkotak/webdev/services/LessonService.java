package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Lesson;
import com.northeastern.pkotak.webdev.models.Module;
import com.northeastern.pkotak.webdev.repositories.CourseRepository;
import com.northeastern.pkotak.webdev.repositories.LessonRepository;
import com.northeastern.pkotak.webdev.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
    public Lesson createLesson(@RequestBody Lesson lesson, @PathVariable("courseId") int courseId,
                               @PathVariable("moduleId") int moduleId) {
        Optional<Module> optionalModule = moduleRepository.findById(moduleId);
        if(optionalModule.isPresent()) {
            Module module = optionalModule.get();
            lesson.setModule(module);
            return lessonRepository.save(lesson);
        }
        return null;

    }

    @GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
    public List<Lesson> findAllModulesForCourse(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId) {
        Optional<Module> data = moduleRepository.findById(moduleId);
        if(data.isPresent()) {
            Module module = data.get();
            return module.getLessons();
        }
        return null;
    }

    @DeleteMapping("/api/lesson/{lId}")
    public void deleteModule(@PathVariable("lId") int lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
