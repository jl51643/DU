package com.fer.hr.du.repository;

import com.fer.hr.du.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
