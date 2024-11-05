package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.model.AuditoriaPrecoItem;
import com.meialuastore.meialuastore.model.Produto;
import com.meialuastore.meialuastore.repository.AuditoriaPrecoItemRepository;
import com.meialuastore.meialuastore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auditoria-preco")
public class AuditoriaPrecoItemController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AuditoriaPrecoItemRepository auditoriaPrecoItemRepository;

    @PutMapping("/{id_produto}/preco")
    public String atualizarPrecoProduto(@PathVariable Integer id_produto, @RequestParam Double novoPreco) {
        Produto produto = produtoRepository.findById(id_produto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setPreco(novoPreco);
        produtoRepository.save(produto); // Isso vai disparar o trigger

        return null;
    }
}
