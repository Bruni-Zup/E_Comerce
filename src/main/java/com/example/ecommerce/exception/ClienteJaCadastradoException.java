package com.example.ecommerce.exception;

/**
 * Exceção lançada quando se tenta cadastrar um cliente que já está cadastrado.
 */
public class ClienteJaCadastradoException extends RuntimeException {
    public ClienteJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
