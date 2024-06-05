package com.github.daniellimadev.apitransacao.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratandoExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(com.fasterxml.jackson.core.JsonParseException.class)
    public ResponseEntity<Object> handleException(com.fasterxml.jackson.core.JsonParseException ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerException(NullPointerException ex) {
        return ResponseEntity.badRequest().build();
    }
}
