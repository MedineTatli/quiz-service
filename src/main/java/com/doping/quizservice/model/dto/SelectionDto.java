package com.doping.quizservice.model.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SelectionDto implements Serializable {

    public static final long serialVersionUID = -1L;

    private Long selectionId;
    private String selection;
    private Boolean isTrue;
}
