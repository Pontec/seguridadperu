package com.utp.seguridadperu.controller.Advice;

import com.utp.seguridadperu.agregates.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseBase<String>> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        // Aquí construyes la respuesta de error para el caso de recursos no encontrados
        ResponseBase<String> response = new ResponseBase<>(404, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Manejo de IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseBase<String>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        // Aquí construyes la respuesta de error para el caso de argumentos inválidos
        ResponseBase<String> response = new ResponseBase<>(400, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejo general de cualquier otra excepción (por ejemplo, un error interno del servidor)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<String>> handleGlobalException(Exception ex, WebRequest request) {
        ResponseBase<String> response = new ResponseBase<>(500, "Error interno del servidor", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
