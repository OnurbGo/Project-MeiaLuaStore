package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.ImagemRequestDTO;
import com.meialuastore.meialuastore.model.Imagem;
import com.meialuastore.meialuastore.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private ImagemRepository imagemRepository;

    // Endpoint para listar todas as imagens
    @GetMapping
    public List<ImagemRequestDTO> listarImagens() {
        return imagemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/produto/{id_produto}")
    public List<ImagemRequestDTO> listarImagensPorProduto(@PathVariable Integer id_produto) {
        return imagemRepository.findAll().stream()
                .filter(imagem -> imagem.getProduto().getId_produto().equals(id_produto))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ImagemRequestDTO convertToDTO(Imagem imagem) {
        ImagemRequestDTO dto = new ImagemRequestDTO();
        dto.setId_imagem(imagem.getId_imagem());
        dto.setUrl(imagem.getUrl());
        dto.setId_produto(imagem.getProduto().getId_produto());
        return dto;
    }
}
