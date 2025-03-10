package com.encora.studentreactive.infrastructure.repository;

import com.encora.studentreactive.infrastructure.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<StudentEntity, Long> {
  Flux <StudentEntity> findAllByStatus(Boolean status);
}
