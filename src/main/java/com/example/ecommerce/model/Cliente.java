package com.example.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.regex.Pattern;

@Entity
@Table(name = "clientes")
public class Cliente {

    private String nome;

    @Id
    private String cpf;

    private String email;

    // Construtor padrão para o JPA
    public Cliente() {}

    public Cliente(String nome, String cpf, String email) {
        if (!isCpfValido(cpf)) throw new IllegalArgumentException("CPF inválido.");
        if (!isEmailValido(email)) throw new IllegalArgumentException("Email inválido.");

        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    private boolean isCpfValido(String cpf) {
        return Pattern.matches("\\d{11}", cpf);
    }

    private boolean isEmailValido(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
