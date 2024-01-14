package com.doping.quizservice.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SelectionRequest {

    @NotEmpty(message = "selection cannot be empty!")
    private String selection;
    @NotEmpty(message = "isTrue cannot be empty!")
    private boolean isTrue;
}
