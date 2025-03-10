package com.encora.studentreactive.domain.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Student {

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

}
