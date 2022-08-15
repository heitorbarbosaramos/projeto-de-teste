package com.heitor.projeto.exceptions;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourcesExceptionsHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<MensagemPadrao> objectNotFound(NoSuchElementException e, HttpServletRequest request){
        MensagemPadrao error = new MensagemPadrao(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), e.getMessage(), System.currentTimeMillis(), LocalDateTime.now(), request.getRequestURI());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
