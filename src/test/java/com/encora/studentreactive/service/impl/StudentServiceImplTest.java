package com.encora.studentreactive.service.impl;

import com.encora.studentreactive.model.api.Student;
import com.encora.studentreactive.model.entity.StudentEntity;
import com.encora.studentreactive.repository.StudentRepository;
import com.encora.studentreactive.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

  @Mock
  private StudentRepository repository;

  @InjectMocks
  private StudentServiceImpl service;

  @Test
  void testSaveStudentWhenIsSuccessful() {
    //Arrange
    Student student = Student.builder()
        .id(1L)
        .firstname("Nombre")
        .lastname("Apellido")
        .build();
    Mockito.when(repository.save(Mockito.any(StudentEntity.class)))
        .thenReturn(Mono.just(student.toEntity()));
    //Act && Assert
    StepVerifier.create(service.saveStudent(student))
        .verifyComplete();
  }

  @Test
  void testExistStudentByIdWhenStudentExist() {
    //Arrange
    Student student = Student.builder()
        .id(1L)
        .firstname("Nombre")
        .lastname("Apellido")
        .build();
    Mockito.when(repository.existsById(Mockito.anyLong()))
        .thenReturn(Mono.just(Boolean.TRUE));
    //Act && Assert
    StepVerifier.create(service.existStudentById(student.getId()))
        .expectNext(Boolean.TRUE)
        .verifyComplete();
  }

  @Test
  void testGetAllStudentsWhenIsSuccessful() {
    //Arrange
    Student student = Student.builder()
        .id(1L)
        .firstname("Nombre")
        .lastname("Apellido")
        .build();
    Mockito.when(repository.findAll())
        .thenReturn(Flux.just(student.toEntity()));
    //Act && Assert
    StepVerifier.create(service.getAllStudents())
        .expectNextMatches(item -> student.getId().equals(item.getId()))
        .verifyComplete();
  }

  @Test
  void testGetAllStudentsByStatusWhenStatusIsTrue() {
    //Arrange
    Student student = Student.builder()
        .id(1L)
        .firstname("Nombre")
        .lastname("Apellido")
        .status(Boolean.TRUE)
        .build();
    Mockito.when(repository.findAllByStatus(Mockito.anyBoolean()))
        .thenReturn(Flux.just(student.toEntity()));
    //Act && Assert
    StepVerifier.create(service.getAllStudentsByStatus(Boolean.TRUE))
        .expectNextMatches(item -> student.getStatus().equals(item.getStatus()))
        .verifyComplete();
  }
}