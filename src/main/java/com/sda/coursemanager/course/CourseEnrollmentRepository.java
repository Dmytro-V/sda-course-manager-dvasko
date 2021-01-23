package com.sda.coursemanager.course;

import com.sda.coursemanager.course.model.Course;
import com.sda.coursemanager.course.model.CourseEnrollment;
import com.sda.coursemanager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {

    Optional<CourseEnrollment> findByParticipantAndCourse(User participant, Course course);
}
