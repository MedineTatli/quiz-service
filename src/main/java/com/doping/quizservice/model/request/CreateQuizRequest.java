package com.doping.quizservice.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateQuizRequest {

    @NotEmpty(message = "quizName cannot be empty!")
    private String quizName;
    @NotEmpty(message = "questionsList cannot be empty!")
    private List<QuestionsRequest> questionsList;
}
