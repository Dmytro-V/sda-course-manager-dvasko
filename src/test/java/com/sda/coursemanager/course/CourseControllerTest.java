package com.sda.coursemanager.course;

import com.sda.coursemanager.course.model.Course;
import com.sda.coursemanager.course.model.CourseEnrollment;
import com.sda.coursemanager.course.model.dto.CourseEnrollmentDto;
import com.sda.coursemanager.course.model.dto.EnrollmentsForm;
import com.sda.coursemanager.exceptions.EnrollmentCreateException;
import com.sda.coursemanager.exceptions.NotFoundException;
import com.sda.coursemanager.exceptions.WrongUserTypeException;
import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.user.UserRepository;
import com.sda.coursemanager.user.model.Role;
import com.sda.coursemanager.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseEnrollmentRepository courseEnrollmentRepository;
    @InjectMocks
    private CourseController courseController;

    private EnrollmentsForm enrollmentsForm;

    @BeforeEach
    void setUp() {
        this.enrollmentsForm = new EnrollmentsForm();
        enrollmentsForm.setCourseId(1L);
        enrollmentsForm.setParticipantId(2L);
    }

    @Test
    void setCourseEnrollmentFailIdCourse() {

        when(courseRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            courseController.setCourseEnrollment(enrollmentsForm);
        });
    }

    @Test
    void setCourseEnrollmentFailIdParticipant() {

        when(courseRepository.findById(any())).thenReturn(Optional.of(new Course()));
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            courseController.setCourseEnrollment(enrollmentsForm);
        });
    }

    @Test
    void setCourseEnrollmentFailTypeParticipant() {

        when(courseRepository.findById(any())).thenReturn(Optional.of(new Course()));

        User teacher = new User();
        teacher.setType(Role.TEACHER);
        when(userRepository.findById(any())).thenReturn(Optional.of(teacher));

        assertThrows(WrongUserTypeException.class, () -> {
            courseController.setCourseEnrollment(enrollmentsForm);
        });
    }

    @Test
    void setCourseEnrollmentFailAlreadyExist() {

        when(courseRepository.findById(any())).thenReturn(Optional.of(new Course()));

        User participant = new User();
        participant.setType(Role.PARTICIPANT);
        when(userRepository.findById(any())).thenReturn(Optional.of(participant));

        when(courseEnrollmentRepository.findByParticipantAndCourse(any(), any())).thenReturn(Optional.of(new CourseEnrollment()));

        assertThrows(EnrollmentCreateException.class, () -> {
            courseController.setCourseEnrollment(enrollmentsForm);
        });
    }

    @Test
    void setCourseEnrollmentSuccess() throws WrongUserTypeException, NotFoundException, EnrollmentCreateException {
        Course course = new Course();
        course.setLessonBlocks(new ArrayList<>());
        when(courseRepository.findById(any())).thenReturn(Optional.of(course));

        User participant = new User();
        participant.setType(Role.PARTICIPANT);
        when(userRepository.findById(any())).thenReturn(Optional.of(participant));

        when(courseEnrollmentRepository.findByParticipantAndCourse(any(), any())).thenReturn(Optional.empty());

        CourseEnrollment courseEnrollment = new CourseEnrollment();
        courseEnrollment.setCourse(course);
        courseEnrollment.setParticipant(participant);

        when(courseEnrollmentRepository.save(any())).thenReturn(courseEnrollment);

        courseController.setCourseEnrollment(enrollmentsForm);
    }

}
