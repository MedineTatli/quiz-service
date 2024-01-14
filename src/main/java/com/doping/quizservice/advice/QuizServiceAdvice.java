package com.doping.quizservice.advice;

import com.doping.quizservice.advice.exception.QuizNotFoundException;
import com.doping.quizservice.advice.exception.StudentNotFoundException;
import com.doping.quizservice.advice.exception.TooManyCorrectAnswerException;
import com.doping.quizservice.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class QuizServiceAdvice {

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<Error> handleException(QuizNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(constructError(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Error> handleException(StudentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(constructError(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(TooManyCorrectAnswerException.class)
    public ResponseEntity<Error> handleException(TooManyCorrectAnswerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(constructError(e.getCode(), e.getMessage()));
    }

    private Error constructError(final int code, final String message){
        return Error.builder()
                .code(code)
                .message(message)
                .timestamp(new DateTime().getMillis())
                .build();
    }
}
