package com.sda.coursemanager.lesson;

import com.sda.coursemanager.exceptions.WrongUserTypeException;
import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.lesson.model.dto.LessonBlockUpdateForm;
import com.sda.coursemanager.user.UserRepository;
import com.sda.coursemanager.user.model.Role;
import com.sda.coursemanager.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    @Test
    void setTeacherFailTypeTeacher() {
        User participant = initUser("single_user", "pass", Role.PARTICIPANT);

        LessonBlockUpdateForm lessonBlockUpdateForm = new LessonBlockUpdateForm();
        lessonBlockUpdateForm.setSubject("subject");
        lessonBlockUpdateForm.setTeacherId(1L);

        LessonBlock lessonBlock = new LessonBlock();


        when(lessonBlockRepository.findById(any())).thenReturn(Optional.of(lessonBlock));

        when(userRepository.findById(any())).thenReturn(Optional.of(participant));

        assertThrows(WrongUserTypeException.class, () -> {
            lessonController.setTeacher(any(), lessonBlockUpdateForm);
        });
    }

    private User initUser(String login, String pass, Role type) {
        User user = new User();
        user.setActive(true);
        user.setLogin(login);
        user.setPass(pass);
        user.setFirstName(login);
        user.setLastName(pass);
        user.setType(type);
        return user;
    }

}

