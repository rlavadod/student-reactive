package com.encora.studentreactive.application.services;

import com.encora.studentreactive.domain.model.Student;
import com.encora.studentreactive.domain.port.in.ObtainStudent;
import com.encora.studentreactive.domain.port.in.SaveStudent;
import com.encora.studentreactive.domain.port.out.StudentDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService implements ObtainStudent, SaveStudent {

  @Autowired
  private StudentDatabase studentDatabase;

  @Override
  public Flux<Student> obtainAll() {
    return studentDatabase.findAll();
  }

  @Override
  public Flux<Student> obtainAllByStatus(Boolean status) {
    return studentDatabase.findAllByStatus(status);
  }

  @Override
  public Mono<Boolean> existStudentById(Long id) {
    return studentDatabase.existStudentById(id);
  }

  @Override
  public Mono<Void> saveStudent(Student student) {
    return studentDatabase.save(student);
  }
}
