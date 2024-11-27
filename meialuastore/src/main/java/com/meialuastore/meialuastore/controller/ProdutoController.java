package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.ProdutoRequestDTO;
import com.meialuastore.meialuastore.model.Produto;
import com.meialuastore.meialuastore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired /*injeção automática de dependências*/
    private ProdutoRepository repository;

    /*metodo de trazer todas as informações*/
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = repository.findAll();
        return ResponseEntity.ok(produtos);
    }

    /*trazer informações passando um parametro de busca*/
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não foi encontrado"));
        return ResponseEntity.ok(produto);
    }

    /*metodo para postar um novo produto*/
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoRequestDTO dto) {
        try {
            if (dto.getNome() == null || dto.getNome().isEmpty() ||
                    dto.getDescricao() == null || dto.getDescricao().isEmpty() ||
                    dto.getPreco() == null || dto.getPreco() <= 0 ||
                    dto.getCategoria() == null || dto.getCategoria().isEmpty()) {

                return ResponseEntity.badRequest().build();
            }

            Produto produto = new Produto();
            produto.setNome_produto(dto.getNome());
            produto.setDescricao_produto(dto.getDescricao());
            produto.setPreco(dto.getPreco());
            produto.setCategoria(dto.getCategoria());

            Produto savedProduto = repository.save(produto);
            return ResponseEntity.status(201).body(savedProduto);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /*metodo de deletar informação passando um parametro*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não foi encontrado"));

        this.repository.delete(produto);
        return ResponseEntity.noContent().build();
    }

    /*metodo de atualizar o preço passando o id do produto e o novo preços*/
    @PutMapping("/{id_produto}/preco")
    public String atualizarPrecoProduto(@PathVariable Integer id_produto, @RequestParam Double novoPreco) {
        Produto produto = repository.findById(id_produto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setPreco(novoPreco);
        repository.save(produto); // ativa o trigger do banco

        return null;
    }
}
