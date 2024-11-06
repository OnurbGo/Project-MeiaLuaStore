package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.model.Pagamento;
import com.meialuastore.meialuastore.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping
    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getById(@PathVariable int id) {
        return pagamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pagamento create(@RequestBody Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> update(@PathVariable int id, @RequestBody Pagamento pagamento) {
        return pagamentoRepository.findById(id).map(existingPagamento -> {
            existingPagamento.setMetodoPagamento(pagamento.getMetodoPagamento());
            existingPagamento.setPedido(pagamento.getPedido());
            existingPagamento.setFormaPagamento(pagamento.getFormaPagamento());
            existingPagamento.setDataPagamento(pagamento.getDataPagamento());
            existingPagamento.setValorPago(pagamento.getValorPago());
            return ResponseEntity.ok(pagamentoRepository.save(existingPagamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
