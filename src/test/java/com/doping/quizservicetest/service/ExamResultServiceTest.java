package com.doping.quizservicetest.service;

import com.doping.quizservice.advice.exception.StudentNotFoundException;
import com.doping.quizservice.model.dto.ResultDto;
import com.doping.quizservice.model.request.ResultRequest;
import com.doping.quizservice.persistance.converter.ExamConverter;
import com.doping.quizservice.persistance.converter.StudentAnswerConverter;
import com.doping.quizservice.persistance.entity.*;
import com.doping.quizservice.persistance.repository.*;
import com.doping.quizservice.service.ExamResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExamResultServiceTest {

    @InjectMocks
    private ExamResultService examResultService;

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ExamConverter examConverter;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private SelectionRepository selectionRepository;

    @Mock
    private StudentAnswerRepository studentAnswerRepository;

    @Mock
    private StudentAnswerConverter studentAnswerConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void createResult_withValidRequest_shouldReturnResultDto() {

        ResultRequest resultRequest = new ResultRequest("1", 1L, Collections.singletonMap(1L, 2L));
        StudentEntity studentEntity = new StudentEntity();
        QuizEntity quizEntity = new QuizEntity();
        ExamEntity examEntity = new ExamEntity();
        QuestionEntity questionEntity = new QuestionEntity();
        SelectionEntity selectionEntity = new SelectionEntity();
        examEntity.setStudentNo(studentEntity);
        studentEntity.setStudentNumber("1");

        when(studentRepository.findByStudentNumber("1")).thenReturn(Optional.of(studentEntity));
        when(quizRepository.findById(1L)).thenReturn(Optional.of(quizEntity));
        when(examConverter.toExamEntity(any(), any())).thenReturn(examEntity);
        when(questionRepository.findById(1L)).thenReturn(Optional.of(questionEntity));
        when(selectionRepository.findById(2L)).thenReturn(Optional.of(selectionEntity));

        ResultDto resultDto = examResultService.createResult(resultRequest);

        assertNotNull(resultDto);
        verify(examRepository, times(2)).save(examEntity);
        verify(studentAnswerRepository, times(1)).save(any());
    }
    @Test
    public void createResult_with_invalid_student_no_shouldThrowErrorException() {

        ResultRequest resultRequest = new ResultRequest("123",null,null);

        when(studentRepository.findByStudentNumber("123")).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> examResultService.createResult(resultRequest));
        verify(examRepository, never()).save(any());
        verify(studentAnswerRepository, never()).save(any());
    }
}
