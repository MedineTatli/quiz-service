package com.doping.quizservice.advice.exception;

import com.doping.quizservice.advice.constant.ErrorCodes;
import lombok.Getter;

@Getter
public class QuizNotFoundException extends  RuntimeException{

    private final int code;

    public QuizNotFoundException(){
        super("Quiz not found exception.");
        this.code = ErrorCodes.QUIZ_NOT_FOUND;
    }
}
