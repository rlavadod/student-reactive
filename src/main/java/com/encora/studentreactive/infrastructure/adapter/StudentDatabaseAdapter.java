package com.encora.studentreactive.infrastructure.adapter;

import com.encora.studentreactive.domain.model.Student;
import com.encora.studentreactive.domain.port.out.StudentDatabase;
import com.encora.studentreactive.infrastructure.repository.StudentRepository;
import com.encora.studentreactive.infrastructure.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentDatabaseAdapter implements StudentDatabase {

  @Autowired
  private StudentRepository repository;

  @Override
  public Mono<Void> save(Student student) {
    return repository.save(StudentMapper.toEntity(student))
        .then();
  }

  @Override
  public Mono<Boolean> existStudentById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public Flux<Student> findAll() {
    return repository.findAll()
        .map(StudentMapper::from);
  }

  @Override
  public Flux<Student> findAllByStatus(Boolean status) {
    return repository.findAllByStatus(status)
        .map(StudentMapper::from);
  }
}
