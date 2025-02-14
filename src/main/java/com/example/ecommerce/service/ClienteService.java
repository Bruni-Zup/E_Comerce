package com.example.ecommerce.service;

import com.example.ecommerce.exception.ClienteJaCadastradoException;
import com.example.ecommerce.exception.ClienteNaoEncontradoException;
import com.example.ecommerce.model.Cliente;
import com.example.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cadastrarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getCpf())) {
            throw new ClienteJaCadastradoException("CPF já cadastrado: " + cliente.getCpf());
        }
        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            throw new ClienteJaCadastradoException("Email já cadastrado: " + cliente.getEmail());
        }
        clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findById(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado: " + cpf));
    }
}
