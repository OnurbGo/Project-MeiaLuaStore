package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.ProdutoPedidoRequestDTO;
import com.meialuastore.meialuastore.model.ProdutoPedido;
import com.meialuastore.meialuastore.repository.ProdutoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produto-pedido")
public class ProdutoPedidoController {

    @Autowired
    private ProdutoPedidoRepository repository;

    @GetMapping
    public ResponseEntity<List<ProdutoPedidoRequestDTO>> findAll() {
        List<ProdutoPedidoRequestDTO> produtosPedidos = repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtosPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoPedidoRequestDTO> findById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(produtoPedido -> ResponseEntity.ok(convertToDTO(produtoPedido)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoPedidoRequestDTO> save(@RequestBody ProdutoPedidoRequestDTO produtoPedidoRequestDTO) {
        ProdutoPedido produtoPedido = convertToEntity(produtoPedidoRequestDTO);
        ProdutoPedido savedProdutoPedido = repository.save(produtoPedido);
        return ResponseEntity.ok(convertToDTO(savedProdutoPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(produtoPedido -> {
                    repository.delete(produtoPedido);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Métodos de conversão
    private ProdutoPedidoRequestDTO convertToDTO(ProdutoPedido produtoPedido) {
        ProdutoPedidoRequestDTO dto = new ProdutoPedidoRequestDTO();
        dto.setId_produto_pedido(produtoPedido.getId_produto_pedido());
        dto.setId_pedido(produtoPedido.getId_pedido());
        dto.setId_produto(produtoPedido.getId_produto());
        dto.setQuantidade(produtoPedido.getQuantidade());
        return dto;
    }

    private ProdutoPedido convertToEntity(ProdutoPedidoRequestDTO dto) {
        ProdutoPedido produtoPedido = new ProdutoPedido();
        produtoPedido.setId_produto_pedido(dto.getId_produto_pedido());
        produtoPedido.setId_pedido(dto.getId_pedido());
        produtoPedido.setId_produto(dto.getId_produto());
        produtoPedido.setQuantidade(dto.getQuantidade());
        return produtoPedido;
    }
}
