package com.doping.quizservice.advice.exception;

import com.doping.quizservice.advice.constant.ErrorCodes;
import lombok.Getter;

@Getter
public class TooManyCorrectAnswerException extends  RuntimeException{

    private final int code;

    public TooManyCorrectAnswerException(){
        super("Too many correct answer exception");
        this.code = ErrorCodes.TOO_MANY_CORRECT_ANSWER;
    }
}
