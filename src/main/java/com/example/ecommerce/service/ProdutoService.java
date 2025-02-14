package com.example.ecommerce.service;

import com.example.ecommerce.exception.ProdutoJaCadastradoException;  // Exceção personalizada
import com.example.ecommerce.exception.EstoqueInsuficienteException;    // Exceção personalizada
import com.example.ecommerce.exception.ProdutoNaoEncontradoException;    // Exceção personalizada
import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Método para cadastrar um novo produto, verifica se já existe
    public void cadastrarProduto(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new ProdutoJaCadastradoException("Produto com nome '" + produto.getNome() + "' já existe.");
        }
        produtoRepository.save(produto);
    }

    // Listar todos os produtos com paginação
    public Page<Produto> listarProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    // Buscar produto por ID
    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado: ID " + id));
    }

    // Buscar produto por nome (case-insensitive)
    public Produto buscarProdutoPorNome(String nome) {
        return produtoRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado: Nome " + nome));
    }

    // Verificar disponibilidade de um produto no estoque
    public boolean verificarDisponibilidade(Produto produto, int quantidade) {
        return produto.getQuantidade() >= quantidade;
    }

    // Atualizar o estoque de um produto
    @Transactional
    public void atualizarEstoque(Produto produto, int quantidade) {
        if (quantidade > produto.getQuantidade()) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome());
        }
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoRepository.save(produto);
    }

    // Listar todos os produtos sem paginação
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }
}
