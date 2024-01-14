package com.doping.quizservice.persistance.repository;

import com.doping.quizservice.persistance.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {


}
