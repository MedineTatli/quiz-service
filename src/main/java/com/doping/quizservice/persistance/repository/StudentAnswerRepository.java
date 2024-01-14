package com.doping.quizservice.persistance.repository;

import com.doping.quizservice.persistance.entity.StudentAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity, Long> {

    List<StudentAnswerEntity> findAllByExamEntity_Id(Long examId);

}
