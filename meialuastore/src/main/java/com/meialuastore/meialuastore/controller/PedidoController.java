package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.PedidoRequestDTO;
import com.meialuastore.meialuastore.model.Pedido;
import com.meialuastore.meialuastore.model.Usuario;
import com.meialuastore.meialuastore.repository.PedidoRepository;
import com.meialuastore.meialuastore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = repository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        // validação qunatidade
        if (pedidoRequestDTO.getQuantidade() == null || pedidoRequestDTO.getQuantidade() <= 0) {
            return ResponseEntity.badRequest().body("Quantidade do pedido é obrigatória e deve ser maior que zero.");
        }

        // Validação status
        if (pedidoRequestDTO.getStatus() == null || pedidoRequestDTO.getStatus().isEmpty()) {
            return ResponseEntity.badRequest().body("Status do pedido é obrigatório.");
        }

        // Validação usuario
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(pedidoRequestDTO.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado com o ID fornecido.");
        }

        // new pedido
        Pedido pedido = new Pedido();
        pedido.setQuantidade(pedidoRequestDTO.getQuantidade());  // Define a quantidade
        pedido.setStatus(pedidoRequestDTO.getStatus());
        pedido.setUsuario(usuarioOpt.get());

        //data
        if (pedidoRequestDTO.getDataPedido() == null) {
            pedido.setDataPedido(Timestamp.valueOf(LocalDateTime.now()));
        } else {
            pedido.setDataPedido(pedidoRequestDTO.getDataPedido());
        }

        //save
        Pedido savedPedido = repository.save(pedido);
        return ResponseEntity.ok(savedPedido);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(pedido -> {
                    repository.delete(pedido);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
