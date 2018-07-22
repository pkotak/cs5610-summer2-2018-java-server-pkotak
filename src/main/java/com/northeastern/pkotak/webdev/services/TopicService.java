package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Lesson;
import com.northeastern.pkotak.webdev.models.Topic;
import com.northeastern.pkotak.webdev.repositories.LessonRepository;
import com.northeastern.pkotak.webdev.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    LessonRepository lessonRepository;

    /**
     * Method to create a new topic
     * @param topic Topic object to be inserted into the database
     * @param lId Lesson id
     * @return
     */
    @PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
    public Topic createTopic(@RequestBody Topic topic,
                             @PathVariable("lessonId") int lId){
        Optional<Lesson> optionalLesson = lessonRepository.findById(lId);
        if(optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            topic.setLesson(lesson);
            return topicRepository.save(topic);
        }
        return null;
    }

    /**
     * Method to find all the topics that belong to a specific lesson
     * @param lessonId Lesson Id
     * @return List of topics that belong to a lesson
     */
    @GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
    public List<Topic> findAllTopicsForLesson(@PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            return lesson.getTopics();
        }
        return null;
    }

    /**
     * method to delete a topic
     * @param topicId specific Id of a topic to be deleted
     */
    @DeleteMapping("/api/topic/{tId}")
    public void deleteTopic(@PathVariable("tId") int topicId) {
        topicRepository.deleteById(topicId);
    }
}
