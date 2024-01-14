package com.doping.quizservice.model.dto;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuizDto implements Serializable {

    public static final long serialVersionUID = -1L;

    private Long quizId;
    private String quizName;
    private List<QuestionDto> questions;

}
