package com.example.ecommerce.controller;

import com.example.ecommerce.model.Produto;
import com.example.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public void cadastrarProduto(@RequestBody Produto produto) {
        produtoService.cadastrarProduto(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarTodosProdutos();
    }
}
