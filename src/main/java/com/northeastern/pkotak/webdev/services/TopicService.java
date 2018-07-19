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

    @GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
    public List<Topic> findAllTopicsForLesson(@PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            return lesson.getTopics();
        }
        return null;
    }

    @DeleteMapping("/api/topic/{tId}")
    public void deleteTopic(@PathVariable("tId") int topicId) {
        topicRepository.deleteById(topicId);
    }
}
