package com.sda.coursemanager.course;

import com.sda.coursemanager.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
