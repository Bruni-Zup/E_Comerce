package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cliente;
import com.example.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public void cadastrarCliente(@RequestBody Cliente cliente) {
        clienteService.cadastrarCliente(cliente);
    }

    @GetMapping("/{cpf}")
    public Cliente obterCliente(@PathVariable String cpf) {
        return clienteService.buscarClientePorCpf(cpf);
    }
}
