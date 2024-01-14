package com.doping.quizservice.persistance.converter;

import com.doping.quizservice.model.dto.StudentDto;
import com.doping.quizservice.model.request.CreateStudentRequest;
import com.doping.quizservice.persistance.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentConverter {


    public StudentEntity toStudentEntity(CreateStudentRequest createStudentRequest) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentNumber(createStudentId());
        studentEntity.setFirstName(createStudentRequest.getName());
        studentEntity.setSurname(createStudentRequest.getSurname());
        return studentEntity;
    }

    private String createStudentId() {
        return UUID.randomUUID().toString();
    }

    public StudentDto toStudentDto(StudentEntity studentEntity) {
        return StudentDto.builder()
                .studentNumber(studentEntity.getStudentNumber())
                .firstName(studentEntity.getFirstName())
                .surname(studentEntity.getSurname())
                .build();
    }

    public List<StudentDto> toStudentDtoList(List<StudentEntity> studentEntities) {
        return studentEntities.stream().map(this::toStudentDto).collect(Collectors.toList());

    }
}
