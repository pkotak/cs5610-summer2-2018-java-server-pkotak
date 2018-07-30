package com.northeastern.pkotak.webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne
    @JsonIgnore
    private Module module;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Topic> topics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void set(Lesson updatedLesson){
        this.setTitle(updatedLesson.getTitle() != null ? updatedLesson.getTitle() : this.getTitle());
        this.setModule(updatedLesson.getModule() != null ? updatedLesson.getModule() : this.getModule());
        this.setTopics(updatedLesson.getTopics() != null ? updatedLesson.getTopics() : this.getTopics());
    }

}
