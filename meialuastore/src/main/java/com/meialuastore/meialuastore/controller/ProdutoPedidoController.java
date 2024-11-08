package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.ProdutoPedidoRequestDTO;
import com.meialuastore.meialuastore.model.ProdutoPedido;
import com.meialuastore.meialuastore.model.Pedido;
import com.meialuastore.meialuastore.model.Produto;
import com.meialuastore.meialuastore.repository.ProdutoPedidoRepository;
import com.meialuastore.meialuastore.repository.PedidoRepository;
import com.meialuastore.meialuastore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produto-pedido")
public class ProdutoPedidoController {


    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoPedidoRequestDTO>> findAll() {
        List<ProdutoPedidoRequestDTO> produtosPedidos = produtoPedidoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtosPedidos);
    }

    @GetMapping("/{idPedido}/{idProduto}")
    public ResponseEntity<ProdutoPedidoRequestDTO> findById(
            @PathVariable Integer idPedido, @PathVariable Integer idProduto) {
        return produtoPedidoRepository.findByPedidoIdAndProdutoId(idPedido, idProduto)
                .map(produtoPedido -> ResponseEntity.ok(convertToDTO(produtoPedido)))
                .orElse(ResponseEntity.notFound().build());
    }

    private ProdutoPedidoRequestDTO convertToDTO(ProdutoPedido produtoPedido) {
        ProdutoPedidoRequestDTO dto = new ProdutoPedidoRequestDTO();
        dto.setId_pedido(produtoPedido.getPedido().getId_pedido());
        dto.setId_produto(produtoPedido.getProduto().getId_produto());
        return dto;
    }
}