package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.ProdutoRequestDTO;
import com.meialuastore.meialuastore.model.Produto;
import com.meialuastore.meialuastore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = repository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não foi encontrado"));
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Produto produto = new Produto();
        produto.setNome_produto(dto.nome());

        Produto savedProduto = repository.save(produto);
        return ResponseEntity.status(201).body(savedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não foi encontrado"));

        repository.delete(produto);
        return ResponseEntity.noContent().build();
    }
}
