package com.sda.coursemanager.course.model.dto;

import com.sda.coursemanager.lesson.model.dto.LessonBlockDto;

import java.util.List;

public class CourseDetailsDto {
    private long id;
    private String name;
    private List<LessonBlockDto> lessonBlockDtos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LessonBlockDto> getLessonBlockDtos() {
        return lessonBlockDtos;
    }

    public void setLessonBlockDtos(List<LessonBlockDto> lessonBlockDtos) {
        this.lessonBlockDtos = lessonBlockDtos;
    }
}
