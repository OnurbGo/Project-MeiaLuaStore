package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.PagamentoRequestDTO;
import com.meialuastore.meialuastore.model.Pagamento;
import com.meialuastore.meialuastore.model.Pedido;
import com.meialuastore.meialuastore.model.FormaPagamento;
import com.meialuastore.meialuastore.model.Produto;
import com.meialuastore.meialuastore.repository.PagamentoRepository;
import com.meialuastore.meialuastore.repository.PedidoRepository;
import com.meialuastore.meialuastore.repository.FormaPagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired /*injeção automática de dependências*/
    private PagamentoRepository pagamentoRepository;

    @Autowired /*injeção automática de dependências*/
    private PedidoRepository pedidoRepository;

    @Autowired /*injeção automática de dependências*/
    private FormaPagamentoRepository formaPagamentoRepository;

    /*trazer todas as informações*/
    @GetMapping
    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll();
    }

    /*trazer informações passando um parametro de busca*/
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getById(@PathVariable int id) {
        return pagamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*metodo para postar um novo pagamento*/
    @PostMapping
    public ResponseEntity<Pagamento> create(@RequestBody PagamentoRequestDTO pagamentoDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pagamentoDTO.getPedidoId());
        Optional<FormaPagamento> formaPagamentoOptional = formaPagamentoRepository.findById(pagamentoDTO.getFormaPagamentoId());
        if (pedidoOptional.isEmpty() || formaPagamentoOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setMetodoPagamento(pagamentoDTO.getMetodoPagamento());
        pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
        pagamento.setValorPago(pagamentoDTO.getValorPago());
        pagamento.setPedido(pedidoOptional.get());
        pagamento.setFormaPagamento(formaPagamentoOptional.get());

        Pagamento savedPagamento = pagamentoRepository.save(pagamento);
        return ResponseEntity.ok(savedPagamento);
    }

    /*deletar informação passando um parametro*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Esse Pagamento não foi encontrado"));

        this.pagamentoRepository.delete(pagamento);
        return ResponseEntity.noContent().build();
    }
}
