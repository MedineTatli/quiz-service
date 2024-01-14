package com.doping.quizservice.service;

import com.doping.quizservice.model.dto.StudentDto;
import com.doping.quizservice.model.request.CreateStudentRequest;
import com.doping.quizservice.persistance.converter.StudentConverter;
import com.doping.quizservice.persistance.entity.StudentEntity;
import com.doping.quizservice.persistance.repository.StudentRepository;
import com.doping.quizservice.persistance.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.doping.quizservice.contstant.CacheConstants.ALL_STUDENT_CACHE;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final RedisService redisService;

    public ResponseEntity<HttpStatus> createStudent(CreateStudentRequest createStudentRequest) {
        log.info("CreateStudentRequest is saving...");
        studentRepository.save(studentConverter.toStudentEntity(createStudentRequest));
        redisService.allStudentCacheEvict();
        log.info("CreateStudentRequest saved.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Cacheable(value = ALL_STUDENT_CACHE)
    public List<StudentDto> getStudents() {
        log.info("Get to StudentDto List");
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        log.info("StudentDto retrieved");
        return studentEntityList.stream().map(studentConverter::toStudentDto).toList();
    }
}
