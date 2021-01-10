package com.sda.coursemanager.course.model;

import com.sda.coursemanager.user.model.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    @ManyToOne
    private User participant;
    @ManyToOne
    private Course course;

    public CourseEnrollment() {
    }

    public CourseEnrollment(LocalDate date, User participant, Course course) {
        this.date = date;
        this.participant = participant;
        this.course = course;
    }

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

    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User participant) {
        this.participant = participant;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
