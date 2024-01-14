package com.doping.quizservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuizResultResponse {

    private Long quizNo;
    private Long studentNo;
    private Integer trueAnswerCount;
}
