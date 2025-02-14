package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.ecommerce.exception.ClienteNaoEncontradoException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        // Cria uma resposta de erro
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        // Retorna a resposta com o código de status 404
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Outros métodos de tratamento de exceção podem ser adicionados aqui
}
