package com.example.ecommerce.service;

import com.example.ecommerce.model.Cliente;
import com.example.ecommerce.model.Compra;
import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {

    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final CompraRepository compraRepository;

    @Autowired
    public CompraService(ProdutoService produtoService, ClienteService clienteService, CompraRepository compraRepository) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.compraRepository = compraRepository;
    }

    public Compra realizarCompra(String cpfCliente, List<Produto> produtosComprados) {
        Cliente cliente = clienteService.buscarClientePorCpf(cpfCliente);
        List<Produto> produtosEmFalta = new ArrayList<>();

        // Para cada produto solicitado, assume-se que a quantidade desejada é 1,
        // a não ser que o JSON envie um valor diferente em 'quantidade'
        for (Produto produtoSolicitado : produtosComprados) {
            Produto produtoEstoque = produtoService.buscarProdutoPorNome(produtoSolicitado.getNome());
            // Define a quantidade desejada (pode ser customizada conforme o contrato do JSON)
            int quantidadeDesejada = produtoSolicitado.getQuantidade() > 0 ? produtoSolicitado.getQuantidade() : 1;
            if (produtoService.verificarDisponibilidade(produtoEstoque, quantidadeDesejada)) {
                produtoService.atualizarEstoque(produtoEstoque, quantidadeDesejada);
            } else {
                produtosEmFalta.add(produtoEstoque);
            }
        }

        if (!produtosEmFalta.isEmpty()) {
            String produtosEmFaltaNome = produtosEmFalta.stream()
                    .map(Produto::getNome)
                    .reduce((p1, p2) -> p1 + ", " + p2)
                    .orElse("");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Produto(s) em falta: " + produtosEmFaltaNome);
        }

        Compra compra = new Compra(cliente, produtosComprados);
        return compraRepository.save(compra);
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public List<Compra> listarComprasPorCliente(String cpf) {
        return compraRepository.findByClienteCpf(cpf);
    }
}
