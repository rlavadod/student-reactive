package com.encora.studentreactive.util;

import com.encora.studentreactive.model.api.ErrorResponse;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  ErrorResponse handleGeneralException(Exception exception) {
    return ErrorResponse.builder()
        .code("unknown.".concat(exception.getClass().getSimpleName()))
        .message(exception.getMessage())
        .build();
  }

  @ExceptionHandler(ServiceException.class)
  ResponseEntity<ErrorResponse> handleServiceException(ServiceException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(ErrorResponse.builder()
            .code("service." + HttpStatus.CONFLICT.name())
            .message(exception.getMessage())
            .build());
  }

  @ExceptionHandler(WebExchangeBindException.class)
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  ErrorResponse handleWebExchangeBindException(WebExchangeBindException webe) {
    return ErrorResponse.builder()
        .code("input-fail.".concat(webe.getBindingResult().getObjectName()))
        .message(webe.getReason())
        .details(webe.getAllErrors().stream()
            .map(o -> String.format("%s: %s", Optional
                .ofNullable(o.getCodes()).map(c -> c[0])
                .orElse(o.getCode()), o.getDefaultMessage()))
            .collect(Collectors.toList()))
        .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
  ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
    return ErrorResponse.builder()
        .code("input-fail.".concat(manve.getBindingResult().getObjectName()))
        .message(manve.getMessage())
        .details(manve.getBindingResult().getAllErrors().stream()
            .map(o -> String.format("[%s]: %s", Optional.ofNullable(o.getCodes()).map(c -> c[0]).orElse(o.getCode()), o.getDefaultMessage()))
            .collect(Collectors.toList()))
        .build();
  }

  @ExceptionHandler(DataAccessException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorResponse handleDataAccessException(DataAccessException dive) {
    return ErrorResponse.builder()
        .code("data-access.".concat(dive.getMostSpecificCause().getClass().getSimpleName()))
        .message(dive.getMessage())
        .build();
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorResponse handleConstraintViolationException(ConstraintViolationException cve) {
    return ErrorResponse.builder()
        .code("constraint.")
        .message(cve.getMessage())
        .details(cve.getConstraintViolations().stream()
            .map(it -> String.format("%s<%s> (%s): %s", it.getPropertyPath().toString(), it.getConstraintDescriptor(), it.getInvalidValue().toString(), it.getMessage()))
            .collect(Collectors.toList()))
        .build();
  }

}
