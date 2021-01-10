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
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final LessonBlockRepository lessonBlockRepository;
    private final CourseRepository courseRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;

    public DbInitializer(UserRepository userRepository, LessonRepository lessonRepository, LessonBlockRepository lessonBlockRepository, CourseRepository courseRepository, CourseEnrollmentRepository courseEnrollmentRepository) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.lessonBlockRepository = lessonBlockRepository;
        this.courseRepository = courseRepository;
        this.courseEnrollmentRepository = courseEnrollmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        initUsers();

        if (lessonRepository.count() == 0 || lessonBlockRepository.count() == 0 || courseRepository.count() == 0 || courseEnrollmentRepository.count() == 0) {

            // initializing lessonBlock Spring
            Lesson lessonSpringBoot = new Lesson("Spring Boot", LocalDate.now().minusDays(50));
            Lesson lessonSpringJpa = new Lesson("Spring JPA", LocalDate.now().minusDays(40));
            Lesson lessonSpringSecurity = new Lesson("Spring Security", LocalDate.now().minusDays(30));

            List<Lesson> listSpring = new ArrayList<>();
            listSpring.add(lessonSpringBoot);
            listSpring.add(lessonSpringJpa);
            listSpring.add(lessonSpringSecurity);

            lessonRepository.saveAll(listSpring);

            User teacher = userRepository.findByType(Role.TEACHER);
            LessonBlock lessonBlockSpring = new LessonBlock("Spring", listSpring, teacher);
            lessonBlockRepository.save(lessonBlockSpring);


            // initializing lessonBlock Test
            Lesson lessonTests = new Lesson("Testing in java", LocalDate.now().minusDays(20));
            Lesson lessonTestAdvanced = new Lesson("Testing advanced", LocalDate.now().minusDays(10));

            List<Lesson> listTests = new ArrayList<>();
            listTests.add(lessonTests);
            listTests.add(lessonTestAdvanced);

            lessonRepository.saveAll(listTests);

            LessonBlock lessonBlockTests = new LessonBlock("Test", listTests, teacher);
            lessonBlockRepository.save(lessonBlockTests);


            //initializing Course
            List<LessonBlock> listLessonBlocksJava = new ArrayList<>();
            listLessonBlocksJava.add(lessonBlockSpring);
            listLessonBlocksJava.add(lessonBlockTests);
            Course courseJava = new Course("Java", listLessonBlocksJava);
            courseRepository.save(courseJava);


            //initializing CourseEnrollment
            User user = userRepository.findByType(Role.PARTICIPANT);
            CourseEnrollment courseEnrollment = new CourseEnrollment(LocalDate.now().minusDays(5), user, courseJava);
            courseEnrollmentRepository.save(courseEnrollment);

        }
    }

    private void initUsers() {
        if (!userRepository.existsUserByType(Role.ADMIN)) {
            User admin = new User();
            admin.setLogin("admin");
            admin.setPass("admin");
            admin.setType(Role.ADMIN);
            admin.setFirstName("Administrator");
            admin.setLastName("Administrowski");
            admin.setActive(true);
            userRepository.save(admin);
        }

        if (!userRepository.existsUserByType(Role.TEACHER)) {
            User teacher = new User();
            teacher.setLogin("teacher");
            teacher.setPass("teacher");
            teacher.setType(Role.TEACHER);
            teacher.setFirstName("Teacher");
            teacher.setLastName("Teacherowski");
            teacher.setActive(true);
            userRepository.save(teacher);
        }

        if (!userRepository.existsUserByType(Role.PARTICIPANT)) {
            User participant = new User();
            participant.setLogin("user");
            participant.setPass("user");
            participant.setType(Role.PARTICIPANT);
            participant.setFirstName("User");
            participant.setLastName("Userów");
            participant.setActive(true);
            userRepository.save(participant);
        }
    }

}
