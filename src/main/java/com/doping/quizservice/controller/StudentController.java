package com.doping.quizservice.controller;

import com.doping.quizservice.model.dto.StudentDto;
import com.doping.quizservice.model.request.CreateStudentRequest;
import com.doping.quizservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<HttpStatus> createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping
    public List<StudentDto> getStudents() {
        return studentService.getStudents();
    }



}
