package com.sda.coursemanager.user;

import com.sda.coursemanager.user.model.Role;
import com.sda.coursemanager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByType(Role type);

}
