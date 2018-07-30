package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Topic;
import com.northeastern.pkotak.webdev.models.Widget;
import com.northeastern.pkotak.webdev.repositories.TopicRepository;
import com.northeastern.pkotak.webdev.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets(){
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{id}")
    public Optional<Widget> findWidgetById(@PathVariable("id") int widgetId){
        return widgetRepository.findById(widgetId);
    }

    @GetMapping("/api/topic/{topicId}/widget")
    public List<Widget> findWidgetsForTopic(@PathVariable("topicId") int topicId){
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if(optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            return topic.getWidgets();
        }
        return null;
    }

    @PostMapping("/api/topic/{topicId}/widget")
    public List<Widget> saveWidgets(@PathVariable("topicId") int topicId, @RequestBody List<Widget> widgets){
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if(optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            for(Widget w : widgets){
                w.setTopic(topic);
                widgetRepository.save(w);
            }
            return (List<Widget>) widgetRepository.findAll();
        }
        return null;
    }

    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(@PathVariable("widgetId") int widgetId,@RequestBody Widget updatedWidget){
        Optional<Widget> optionalWidget = widgetRepository.findById(widgetId);
        if(optionalWidget.isPresent()){
            Widget widget = optionalWidget.get();
            //TODO: add set Method in widget Model
            return widget;
        }
        return null;
    }
}
