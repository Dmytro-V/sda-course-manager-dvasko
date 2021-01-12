package com.sda.coursemanager.course.model.dto;


import com.sda.coursemanager.user.model.dto.UserDto;

import java.time.LocalDate;

public class CourseEnrollmentDto {

    private long id;
    private LocalDate date;
    private UserDto participant;
    private CourseDto course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserDto getParticipant() {
        return participant;
    }

    public void setParticipant(UserDto participant) {
        this.participant = participant;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }
}
