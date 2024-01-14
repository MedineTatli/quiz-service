package com.doping.quizservice.persistance.repository;

import com.doping.quizservice.persistance.entity.SelectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionRepository extends JpaRepository<SelectionEntity, Long> {

}
