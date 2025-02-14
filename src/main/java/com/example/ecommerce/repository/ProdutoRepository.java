package com.example.ecommerce.repository;

import com.example.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Pesquisa por nome de produto, sensível a maiúsculas e minúsculas
    Produto findByNome(String nome);

    // Verifica se já existe um produto com o mesmo nome
    boolean existsByNome(String nome);

    // Método alternativo que pode ser útil para busca que não seja sensível a maiúsculas/minúsculas
    Optional<Produto> findByNomeIgnoreCase(String nome);
}
