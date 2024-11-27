package com.meialuastore.meialuastore.controller;

import com.meialuastore.meialuastore.dto.ImagemRequestDTO;
import com.meialuastore.meialuastore.model.Imagem;
import com.meialuastore.meialuastore.model.Produto;
import com.meialuastore.meialuastore.repository.ImagemRepository;
import com.meialuastore.meialuastore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired /*injeção automática de dependências*/
    private ImagemRepository imagemRepository;

    @Autowired /*injeção automática de dependências*/
    private ProdutoRepository produtoRepository;

    /*trazer todas as informações*/
    @GetMapping
    public List<ImagemRequestDTO> listarImagens() {
        return imagemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /*trazer informações passando um parametro de busca*/

    @GetMapping("/produto/{id_produto}")
    public ResponseEntity<List<ImagemRequestDTO>> listarImagensPorProduto(@PathVariable Integer id_produto) {
        List<Imagem> imagens = imagemRepository.findByProduto_Id_produto(id_produto);

        if (imagens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ImagemRequestDTO> imagemDTOs = imagens.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(imagemDTOs);
    }

    /*A principal função do DTO em meu codigo é representar de maneira
    simplificada os dados que serão enviados ou recebidos em uma requisição
    HTTP, desacoplando a lógica interna e as entidades do banco de dados
    da API exposta ao cliente.*/

    private ImagemRequestDTO convertToDTO(Imagem imagem) {
        ImagemRequestDTO dto = new ImagemRequestDTO();
        dto.setUrl(imagem.getUrl());

        if (imagem.getProduto() != null) {
            dto.setId_produto(imagem.getProduto().getId_produto());
        } else {
            dto.setId_produto(null);
        }

        return dto;
    }

    /*metodo para postar um nova imagem*/
    @PostMapping
    public ResponseEntity<?> criarImagem(@RequestBody ImagemRequestDTO imagemRequestDTO) {
        // Validação produto
        Optional<Produto> produtoOpt = produtoRepository.findById(imagemRequestDTO.getId_produto());
        if (produtoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Produto não encontrado com o ID fornecido.");
        }

        // Log para debug
        System.out.println("Produto encontrado: " + produtoOpt.get());

        // new Imagem
        Imagem imagem = new Imagem();
        imagem.setUrl(imagemRequestDTO.getUrl());
        imagem.setProduto(produtoOpt.get());

        // Save
        Imagem imagemSalva = imagemRepository.save(imagem);
        return ResponseEntity.ok(imagemSalva);
    }

    /*metodo para deletar imagem passando parametro*/
    @DeleteMapping("/{id_imagens}")
    public ResponseEntity<?> excluirImagem(@PathVariable Integer id_imagens) {
        Optional<Imagem> imagemOpt = imagemRepository.findById(id_imagens);
        if (imagemOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Excluir imagem
        imagemRepository.delete(imagemOpt.get());
        return ResponseEntity.noContent().build();
    }

}
