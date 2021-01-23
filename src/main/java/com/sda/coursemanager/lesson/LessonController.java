package com.sda.coursemanager.lesson;

import com.sda.coursemanager.exceptions.NotFoundException;
import com.sda.coursemanager.exceptions.WrongUserTypeException;
import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.lesson.model.dto.LessonBlockDto;
import com.sda.coursemanager.lesson.model.dto.LessonBlockUpdateForm;
import com.sda.coursemanager.user.UserRepository;
import com.sda.coursemanager.user.model.Role;
import com.sda.coursemanager.user.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LessonController {

    private final LessonBlockRepository lessonBlockRepository;
    private final UserRepository userRepository;

    public LessonController(LessonBlockRepository lessonBlockRepository, UserRepository userRepository) {
        this.lessonBlockRepository = lessonBlockRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/lesson-blocks/{id}")
    public LessonBlockDto getLessonBlock(@PathVariable Long id) throws NotFoundException {
        LessonBlock lessonBlock = lessonBlockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lesson block not found"));
        return LessonMapper.mapLessonBlockToDto(lessonBlock);
    }

    @PutMapping("/lesson-blocks/{id}")
    public LessonBlockDto setTeacher(@PathVariable("id") Long id,
                                     @RequestBody LessonBlockUpdateForm lessonBlockUpdateForm) throws NotFoundException, WrongUserTypeException {
        LessonBlock lessonBlock = lessonBlockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("lesson block not found"));

        lessonBlock.setSubject(lessonBlockUpdateForm.getSubject());

        User teacher = userRepository.findById(lessonBlockUpdateForm.getTeacherId())
                .orElseThrow(() -> new NotFoundException("teacher not found"));

        if (teacher.getType() != Role.TEACHER) {
            throw new WrongUserTypeException("this is not a teacher");
        }

        lessonBlock.setTeacher(teacher);
        LessonBlock updatedLessonBlock = lessonBlockRepository.save(lessonBlock);
        return LessonMapper.mapLessonBlockToDto(updatedLessonBlock);
    }

}
