package com.sda.coursemanager.course.model.dto;

import javax.validation.constraints.NotNull;

public class EnrollmentsForm {
    @NotNull
    private Long courseId;
    @NotNull
    private Long participantId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }
}
