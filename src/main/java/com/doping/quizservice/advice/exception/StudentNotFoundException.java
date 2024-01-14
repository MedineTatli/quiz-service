package com.doping.quizservice.advice.exception;

import com.doping.quizservice.advice.constant.ErrorCodes;
import lombok.Getter;

@Getter
public class StudentNotFoundException extends  RuntimeException{

    private final int code;

    public StudentNotFoundException(){
        super("Student not found exception.");
        this.code = ErrorCodes.STUDENT_NOT_FOUND;
    }
}
