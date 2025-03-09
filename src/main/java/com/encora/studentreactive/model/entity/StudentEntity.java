package com.encora.studentreactive.model.entity;

import javax.persistence.GeneratedValue;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@Table(name = "alumno")
public class StudentEntity implements Persistable<Long> {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String firstname;
  @Column
  private String lastname;
  @Column
  private Boolean status;
  @Column
  private Integer age;

  @Override
  @Transient
  public boolean isNew() {
    return id == null;
  }
}
