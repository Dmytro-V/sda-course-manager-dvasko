package com.sda.coursemanager.lesson;

import com.sda.coursemanager.exceptions.NotFoundException;
import com.sda.coursemanager.exceptions.WrongUserTypeException;
import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.lesson.model.dto.LessonBlockUpdateForm;
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
class LessonControllerTest {

    @Mock
    private LessonBlockRepository lessonBlockRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private LessonController lessonController;

    private LessonBlockUpdateForm lessonBlockUpdateForm;

    @BeforeEach
    void setUp() {
        this.lessonBlockUpdateForm = new LessonBlockUpdateForm();
        lessonBlockUpdateForm.setTeacherId(1L);
        lessonBlockUpdateForm.setSubject("subject");
    }

    @Test
    void setTeacherFailIdLessonBlock() {

        when(lessonBlockRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            lessonController.setTeacher(any(), new LessonBlockUpdateForm());
        });
    }

    @Test
    void setTeacherFailIdTeacher() {

        when(lessonBlockRepository.findById(any())).thenReturn(Optional.of(new LessonBlock()));

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            lessonController.setTeacher(any(), lessonBlockUpdateForm);
        });
    }

    @Test
    void setTeacherFailTypeTeacher() {

        when(lessonBlockRepository.findById(any())).thenReturn(Optional.of(new LessonBlock()));

        User participant = new User();
        participant.setType(Role.PARTICIPANT);

        when(userRepository.findById(any())).thenReturn(Optional.of(participant));

        assertThrows(WrongUserTypeException.class, () -> {
            lessonController.setTeacher(any(), lessonBlockUpdateForm);
        });
    }

    @Test
    void setTeacherSuccess() throws NotFoundException, WrongUserTypeException {

        when(lessonBlockRepository.findById(any())).thenReturn(Optional.of(new LessonBlock()));

        User teacher = new User();
        teacher.setType(Role.TEACHER);
        when(userRepository.findById(any())).thenReturn(Optional.of(teacher));

        LessonBlock lessonBlock = new LessonBlock();
        lessonBlock.setLessons(new ArrayList<>());
        when(lessonBlockRepository.save(any())).thenReturn(lessonBlock);

        lessonController.setTeacher(any(), lessonBlockUpdateForm);
    }

}

