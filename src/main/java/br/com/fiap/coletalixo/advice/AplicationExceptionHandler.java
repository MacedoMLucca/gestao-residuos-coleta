package br.com.fiap.coletalixo.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error){

        Map<String, String> mapaDeErro = new HashMap<>();
        List<FieldError> campos = error.getBindingResult().getFieldErrors();

        for(FieldError campo : campos){
            mapaDeErro.put(campo.getField(), campo.getDefaultMessage());
        }

        return mapaDeErro;

    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegrityViolation(DataIntegrityViolationException error){
        
        Map<String, String> mapaDeErro = new HashMap<>();
        mapaDeErro.put("erro","Usuário já cadastrado");
        return mapaDeErro;
        
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException error) {
        Map<String, String> mapaDeErro = new HashMap<>();
        mapaDeErro.put("erro", error.getMessage());
        return mapaDeErro;
    }
}
