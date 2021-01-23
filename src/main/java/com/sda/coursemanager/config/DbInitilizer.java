package com.sda.coursemanager.config;

import com.sda.coursemanager.course.CourseEnrollmentRepository;
import com.sda.coursemanager.course.CourseRepository;
import com.sda.coursemanager.course.model.Course;
import com.sda.coursemanager.course.model.CourseEnrollment;
import com.sda.coursemanager.lesson.LessonBlockRepository;
import com.sda.coursemanager.lesson.LessonRepository;
import com.sda.coursemanager.lesson.model.Lesson;
import com.sda.coursemanager.lesson.model.LessonBlock;
import com.sda.coursemanager.user.UserRepository;
import com.sda.coursemanager.user.model.Role;
import com.sda.coursemanager.user.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DbInitilizer implements CommandLineRunner {

    private final ConfigurableApplicationContext context;

    private final LessonRepository lessonRepository;
    private final LessonBlockRepository lessonBlockRepository;
    private final CourseRepository courseRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final UserRepository userRepository;

    public DbInitilizer(LessonRepository lessonRepository, LessonBlockRepository lessonBlockRepository,
                        CourseRepository courseRepository, CourseEnrollmentRepository courseEnrollmentRepository,
                        UserRepository userRepository,
                        ConfigurableApplicationContext context) {
        this.lessonRepository = lessonRepository;
        this.lessonBlockRepository = lessonBlockRepository;
        this.courseRepository = courseRepository;
        this.courseEnrollmentRepository = courseEnrollmentRepository;
        this.userRepository = userRepository;
        this.context = context;
    }

    @Override
    public void run(String... args) {

        flushDatabase();
        if (userRepository.count() == 0) {
            User participant = initUser("single_user", "pass", Role.PARTICIPANT);

            IntStream.range(0, 5).forEach(i -> {
                initUser("participant-"+i, "pass", Role.PARTICIPANT);
                initUser("teacher-"+i, "pass", Role.TEACHER);
                initUser("admin-"+i, "admin", Role.ADMIN);
            });

            initCourse("testing for dummies");
            initCourse("cloud computing");
            initCourse("artificial intelligence");
            Course javaCourse = initCourse("java basics");

            CourseEnrollment enrollment = new CourseEnrollment();
            enrollment.setDate(LocalDate.now());
            enrollment.setParticipant(participant);
            enrollment.setCourse(javaCourse);
            courseEnrollmentRepository.save(enrollment);
        }

//        context.close();
    }

    private User initUser(String login, String pass, Role type) {
        User user = new User();
        user.setActive(true);
        user.setLogin(login);
        user.setPass(pass);
        user.setFirstName(login);
        user.setLastName(pass);
        user.setType(type);
        userRepository.save(user);
        return user;
    }

    private Course initCourse(String name) {
        Course course = new Course();
        course.setName(name);
        course.setLessonBlocks(initBlocks(name));
        courseRepository.save(course);
        return course;
    }

    private List<LessonBlock> initBlocks(String coursePrefix) {
        LessonBlock block_test = new LessonBlock();
        LessonBlock block_db = new LessonBlock();
        LessonBlock block_java = new LessonBlock();

        block_java.setSubject(coursePrefix + " java");
        block_java.setLessons(initLessons("java", 12));
        lessonBlockRepository.save(block_java);

        block_db.setSubject(coursePrefix + " db");
        block_db.setLessons(initLessons("db", 4));
        lessonBlockRepository.save(block_db);

        block_test.setSubject(coursePrefix + " test");
        block_test.setLessons(initLessons("test", 2));
        lessonBlockRepository.save(block_test);

        return List.of(block_java, block_db, block_test);
    }

    private List<Lesson> initLessons(String subject, int count) {
        return IntStream.range(0, count).mapToObj(i -> {
            Lesson lesson = new Lesson();
            lesson.setSubject(subject + " " + i);
            lesson.setDate(LocalDate.now().minusDays(31 - i));
            lessonRepository.save(lesson);
            return lesson;
        }).collect(Collectors.toList());
    }

    private void flushDatabase() {
        courseEnrollmentRepository.deleteAll();
        courseRepository.deleteAll();
        lessonBlockRepository.deleteAll();
        lessonRepository.deleteAll();
        userRepository.deleteAll();
    }
}
