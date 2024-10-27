package com.example.mercearia.services;

import br.com.cafe.security.dto.CategoriaRequestDTO;
import br.com.cafe.security.dto.CategoriaResponseDTO;
import br.com.cafe.security.entities.Categoria;
import br.com.cafe.security.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    
    public CategoriaResponseDTO save(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaRequestDTO.getNome());
        categoria.setDescricao(categoriaRequestDTO.getDescricao());

        Categoria savedCategoria = categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(savedCategoria.getId(), savedCategoria.getNome(), savedCategoria.getDescricao());
    }


    public Categoria findById(Integer id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
    }

    public List<CategoriaResponseDTO> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(categoria -> {
            CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO(
                    categoria.getId(),
                    categoria.getNome(),
                    categoria.getDescricao()
            );
            return categoriaResponseDTO;
        }).collect(Collectors.toList());
    }

    
    public CategoriaResponseDTO update(Integer id, CategoriaRequestDTO categoriaRequestDTO) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
            Categoria categoria = optionalCategoria.get();
            categoria.setNome(categoriaRequestDTO.getNome());
            categoria.setDescricao(categoriaRequestDTO.getDescricao());
            categoriaRepository.save(categoria);
            return  new CategoriaResponseDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    
    public boolean delete(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;         }
        return false; 
    }


}
