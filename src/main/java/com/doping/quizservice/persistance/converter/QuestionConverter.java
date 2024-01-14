package com.doping.quizservice.persistance.converter;

import com.doping.quizservice.model.dto.QuestionDto;
import com.doping.quizservice.model.dto.SelectionDto;
import com.doping.quizservice.model.request.QuestionsRequest;
import com.doping.quizservice.persistance.entity.QuestionEntity;
import com.doping.quizservice.persistance.entity.QuizEntity;
import com.doping.quizservice.persistance.entity.SelectionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QuestionConverter {

    private final SelectionConverter selectionConverter;

    public QuestionEntity toQuestionEntity(QuestionsRequest questionsRequest, QuizEntity quizEntity) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionText(questionsRequest.getQuestion());
        List<SelectionEntity> selectionEntities = questionsRequest.getSelections()
                .stream()
                .map(selection -> selectionConverter.toSelectionEntity(selection, questionEntity))
                .toList();
        questionEntity.setSelections(selectionEntities);
        questionEntity.setQuiz(quizEntity);
        return questionEntity;
    }


    public QuestionDto toQuestionDto(QuestionEntity question) {
        List<SelectionDto> selections = question.getSelections().stream()
                .map(selectionConverter::toSelectionDto).toList();
        return QuestionDto.builder()
                .questionId(question.getId())
                .questionText(question.getQuestionText())
                .selections(selections)
                .build();
    }
}
