package com.doping.quizservicetest.service;

import com.doping.quizservice.model.dto.StudentDto;
import com.doping.quizservice.model.request.CreateStudentRequest;
import com.doping.quizservice.persistance.converter.StudentConverter;
import com.doping.quizservice.persistance.entity.StudentEntity;
import com.doping.quizservice.persistance.repository.StudentRepository;
import com.doping.quizservice.persistance.service.RedisService;
import com.doping.quizservice.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentConverter studentConverter;

    @Mock
    private RedisService redisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStudent_shouldReturnHttpStatusCreated() {
        CreateStudentRequest request = new CreateStudentRequest("name", "surname");
        StudentEntity studentEntity = new StudentEntity();
        when(studentConverter.toStudentEntity(request)).thenReturn(studentEntity);

        ResponseEntity<HttpStatus> response = studentService.createStudent(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(studentRepository, times(1)).save(studentEntity);
        verify(redisService, times(1)).allStudentCacheEvict();
    }

    @Test
    void getStudents_shouldReturnStudentDtoList() {
        List<StudentEntity> studentEntities = Arrays.asList(
                new StudentEntity(1L, "name", "surname", null, null),
                new StudentEntity(2L, "test", "test", null, null)
        );
        when(studentRepository.findAll()).thenReturn(studentEntities);

        List<StudentDto> expectedStudentDtos = Arrays.asList(
                new StudentDto("1L", "test", "medine"),
                new StudentDto("2L", "eren", "eren")
        );

        when(studentConverter.toStudentDtoList(studentEntities)).thenReturn(expectedStudentDtos);

        List<StudentDto> actualStudentDtos = studentService.getStudents();

        assertNotNull(actualStudentDtos);
        assertEquals(expectedStudentDtos.size(), actualStudentDtos.size());
        verify(studentRepository, times(1)).findAll();
    }

}
