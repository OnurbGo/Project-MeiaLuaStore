package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.model.FormaPagamento;
import com.meialuastore.meialuastore.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public List<FormaPagamento> getAll() {
        return formaPagamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> getById(@PathVariable int id) {
        return formaPagamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FormaPagamento create(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> update(@PathVariable int id, @RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoRepository.findById(id).map(existingFormaPagamento -> {
            existingFormaPagamento.setMetodoForma(formaPagamento.getMetodoForma());
            return ResponseEntity.ok(formaPagamentoRepository.save(existingFormaPagamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (formaPagamentoRepository.existsById(id)) {
            formaPagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
