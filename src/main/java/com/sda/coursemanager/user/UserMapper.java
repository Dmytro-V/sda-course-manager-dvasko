package com.sda.coursemanager.user;

import com.sda.coursemanager.user.model.User;
import com.sda.coursemanager.user.model.dto.UserDto;

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
}
