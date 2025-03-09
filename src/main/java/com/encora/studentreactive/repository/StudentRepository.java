package com.encora.studentreactive.repository;

import com.encora.studentreactive.model.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<StudentEntity, Long> {
  Flux <StudentEntity> findAllByStatus(Boolean status);
}
