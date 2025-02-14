package com.example.ecommerce.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itens = new ArrayList<>();

    // Construtores
    public Compra() {}

    public Compra(Cliente cliente, List<ItemCompra> itens) {
        if (cliente == null || itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A compra deve ter um cliente e ao menos um item.");
        }
        this.cliente = cliente;
        this.itens = itens;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        this.cliente = cliente;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A compra deve ter ao menos um item.");
        }
        this.itens = itens;
    }

    // Método adicional para adicionar itens na compra
    public void adicionarItem(ItemCompra itemCompra) {
        if (itemCompra == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        itens.add(itemCompra);
    }

    // Método para calcular o total da compra
    public Double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemCompra::calcularSubtotal)
                .sum();
    }
}
