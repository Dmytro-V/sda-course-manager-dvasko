package com.sda.coursemanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class LessonBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subject;
    @OneToMany
    private List<Lesson> lessons;
    @ManyToOne
    private User teacher;

    public LessonBlock() {
    }

    public LessonBlock(String subject, List<Lesson> lessons, User teacher) {
        this.subject = subject;
        this.lessons = lessons;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
}
