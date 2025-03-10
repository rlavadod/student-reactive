package com.encora.studentreactive.domain.port.in;

import com.encora.studentreactive.domain.model.Student;
import reactor.core.publisher.Mono;

public interface SaveStudent {
  Mono<Void> saveStudent(Student student);
}
