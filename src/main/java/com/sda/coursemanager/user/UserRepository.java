package com.sda.coursemanager.user;

import com.sda.coursemanager.user.model.Role;
import com.sda.coursemanager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByType(Role role);

    User findByType(Role role);
}
