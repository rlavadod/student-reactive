package com.encora.studentreactive.domain.port.out;

import com.encora.studentreactive.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentDatabase {
  Mono<Void> save(Student student);
  Mono<Boolean> existStudentById(Long id);
  Flux<Student> findAll();
  Flux<Student> findAllByStatus(Boolean status);
}
