package com.sda.coursemanager.lesson;

import com.sda.coursemanager.lesson.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
