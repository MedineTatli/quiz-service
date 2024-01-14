package com.doping.quizservice.persistance.repository;

import com.doping.quizservice.persistance.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByStudentNumber(String studentNumber);
}
