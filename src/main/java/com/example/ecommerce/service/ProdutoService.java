package com.example.ecommerce.service;

import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarProduto(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new IllegalArgumentException("Produto com nome '" + produto.getNome() + "' já existe.");
        }
        produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorNome(String nome) {
        Produto produto = produtoRepository.findByNome(nome);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado: " + nome);
        }
        return produto;
    }

    public boolean verificarDisponibilidade(Produto produto, int quantidade) {
        return produto.getQuantidade() >= quantidade;
    }

    public void atualizarEstoque(Produto produto, int quantidade) {
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoRepository.save(produto);
    }
}
