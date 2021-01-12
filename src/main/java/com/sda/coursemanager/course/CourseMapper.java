package com.sda.coursemanager.course;

import com.sda.coursemanager.course.model.Course;
import com.sda.coursemanager.course.model.CourseEnrollment;
import com.sda.coursemanager.course.model.dto.CourseDetailsDto;
import com.sda.coursemanager.course.model.dto.CourseDto;
import com.sda.coursemanager.course.model.dto.CourseEnrollmentDto;
import com.sda.coursemanager.lesson.LessonBlockMapper;
import com.sda.coursemanager.user.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    private CourseMapper() {
    }

    public static List<CourseDto> mapCourseToDtoList(List<Course> courses) {
        return courses.stream()
                .map(CourseMapper::mapCourseToDto)
                .collect(Collectors.toList());
    }

    public static CourseDto mapCourseToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());

        int hourCount = course.getLessonBlocks().stream()
                .mapToInt(block -> block.getLessons().size() * 8)
                .sum();

        courseDto.setCourseLength(hourCount);
        return courseDto;
    }

    public static CourseDetailsDto mapCourseToDetailsDto(Course course) {
        CourseDetailsDto courseDetailsDto = new CourseDetailsDto();
        courseDetailsDto.setId(course.getId());
        courseDetailsDto.setName(course.getName());

        courseDetailsDto.setLessonBlockDtos(LessonBlockMapper.mapLessonBlocksToDtoList(course.getLessonBlocks()));

        return courseDetailsDto;
    }

    public static CourseEnrollmentDto mapEnrollmentToEnrollmentDto(CourseEnrollment courseEnrollment) {
        CourseEnrollmentDto enrollmentDto = new CourseEnrollmentDto();
        enrollmentDto.setId(courseEnrollment.getId());
        enrollmentDto.setDate(courseEnrollment.getDate());
        enrollmentDto.setCourse(CourseMapper.mapCourseToDto(courseEnrollment.getCourse()));
        enrollmentDto.setParticipant(UserMapper.mapUserToUserDto(courseEnrollment.getParticipant()));
        return enrollmentDto;
    }

    public static List<CourseEnrollmentDto> mapEnrollmentsToEnrollmentDtoList(List<CourseEnrollment> enrollments) {
        return enrollments.stream()
                .map(CourseMapper::mapEnrollmentToEnrollmentDto)
                .collect(Collectors.toList());
    }

}
