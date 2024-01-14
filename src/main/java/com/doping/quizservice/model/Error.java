package com.doping.quizservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Error {
    public int code;
    private String message;
    private long timestamp;
}
