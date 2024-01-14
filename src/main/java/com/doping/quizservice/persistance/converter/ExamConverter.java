package com.doping.quizservice.persistance.converter;

import com.doping.quizservice.persistance.entity.ExamEntity;
import com.doping.quizservice.persistance.entity.QuizEntity;
import com.doping.quizservice.persistance.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExamConverter {

    public ExamEntity toExamEntity(StudentEntity student, QuizEntity quiz) {
        ExamEntity examEntity = new ExamEntity();
        examEntity.setQuiz(quiz);
        examEntity.setStudentNo(student);
        return examEntity;
    }
}
