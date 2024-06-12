package com.fer.hr.du.repository;

import com.fer.hr.du.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
