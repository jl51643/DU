package com.fer.hr.du.repository;

import com.fer.hr.du.model.AbstractStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<AbstractStateEntity, Long> {
}
