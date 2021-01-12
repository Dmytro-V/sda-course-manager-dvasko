package com.sda.coursemanager.user.model.dto;

import com.sda.coursemanager.user.model.Role;

public class UserDto {

    private long id;
    private Role type;
    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
