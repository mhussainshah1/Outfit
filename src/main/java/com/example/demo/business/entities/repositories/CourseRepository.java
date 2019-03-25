package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
