package com.example.ecommerce.exception;

/**
 * Exceção lançada quando um cliente não é encontrado na base de dados.
 */
public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ClienteNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
