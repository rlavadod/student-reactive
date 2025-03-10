package com.encora.studentreactive.infrastructure.mapper;


import com.encora.studentreactive.domain.model.Student;
import com.encora.studentreactive.infrastructure.entity.StudentEntity;

public class StudentMapper {

  public static Student from(StudentEntity entity) {
    return Student.builder()
        .id(entity.getId())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .status(entity.getStatus())
        .age(entity.getAge())
        .build();
  }

  public static StudentEntity toEntity(Student student) {
    return StudentEntity.builder()
        .id(student.getId())
        .firstname(student.getFirstname())
        .lastname(student.getLastname())
        .status(student.getStatus())
        .age(student.getAge())
        .build();
  }
}
