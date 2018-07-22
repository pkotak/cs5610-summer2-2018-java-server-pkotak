package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Lesson;
import com.northeastern.pkotak.webdev.models.Module;
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

    /**
     * Method to create a new Lesson
     * @param lesson the lesson object to be inserted into the database
     * @param courseId the course Id of the module the lesson belongs to
     * @param moduleId the module id the lesson belongs to
     * @return The newly created Lesson
     */
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

    /**
     * Method to find all the existing lessons
     * @return List of all the exisiting lessons
     */
    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons(){
        return (List<Lesson>) lessonRepository.findAll();
    }

    /**
     * Method to find a lesson by Id.
     * @param id lesson ID
     * @return The lesson if found, else null
     */
    @GetMapping("/api/lesson/{id}")
    public Optional<Lesson> findLessonById(@PathVariable("id") int id){
        return lessonRepository.findById(id);
    }

    /**
     * Method to find all the lessons for a speicific module
     * @param moduleId the module id of the module the lesson belongs to
     * @return List of lessons the belong to the module
     */
    @GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
    public List<Lesson> findAllLessonsForModule(@PathVariable("moduleId") int moduleId) {
        Optional<Module> data = moduleRepository.findById(moduleId);
        if(data.isPresent()) {
            Module module = data.get();
            return module.getLessons();
        }
        return null;
    }

    /**
     * Method to delete a lesson
     * @param lessonId lesson id of lesson to be deleted
     */
    @DeleteMapping("/api/lesson/{lId}")
    public void deleteLesson(@PathVariable("lId") int lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    /**
     * Method to update a lesson
     * @param updatedlesson updated lesson object
     * @param id ID of the existing lesson to be updated
     * @return Updated lesson object
     */
    @PutMapping("/api/lesson/{id}")
    public Lesson updateLesson(@RequestBody Lesson updatedlesson, @PathVariable("id") int id){
        Optional<Lesson> optionalLesson = findLessonById(id);
        if(optionalLesson.isPresent()){
            Lesson lesson = optionalLesson.get();
            lesson.set(updatedlesson);
            return lessonRepository.save(lesson);
        }
        return null;
    }
}
