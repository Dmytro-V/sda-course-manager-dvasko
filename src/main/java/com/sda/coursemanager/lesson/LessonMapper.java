package com.sda.coursemanager.lesson;

import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.lesson.model.dto.LessonBlockDto;
import com.sda.coursemanager.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LessonMapper {

    private LessonMapper() {
    }

    public static List<LessonBlockDto> mapLessonBlocksToDtoList(List<LessonBlock> lessonBlocks) {
        return lessonBlocks.stream()
                .map(LessonMapper::mapLessonBlockToDto)
                .collect(Collectors.toList());
    }

    public static LessonBlockDto mapLessonBlockToDto(LessonBlock lessonBlock) {
        LessonBlockDto lessonBlockDto = new LessonBlockDto();
        lessonBlockDto.setId(lessonBlock.getId());
        lessonBlockDto.setSubject(lessonBlock.getSubject());
        lessonBlockDto.setLessons(new ArrayList<>(lessonBlock.getLessons()));

        lessonBlockDto.setTeacherId(getTeacherId(lessonBlock));
        lessonBlockDto.setTeacherName(getTeacherName(lessonBlock));

        return lessonBlockDto;
    }

    private static String getTeacherName(LessonBlock lessonBlock) {
        return Optional.ofNullable(lessonBlock)
                .map(LessonBlock::getTeacher)
                .map(user -> (user.getFirstName() + " " + user.getLastName()))
                .orElse("");
    }

    private static long getTeacherId(LessonBlock lessonBlock) {
        return Optional.ofNullable(lessonBlock)
                .map(LessonBlock::getTeacher)
                .map(User::getId)
                .orElse(0L);
    }

}
