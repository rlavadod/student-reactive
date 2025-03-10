package com.encora.studentreactive.controller;

import com.encora.studentreactive.model.api.Student;
import com.encora.studentreactive.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(StudentController.class)
class StudentControllerTest {

  @MockBean
  private StudentService studentService;

  @Autowired
  private WebTestClient webClient;

  @BeforeEach
  void setUp() {
  }

  @Test
  void testSaveStudentWhenIsSuccessful() {
    //Arrange
    Student student = Student.builder()
        .firstname("Nombre")
        .lastname("Apellido")
        .status(Boolean.TRUE)
        .age(32)
        .build();
    Mockito.when(studentService.saveStudent(Mockito.any(Student.class)))
        .thenReturn(Mono.empty());
    //Act
    //Assert
    webClient.post().uri("/api/v1/student/")
        .bodyValue(student)
        .exchange()
        .expectStatus()
        .isCreated();
  }

  @Test
  void testSaveStudentWhenIdIsSentAndExistStudent() {
    //Arrange
    Student student = Student.builder()
        .id(1L)
        .firstname("Nombre")
        .lastname("Apellido")
        .status(Boolean.TRUE)
        .age(32)
        .build();
    Mockito.when(studentService.existStudentById(Mockito.anyLong()))
        .thenReturn(Mono.just(Boolean.TRUE));
    //Act
    //Assert
    webClient.post().uri("/api/v1/student/")
        .bodyValue(student)
        .exchange()
        .expectStatus()
        .is4xxClientError();
  }

  @Test
  void testSaveStudentWhenIdIsSentAndNotExistStudent() {
    //Arrange
    Student student = Student.builder()
        .id(1L)
        .firstname("Nombre")
        .lastname("Apellido")
        .status(Boolean.TRUE)
        .age(32)
        .build();
    Mockito.when(studentService.existStudentById(Mockito.anyLong()))
        .thenReturn(Mono.just(Boolean.FALSE));
    //Act
    //Assert
    webClient.post().uri("/api/v1/student/")
        .bodyValue(student)
        .exchange()
        .expectStatus()
        .is4xxClientError();
  }

  @Test
  void testGetAllStudentsWhenIsSuccessful() {
    //Arrange
    Student student = Student.builder()
        .firstname("Nombre")
        .lastname("Apellido")
        .status(Boolean.TRUE)
        .age(32)
        .build();
    Mockito.when(studentService.getAllStudents())
        .thenReturn(Flux.just(student));
    //Act
    //Assert
    webClient.get().uri("/api/v1/student/")
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  void testGetAllStudentsWhenStatusIsTrue() {
    //Arrange
    Student student = Student.builder()
        .firstname("Nombre")
        .lastname("Apellido")
        .status(Boolean.TRUE)
        .age(32)
        .build();
    Mockito.when(studentService.getAllStudentsByStatus(Mockito.anyBoolean()))
        .thenReturn(Flux.just(student));
    //Act
    //Assert
    webClient.get().uri("/api/v1/student/?status=true")
        .exchange()
        .expectStatus()
        .isOk();
  }
}