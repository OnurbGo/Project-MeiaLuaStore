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

    @Autowired /*injeção automática de dependências*/
    private FormaPagamentoRepository formaPagamentoRepository;

    /*trazer todas as informações */
    @GetMapping
    public List<FormaPagamento> getAll() {
        return formaPagamentoRepository.findAll();
    }

    /*trazer informações passando um parametro de busca*/
    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> getById(@PathVariable int id) {
        return formaPagamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
