package com.doping.quizservice.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionsRequest {

    @NotEmpty(message = "question cannot be empty!")
    private String question;
    @NotEmpty(message = "selections cannot be empty!")
    private List<SelectionRequest> selections;
}
