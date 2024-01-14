package com.doping.quizservice.persistance.repository;

import com.doping.quizservice.persistance.entity.StudentAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<StudentAnswerEntity, Long> {


}
