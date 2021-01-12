package com.sda.coursemanager.course;

import com.sda.coursemanager.course.model.Course;
import com.sda.coursemanager.course.model.CourseEnrollment;
import com.sda.coursemanager.course.model.dto.CourseDetailsDto;
import com.sda.coursemanager.course.model.dto.CourseDto;
import com.sda.coursemanager.course.model.dto.CourseEnrollmentDto;
import com.sda.coursemanager.user.UserRepository;
import com.sda.coursemanager.user.model.User;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;

    public CourseController(CourseRepository courseRepository, UserRepository userRepository, CourseEnrollmentRepository courseEnrollmentRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseEnrollmentRepository = courseEnrollmentRepository;
    }

    @GetMapping("/courses/")
    public List<CourseDto> getAllCourses() {
        return CourseMapper.mapCourseToDtoList(courseRepository.findAll());
    }

    @GetMapping("/courses/{id}")
    public CourseDetailsDto getSingleCourseDto(@PathVariable Long id) throws NotFoundException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("course not found"));
        return CourseMapper.mapCourseToDetailsDto(course);
    }

    @PostMapping("/courses/{course-id}/enrollment/{user-id}")
    public CourseEnrollmentDto setCourseEnrollment(@PathVariable("course-id") Long id, @PathVariable("user-id") Long userId) throws NotFoundException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("course not found"));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found"));

        CourseEnrollment enrollment = new CourseEnrollment();
        enrollment.setDate(LocalDate.now());
        enrollment.setParticipant(user);
        enrollment.setCourse(course);
        CourseEnrollment courseEnrollment = courseEnrollmentRepository.save(enrollment);

        return CourseMapper.mapEnrollmentToEnrollmentDto(courseEnrollment);
    }

    @GetMapping("/enrollments/")
    public List<CourseEnrollmentDto> getEnrollments() {
        return CourseMapper.mapEnrollmentsToEnrollmentDtoList(courseEnrollmentRepository.findAll());

    }
}
