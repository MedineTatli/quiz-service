package com.doping.quizservice.controller;

import com.doping.quizservice.model.dto.ResultDto;
import com.doping.quizservice.model.request.ResultRequest;
import com.doping.quizservice.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/exam")
public class ExamController {

    private final ExamResultService examResultService;

    @PostMapping
    public ResultDto createStudentsExam(@RequestBody ResultRequest resultRequest) {
        return examResultService.createResult(resultRequest);
    }
    @GetMapping
    public List<ResultDto> getExamResultsByStudentId(@RequestParam String studentNo){
        return examResultService.getExamResultsByStudentId(studentNo);
    }

}
