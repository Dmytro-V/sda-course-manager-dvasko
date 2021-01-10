package com.sda.coursemanager.course;

import com.sda.coursemanager.course.model.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
}
