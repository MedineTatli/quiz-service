package com.doping.quizservice.model.dto;



import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto implements Serializable {

    private String studentNumber;
    private String firstName;
    private String surname;

}
