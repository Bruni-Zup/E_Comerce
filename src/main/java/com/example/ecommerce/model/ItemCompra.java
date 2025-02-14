package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    // Construtor padrão para o JPA
    public ItemCompra() {}

    // Construtor com argumentos
    public ItemCompra(Compra compra, Produto produto, Integer quantidade) {
        this.compra = compra;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Calcula o subtotal do item da compra multiplicando o preço do produto pela quantidade.
     *
     * @return subtotal do item
     */
    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }
}
