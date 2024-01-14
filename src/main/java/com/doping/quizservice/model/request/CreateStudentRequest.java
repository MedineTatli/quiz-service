package com.doping.quizservice.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotEmpty(message = "name cannot be empty!")
    private String name;
    @NotEmpty(message = "surname cannot be empty!")
    private String surname;
}
