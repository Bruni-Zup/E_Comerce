package com.example.ecommerce.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItemCompraRequest {

    @NotNull(message = "ID do produto não pode ser nulo")
    private Long produtoId;

    @Min(value = 1, message = "Quantidade mínima é 1")
    private Integer quantidade;

    // Getters and Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
