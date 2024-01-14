package com.doping.quizservicetest.service;

import com.doping.quizservice.advice.exception.TooManyCorrectAnswerException;
import com.doping.quizservice.model.request.CreateQuizRequest;
import com.doping.quizservice.model.request.QuestionsRequest;
import com.doping.quizservice.model.request.SelectionRequest;
import com.doping.quizservice.persistance.converter.QuestionConverter;
import com.doping.quizservice.persistance.entity.QuestionEntity;
import com.doping.quizservice.persistance.entity.QuizEntity;
import com.doping.quizservice.persistance.repository.QuizRepository;
import com.doping.quizservice.persistance.service.RedisService;
import com.doping.quizservice.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizServiceTest {

    @InjectMocks
    private QuizService quizService;

    @Mock
    private QuestionConverter questionConverter;

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private RedisService redisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createQuiz_withValidRequest_shouldReturnCreated() {

        CreateQuizRequest createQuizRequest = new CreateQuizRequest("Math Quiz", Collections.singletonList(
                new QuestionsRequest("Question Text", Collections.singletonList(new SelectionRequest("A", true)))
        ));

        when(quizRepository.save(any())).thenReturn(new QuizEntity());
        when(questionConverter.toQuestionEntity(any(), any())).thenReturn(new QuestionEntity());

        ResponseEntity<HttpStatus> response = quizService.createQuiz(createQuizRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(quizRepository, times(1)).save(any());
        verify(redisService, times(1)).evictQuizCache();
    }

    @Test
    public void createQuiz_withMultipleCorrectAnswers_shouldThrowErrorException() {

        CreateQuizRequest createQuizRequest = new CreateQuizRequest("History Quiz",Collections.singletonList(
                new QuestionsRequest("Question Text", Arrays.asList(
                        new SelectionRequest("A", true),
                        new SelectionRequest("B", true)
                ))
        ));

        assertThrows(TooManyCorrectAnswerException.class, () -> quizService.createQuiz(createQuizRequest));
        verify(quizRepository, never()).save(any());
        verify(redisService, never()).evictQuizCache();
    }
}
