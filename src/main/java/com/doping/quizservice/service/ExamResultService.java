package com.doping.quizservice.service;

import com.doping.quizservice.advice.exception.QuizNotFoundException;
import com.doping.quizservice.advice.exception.StudentNotFoundException;
import com.doping.quizservice.model.dto.ResultDto;
import com.doping.quizservice.model.request.ResultRequest;
import com.doping.quizservice.persistance.converter.ExamConverter;
import com.doping.quizservice.persistance.converter.StudentAnswerConverter;
import com.doping.quizservice.persistance.entity.*;
import com.doping.quizservice.persistance.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExamResultService {

    private final StudentAnswerConverter studentAnswerConverter;
    private final QuizRepository quizRepository;
    private final StudentRepository studentRepository;
    private final ExamConverter examConverter;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final SelectionRepository selectionRepository;
    private final StudentAnswerRepository studentAnswerRepository;

    public ResultDto createResult(ResultRequest resultRequest) {
        Optional<StudentEntity> student = studentRepository.findByStudentNumber(resultRequest.getStudentNo());
        if (!student.isPresent()) {
            throw new StudentNotFoundException();
        }
        Optional<QuizEntity> quizEntity = quizRepository.findById(resultRequest.getQuizNo());
        if (!quizEntity.isPresent()) {
            throw new QuizNotFoundException();
        }
        ExamEntity examEntity = examConverter.toExamEntity(student.get(), quizEntity.get());
        examRepository.save(examEntity);
        for (Map.Entry<Long, Long> entry : resultRequest.getQuestionAndAnswers().entrySet()) {
            QuestionEntity questionEntity = questionRepository.findById(entry.getKey()).get();
            SelectionEntity selectionEntity = selectionRepository.findById(entry.getValue()).get();
            StudentAnswerEntity studentAnswerEntity = studentAnswerConverter.toStudentAnswerEntity(examEntity, questionEntity, selectionEntity);
            studentAnswerRepository.save(studentAnswerEntity);
        }
        return calculateExamResult(examEntity);
    }

    private ResultDto calculateExamResult(ExamEntity examEntity) {
        List<StudentAnswerEntity> studentEntityList = studentAnswerRepository.findAllByExamEntity_Id(examEntity.getId());
        long rightAnswerCount = studentEntityList.stream().map(StudentAnswerEntity::getSelection)
                .filter(selectionEntity -> selectionEntity.getIsTrue().equals(true)).count();
        int totalQuestionCount = studentEntityList.size();
        double score = ((double) rightAnswerCount / totalQuestionCount) * 100;
        examEntity.setScore(Math.round(score));
        examRepository.save(examEntity);
        return ResultDto.builder()
                .studentNo(examEntity.getStudentNo().getStudentNumber())
                .questionCount(totalQuestionCount)
                .rightAnswerCount(rightAnswerCount)
                .wrongAnswerCount(rightAnswerCount)
                .score(score)
                .build();
    }

    public List<ResultDto> getExamResultsByStudentId(String studentNo) {
        Optional<StudentEntity> studentEntity = studentRepository.findByStudentNumber(studentNo);
        if (studentEntity.isEmpty()) {
            throw new StudentNotFoundException();
        }
        return studentEntity.get().getExams().stream().map(this::calculateExamResult).toList();
    }
}
