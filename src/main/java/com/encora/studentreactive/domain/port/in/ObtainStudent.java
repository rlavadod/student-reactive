package com.encora.studentreactive.domain.port.in;

import com.encora.studentreactive.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ObtainStudent {
  Flux<Student> obtainAll();
  Flux<Student> obtainAllByStatus(Boolean status);
  Mono<Boolean> existStudentById(Long id);
}
