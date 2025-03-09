package com.encora.studentreactive.service;

import com.encora.studentreactive.model.api.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
  Mono<Void> saveStudent(Student student);
  Mono<Boolean> existStudentById(Long id);
  Flux<Student> getAllStudents();
  Flux<Student> getAllStudentsByStatus(Boolean status);
}
