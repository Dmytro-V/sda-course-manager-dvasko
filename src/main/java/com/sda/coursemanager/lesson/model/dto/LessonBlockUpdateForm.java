package com.sda.coursemanager.lesson.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LessonBlockUpdateForm {
    @NotNull
    @NotEmpty
    private String subject;
    @NotNull
    private long teacherId;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}
