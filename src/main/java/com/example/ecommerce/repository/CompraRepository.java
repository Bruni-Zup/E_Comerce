package com.example.ecommerce.repository;

import com.example.ecommerce.model.Compra;
import com.example.ecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Corrigido para utilizar a entidade Cliente e o relacionamento corretamente
    List<Compra> findByCliente(Cliente cliente);
}
