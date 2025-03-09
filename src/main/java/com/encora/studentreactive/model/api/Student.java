package com.encora.studentreactive.model.api;

import com.encora.studentreactive.model.entity.StudentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Student {
  private Long id;
  private String firstname;
  private String lastname;
  private Boolean status;
  private Integer age;

  public static Student from(StudentEntity entity) {
    return Student.builder()
        .id(entity.getId())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .status(entity.getStatus())
        .age(entity.getAge())
        .build();
  }

  public StudentEntity toEntity() {
    return StudentEntity.builder()
        .id(id)
        .firstname(firstname)
        .lastname(lastname)
        .status(status)
        .age(age)
        .build();
  }
}
