package com.doping.quizservice.persistance.repository;

import com.doping.quizservice.persistance.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    void deleteById(Long quizId);


}
