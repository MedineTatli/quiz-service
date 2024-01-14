package com.doping.quizservice.controller;

import com.doping.quizservice.model.dto.QuizDto;
import com.doping.quizservice.model.request.CreateQuizRequest;
import com.doping.quizservice.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<HttpStatus> createQuiz(@RequestBody CreateQuizRequest createQuizRequest) {
        return quizService.createQuiz(createQuizRequest);
    }

    @GetMapping
    public List<QuizDto> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/by-id")
    public QuizDto getQuizById(@RequestParam Long quizId) {
        return quizService.getQuizById(quizId);
    }
}
