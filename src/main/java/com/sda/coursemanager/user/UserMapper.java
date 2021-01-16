package com.sda.coursemanager.user;

import com.sda.coursemanager.course.CourseMapper;
import com.sda.coursemanager.course.model.Course;
import com.sda.coursemanager.course.model.dto.CourseDto;
import com.sda.coursemanager.user.model.User;
import com.sda.coursemanager.user.model.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDto mapUserToUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setType(user.getType());
        userDto.setUsername(user.getFirstName() + " " + user.getLastName());
        return userDto;
    }

    public static List<UserDto> mapUsersToUserDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::mapUserToUserDto)
                .collect(Collectors.toList());
    }
}
