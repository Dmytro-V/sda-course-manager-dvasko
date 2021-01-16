package com.sda.coursemanager.lesson;

import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.lesson.model.dto.LessonBlockDto;
import com.sda.coursemanager.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LessonBlockMapper {

    private LessonBlockMapper() {
    }

    public static List<LessonBlockDto> mapLessonBlocksToDtoList(List<LessonBlock> lessonBlocks) {
        return lessonBlocks.stream()
                .map(LessonBlockMapper::mapLessonBlockToDto)
                .collect(Collectors.toList());
    }

    public static LessonBlockDto mapLessonBlockToDto(LessonBlock lessonBlock) {
        LessonBlockDto lessonBlockDto = new LessonBlockDto();
        lessonBlockDto.setId(lessonBlock.getId());
        lessonBlockDto.setSubject(lessonBlock.getSubject());
        lessonBlockDto.setLessons(new ArrayList<>(lessonBlock.getLessons()));
        if (lessonBlock.getTeacher() != null) {
            lessonBlockDto.setTeacherId(lessonBlock.getTeacher().getId());
            lessonBlockDto.setTeacherName(lessonBlock.getTeacher().getFirstName() + " " + lessonBlock.getTeacher().getLastName());
        }
        return lessonBlockDto;
    }

    private static Long getTeacherId(LessonBlock block) {
        return Optional.ofNullable(block)
                .map(LessonBlock::getTeacher)
                .map(User::getId)
                .orElse(null);
    }

}
