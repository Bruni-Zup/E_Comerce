package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    // Busca um cliente pelo seu email
    Cliente findByEmail(String email);

    // Verifica se já existe um cliente com o mesmo email
    boolean existsByEmail(String email);
}
