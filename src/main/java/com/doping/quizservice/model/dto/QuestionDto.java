package com.doping.quizservice.model.dto;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto implements Serializable {

    public static final long serialVersionUID = -1L;

    private Long questionId;
    private String questionText;
    private List<SelectionDto> selections;

}
