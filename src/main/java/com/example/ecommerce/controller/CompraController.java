package com.example.ecommerce.controller;

import com.example.ecommerce.model.Compra;
import com.example.ecommerce.model.Produto;
import com.example.ecommerce.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public Compra realizarCompra(@RequestParam String cpf, @RequestBody List<Produto> produtos) {
        return compraService.realizarCompra(cpf, produtos);
    }

    @GetMapping
    public List<Compra> listarCompras() {
        return compraService.listarCompras();
    }

    @GetMapping("/{cpf}")
    public List<Compra> listarComprasPorCliente(@PathVariable String cpf) {
        return compraService.listarComprasPorCliente(cpf);
    }
}
