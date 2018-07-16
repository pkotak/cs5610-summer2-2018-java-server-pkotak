package com.northeastern.pkotak.webdev.repositories;

import com.northeastern.pkotak.webdev.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
