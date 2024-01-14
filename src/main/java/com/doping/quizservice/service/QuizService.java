package com.doping.quizservice.service;

import com.doping.quizservice.advice.exception.QuizNotFoundException;
import com.doping.quizservice.advice.exception.TooManyCorrectAnswerException;
import com.doping.quizservice.model.dto.QuizDto;
import com.doping.quizservice.model.request.CreateQuizRequest;
import com.doping.quizservice.model.request.SelectionRequest;
import com.doping.quizservice.persistance.converter.QuestionConverter;
import com.doping.quizservice.persistance.converter.QuizConverter;
import com.doping.quizservice.persistance.entity.QuestionEntity;
import com.doping.quizservice.persistance.entity.QuizEntity;
import com.doping.quizservice.persistance.repository.QuizRepository;
import com.doping.quizservice.persistance.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.doping.quizservice.contstant.CacheConstants.ALL_QUIZ_CACHE;
import static com.doping.quizservice.contstant.CacheConstants.QUIZ_CACHE_BY_ID;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuestionConverter questionConverter;
    private final QuizConverter quizConverter;
    private final QuizRepository quizRepository;
    private final RedisService redisService;

    public ResponseEntity<HttpStatus> createQuiz(CreateQuizRequest createQuizRequest) {
        createQuizRequest.getQuestionsList().forEach(questionsRequest -> {
            if (questionsRequest.getSelections().stream().filter(SelectionRequest::isTrue).count() != 1) {
                throw new TooManyCorrectAnswerException();
            }
        });

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setQuizName(createQuizRequest.getQuizName());
        List<QuestionEntity> questionEntities = createQuizRequest.getQuestionsList()
                .stream()
                .map(question -> questionConverter.toQuestionEntity(question, quizEntity))
                .toList();
        quizEntity.setQuestions(questionEntities);

        quizRepository.save(quizEntity);
        log.info("Quiz saved: {}", createQuizRequest.getQuizName());
        redisService.evictQuizCache();
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @Cacheable(value = ALL_QUIZ_CACHE)
    public List<QuizDto> getAllQuizzes() {
        List<QuizEntity> quizEntityList = quizRepository.findAll();
        return quizEntityList.stream().map(quizConverter::toQuizDto).toList();

    }

    @Cacheable(value = QUIZ_CACHE_BY_ID, key = "#quizId")
    public QuizDto getQuizById(Long quizId) {
        Optional<QuizEntity> quizEntity = quizRepository.findById(quizId);
        if (quizEntity.isEmpty()) {
            log.error("Quiz not found : {}", quizId);
            throw new QuizNotFoundException();
        }
        return quizConverter.toQuizDto(quizEntity.get());
    }
}
