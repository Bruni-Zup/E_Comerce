package com.example.ecommerce.controller;

import com.example.ecommerce.model.Compra;
import com.example.ecommerce.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    // Realizar uma compra - Recebe CPF e uma lista de IDs de produtos
    @PostMapping
    public Compra realizarCompra(@RequestParam String cpf, @RequestBody List<Long> produtoIds) {
        try {
            return compraService.realizarCompra(cpf, produtoIds);
        } catch (Exception e) {
            // Retorna um erro 400 caso algum produto esteja em falta ou outra exceção ocorra
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Listar todas as compras
    @GetMapping
    public List<Compra> listarCompras() {
        return compraService.listarCompras();
    }

    // Listar compras por cliente - Recebe o CPF como parâmetro
    @GetMapping("/{cpf}")
    public List<Compra> listarComprasPorCliente(@PathVariable String cpf) {
        return compraService.listarComprasPorCliente(cpf);
    }
}
