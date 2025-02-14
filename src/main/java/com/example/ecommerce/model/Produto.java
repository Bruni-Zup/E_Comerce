package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private double preco;

    private int quantidade;

    // Construtor padrão para o JPA
    public Produto() {}

    public Produto(String nome, double preco, int quantidade) {
        if (preco <= 0) throw new IllegalArgumentException("Preço deve ser maior que 0.");
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Método para decrementar a quantidade do estoque.
     *
     * @param quantidade Compra que será realizada.
     * @throws IllegalArgumentException Caso o estoque seja insuficiente.
     */
    public void decrementarEstoque(int quantidade) {
        if (quantidade > this.quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: " + nome);
        }
        this.quantidade -= quantidade;  // Reduz o estoque do produto
    }
}
