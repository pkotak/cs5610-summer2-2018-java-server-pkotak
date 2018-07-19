package com.northeastern.pkotak.webdev.repositories;

import com.northeastern.pkotak.webdev.models.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
}
