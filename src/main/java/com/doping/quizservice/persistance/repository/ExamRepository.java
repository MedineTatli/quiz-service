package com.doping.quizservice.persistance.repository;

import com.doping.quizservice.persistance.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

}
