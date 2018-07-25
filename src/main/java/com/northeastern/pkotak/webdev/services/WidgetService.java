package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Widget;
import com.northeastern.pkotak.webdev.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets(){
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{id}")
    public Optional<Widget> findWidgetById(@PathVariable("id") int widgetId){
        return widgetRepository.findById(widgetId);
    }
}
