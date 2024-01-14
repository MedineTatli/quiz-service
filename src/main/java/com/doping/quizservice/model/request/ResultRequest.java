package com.doping.quizservice.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ResultRequest {

    @NotEmpty(message = "studentNo cannot be empty!")
    private String studentNo;
    @NotEmpty(message = "quizNo cannot be empty!")
    private Long quizNo;
    @NotEmpty(message = "questionAndAnswers cannot be empty!")
    private Map<Long, Long> questionAndAnswers;
}
