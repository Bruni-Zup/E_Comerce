package com.example.ecommerce.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    // Construtores para personalizar a exceção
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
