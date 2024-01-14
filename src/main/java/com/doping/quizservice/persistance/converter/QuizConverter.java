package com.doping.quizservice.persistance.converter;

import com.doping.quizservice.model.dto.QuestionDto;
import com.doping.quizservice.model.dto.QuizDto;
import com.doping.quizservice.persistance.entity.QuizEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QuizConverter {

    private final QuestionConverter questionConverter;

    public QuizDto toQuizDto(QuizEntity quizEntity) {
        List<QuestionDto> questionDtos = quizEntity.getQuestions().stream().map(questionConverter::toQuestionDto).toList();
        return QuizDto.builder()
                .quizId(quizEntity.getId())
                .quizName(quizEntity.getQuizName())
                .questions(questionDtos)
                .build();
    }
}
