package com.encora.studentreactive.service.impl;

import com.encora.studentreactive.model.api.Student;
import com.encora.studentreactive.repository.StudentRepository;
import com.encora.studentreactive.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StudenServiceImpl implements StudentService {

  @Autowired
  private StudentRepository repository;

  @Override
  public Mono<Void> saveStudent(Student student) {
    return repository.save(student.toEntity())
        .then();
  }

  @Override
  public Mono<Boolean> existStudentById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public Flux<Student> getAllStudents() {
    return repository.findAll()
        .map(Student::from);
  }

  @Override
  public Flux<Student> getAllStudentsByStatus(Boolean status) {
    return repository.findAllByStatus(status)
        .map(Student::from);
  }
}
