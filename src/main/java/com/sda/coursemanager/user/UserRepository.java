package com.sda.coursemanager.user;

import com.sda.coursemanager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
