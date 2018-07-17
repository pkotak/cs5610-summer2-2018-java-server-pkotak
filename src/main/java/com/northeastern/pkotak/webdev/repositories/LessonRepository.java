package com.northeastern.pkotak.webdev.repositories;

import com.northeastern.pkotak.webdev.models.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
