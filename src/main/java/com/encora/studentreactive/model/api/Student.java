package com.encora.studentreactive.model.api;

import com.encora.studentreactive.model.entity.StudentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
public class Student {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Long id;
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z]+")
  private String firstname;
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z]+")
  private String lastname;
  private Boolean status;
  @Min(0)
  @Max(130)
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
