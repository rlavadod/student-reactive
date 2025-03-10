package com.encora.studentreactive.infrastructure.controller;

import java.util.Optional;

import com.encora.studentreactive.application.services.StudentService;
import com.encora.studentreactive.domain.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
public class StudentController {

  @Autowired
  private StudentService studentService;

  @PostMapping(value = "/")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> saveStudent(@RequestBody @Valid Student student) {
    return Mono.just(student)
        .flatMap(item -> {
          if (item.getId() != null) {
            return studentService.existStudentById(item.getId())
                .flatMap(flag -> {
                  if (flag) {
                    return Mono.error(new Exception("Ya fue registrado"));
                  }
                  return Mono.error(new Exception("No debe incluir el ID"));
                });
          }
          return Mono.just(item);
        })
        .flatMap(item -> studentService.saveStudent(item));
  }

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Student> getAllStudents(@RequestParam("status") Optional<Boolean> status) {
    if (status.isPresent()) {
      return studentService.obtainAllByStatus(status.get());
    }
    return studentService.obtainAll();
  }

}
