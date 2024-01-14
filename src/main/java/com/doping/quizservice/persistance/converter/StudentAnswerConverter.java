package com.doping.quizservice.persistance.converter;

import com.doping.quizservice.persistance.entity.ExamEntity;
import com.doping.quizservice.persistance.entity.QuestionEntity;
import com.doping.quizservice.persistance.entity.SelectionEntity;
import com.doping.quizservice.persistance.entity.StudentAnswerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentAnswerConverter {

    public StudentAnswerEntity toStudentAnswerEntity(ExamEntity examEntity, QuestionEntity questionEntity, SelectionEntity selectionEntity) {
        StudentAnswerEntity studentAnswerEntity = new StudentAnswerEntity();
        studentAnswerEntity.setExamEntity(examEntity);
        studentAnswerEntity.setQuestion(questionEntity);
        studentAnswerEntity.setSelection(selectionEntity);
        return studentAnswerEntity;
    }
}
