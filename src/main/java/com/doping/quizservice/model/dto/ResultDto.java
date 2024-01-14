package com.doping.quizservice.model.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto implements Serializable {

    private String studentNo;
    private int questionCount;
    private Long rightAnswerCount;
    private Long wrongAnswerCount;
    private double score;

}
