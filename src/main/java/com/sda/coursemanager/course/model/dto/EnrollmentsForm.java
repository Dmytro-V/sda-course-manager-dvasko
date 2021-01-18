package com.sda.coursemanager.course.model.dto;

public class EnrollmentsForm {
    private String subject;
    private long participantId;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }
}
