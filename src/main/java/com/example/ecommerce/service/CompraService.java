package com.example.ecommerce.service;

import com.example.ecommerce.dto.CompraRequest;
import com.example.ecommerce.dto.ItemCompraRequest;
import com.example.ecommerce.model.Cliente;
import com.example.ecommerce.model.Compra;
import com.example.ecommerce.model.ItemCompra;
import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    // Método que recebe um CompraRequest e realiza a compra
    @Transactional
    public Compra realizarCompra(CompraRequest compraRequest) {
        // Buscar o cliente pelo CPF
        Cliente cliente = clienteService.buscarClientePorCpf(compraRequest.getCpf());

        List<ItemCompra> itensCompra = new ArrayList<>();
        List<Produto> produtosEmFalta = new ArrayList<>();

        // Iterar pelos itens da compra
        for (ItemCompraRequest itemRequest : compraRequest.getItens()) {
            Produto produto = produtoService.buscarProdutoPorId(itemRequest.getProdutoId());
            int quantidadeDesejada = itemRequest.getQuantidade();

            // Verifica se há disponibilidade no estoque
            if (produtoService.verificarDisponibilidade(produto, quantidadeDesejada)) {
                // Atualiza o estoque
                produtoService.atualizarEstoque(produto, quantidadeDesejada);

                // Cria o ItemCompra (a associação com a compra será feita em seguida)
                ItemCompra itemCompra = new ItemCompra(null, produto, quantidadeDesejada);
                itensCompra.add(itemCompra);
            } else {
                produtosEmFalta.add(produto);
            }
        }

        // Verifica se algum produto está em falta
        if (!produtosEmFalta.isEmpty()) {
            String produtosEmFaltaNome = produtosEmFalta.stream()
                    .map(Produto::getNome)
                    .reduce((p1, p2) -> p1 + ", " + p2)
                    .orElse("");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Produto(s) em falta: " + produtosEmFaltaNome);
        }

        // Criação da compra e associação dos itens
        Compra compra = new Compra(cliente, itensCompra);
        for (ItemCompra item : itensCompra) {
            item.setCompra(compra);
        }

        // Persiste a compra no banco de dados
        return compraRepository.save(compra);
    }

    // Método sobrecarregado para receber CPF e lista de IDs de produtos separadamente
    public Compra realizarCompra(String cpf, List<Long> produtoIds) {
        // Cria um objeto CompraRequest com quantidade padrão (1) para cada produto
        CompraRequest compraRequest = new CompraRequest();
        compraRequest.setCpf(cpf);
        List<ItemCompraRequest> itens = produtoIds.stream()
                .map(produtoId -> {
                    ItemCompraRequest item = new ItemCompraRequest();
                    item.setProdutoId(produtoId);
                    item.setQuantidade(1); // define quantidade padrão como 1
                    return item;
                })
                .collect(Collectors.toList());
        compraRequest.setItens(itens);
        return realizarCompra(compraRequest);
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public List<Compra> listarComprasPorCliente(String cpf) {
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        return compraRepository.findByCliente(cliente);
    }
}
