package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.UsuarioRequestDTO;
import com.meialuastore.meialuastore.model.Usuario;
import com.meialuastore.meialuastore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired /*injeção automática de dependências*/
    private UsuarioRepository repository;

    /*trazer todas as informações*/
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    /*trazer informações passando um parametro de busca*/
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*metodo para postar um novo usuario*/
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario();
        usuario.setLogin(usuarioRequestDTO.getLogin());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setCpf(usuarioRequestDTO.getCpf());

        Usuario savedUsuario = repository.save(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    /*deletar informações passando um parametro de busca*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        return repository.findById(id)
                .map(usuario -> {
                    repository.delete(usuario);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
