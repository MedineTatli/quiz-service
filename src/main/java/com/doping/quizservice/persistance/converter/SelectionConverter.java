package com.doping.quizservice.persistance.converter;


import com.doping.quizservice.model.dto.SelectionDto;
import com.doping.quizservice.model.request.SelectionRequest;
import com.doping.quizservice.persistance.entity.QuestionEntity;
import com.doping.quizservice.persistance.entity.SelectionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SelectionConverter {

    public SelectionEntity toSelectionEntity(SelectionRequest selectionRequest, QuestionEntity questionEntity) {
        SelectionEntity selectionEntity = new SelectionEntity();
        selectionEntity.setSelection(selectionRequest.getSelection());
        selectionEntity.setIsTrue(selectionRequest.isTrue());
        selectionEntity.setQuestion(questionEntity);
        return selectionEntity;
    }

    public SelectionDto toSelectionDto(SelectionEntity selectionEntity) {
        return SelectionDto.builder()
                .selectionId(selectionEntity.getId())
                .selection(selectionEntity.getSelection())
                .isTrue(selectionEntity.getIsTrue())
                .build();
    }
}
