package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = this.repository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public Produto findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Produto não foi encontrado"));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody MovieRequestDTO dto) {
        if (dto.nome().isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Produto movie = new Produto();
        movie.setNome(dto.nome());

        this.repository.save(movie);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Produto produto = this.repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Filme não foi encontrado"));

        this.repository.delete(produto);
        return ResponseEntity.noContent().build();
    }
}
