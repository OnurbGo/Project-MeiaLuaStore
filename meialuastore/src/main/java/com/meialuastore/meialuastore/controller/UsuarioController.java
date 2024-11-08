package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.UsuarioRequestDTO;
import com.meialuastore.meialuastore.model.Usuario;
import com.meialuastore.meialuastore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        // Converte o DTO para o modelo Usuario
        Usuario usuario = new Usuario();
        usuario.setLogin(usuarioRequestDTO.getLogin());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setCpf(usuarioRequestDTO.getCpf());

        // Salva o usuário e retorna o usuário salvo
        Usuario savedUsuario = repository.save(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

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
