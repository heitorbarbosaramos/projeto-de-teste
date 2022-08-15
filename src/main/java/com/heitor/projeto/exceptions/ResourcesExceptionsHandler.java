package com.heitor.projeto.exceptions;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

@ControllerAdvice
public class ResourcesExceptionsHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<MensagemPadrao> objectNotFound(NoSuchElementException e, HttpServletRequest request){
        MensagemPadrao error = new MensagemPadrao(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), e.getMessage(), System.currentTimeMillis(), LocalDateTime.now(), request.getRequestURI());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
	
	@ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity<MensagemPadrao> invalidDefinitionException(InvalidDefinitionException e, HttpServletRequest request){
        MensagemPadrao error = new MensagemPadrao(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(), System.currentTimeMillis(), LocalDateTime.now(), request.getRequestURI());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MensagemPadrao> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
        MensagemPadrao error = new MensagemPadrao(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(), System.currentTimeMillis(), LocalDateTime.now(), request.getRequestURI());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
