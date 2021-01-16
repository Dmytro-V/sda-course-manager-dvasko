package com.sda.coursemanager.lesson;

import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.lesson.model.dto.LessonBlockDto;
import com.sda.coursemanager.user.UserRepository;
import com.sda.coursemanager.user.model.User;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
public class LessonBlockController {

    private final LessonBlockRepository lessonBlockRepository;
    private final UserRepository userRepository;

    public LessonBlockController(LessonBlockRepository lessonBlockRepository, UserRepository userRepository) {
        this.lessonBlockRepository = lessonBlockRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/lesson-blocks/{id}")
    public LessonBlockDto getLessonBlock(@PathVariable Long id) throws NotFoundException {
        LessonBlock lessonBlock = lessonBlockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lesson block not found"));
        return LessonBlockMapper.mapLessonBlockToDto(lessonBlock);
    }

    @PostMapping("/lesson-blocks/{id}/teachers/{teacher-id}")
    public LessonBlockDto setTeacher(@PathVariable("id") Long id, @PathVariable("teacher-id") Long teacherId) throws NotFoundException {
        LessonBlock lessonBlock = lessonBlockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("lesson block not found"));
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("teacher not found"));
        lessonBlock.setTeacher(teacher);
        lessonBlockRepository.save(lessonBlock);
        return LessonBlockMapper.mapLessonBlockToDto(lessonBlock);
    }

}
