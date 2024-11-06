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

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoPedidoRequestDTO> findById(@PathVariable Integer id) {
        return produtoPedidoRepository.findById(id)
                .map(produtoPedido -> ResponseEntity.ok(convertToDTO(produtoPedido)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoPedidoRequestDTO> save(@RequestBody ProdutoPedidoRequestDTO produtoPedidoRequestDTO) {
        ProdutoPedido produtoPedido = convertToEntity(produtoPedidoRequestDTO);
        ProdutoPedido savedProdutoPedido = produtoPedidoRepository.save(produtoPedido);
        return ResponseEntity.ok(convertToDTO(savedProdutoPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        return produtoPedidoRepository.findById(id)
                .map(produtoPedido -> {
                    produtoPedidoRepository.delete(produtoPedido);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private ProdutoPedidoRequestDTO convertToDTO(ProdutoPedido produtoPedido) {
        ProdutoPedidoRequestDTO dto = new ProdutoPedidoRequestDTO();
        dto.setId_produto_pedido(produtoPedido.getId_produto_pedido());
        dto.setId_pedido(produtoPedido.getPedido().getId_pedido());
        dto.setId_produto(produtoPedido.getProduto().getId_produto());
        dto.setQuantidade(produtoPedido.getQuantidade());
        return dto;
    }

    private ProdutoPedido convertToEntity(ProdutoPedidoRequestDTO dto) {
        ProdutoPedido produtoPedido = new ProdutoPedido();
        produtoPedido.setId_produto_pedido(dto.getId_produto_pedido());

        Pedido pedido = pedidoRepository.findById(dto.getId_pedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + dto.getId_pedido()));
        Produto produto = produtoRepository.findById(dto.getId_produto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + dto.getId_produto()));

        produtoPedido.setPedido(pedido); // definir Pedido no ProdutoPedido
        produtoPedido.setProduto(produto); // definir Produto no ProdutoPedido
        produtoPedido.setQuantidade(dto.getQuantidade());
        return produtoPedido;
    }
}