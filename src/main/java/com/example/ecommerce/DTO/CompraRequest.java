package com.example.ecommerce.dto;

import java.util.List;

public class CompraRequest {

    private String cpf;  // Alterado de clienteId para cpf
    private List<ItemCompraRequest> itens;

    // Getters and Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ItemCompraRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraRequest> itens) {
        this.itens = itens;
    }
}
