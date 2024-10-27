package com.example.mercearia.controller;

import br.com.cafe.security.dto.CategoriaRequestDTO;
import br.com.cafe.security.dto.CategoriaResponseDTO;
import br.com.cafe.security.services.CategoriaService;
import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/salvar")
    public ResponseEntity<CategoriaResponseDTO> salvarCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaResponseDTO categoriaResponseDTO = categoriaService.save(categoriaRequestDTO);
        if(categoriaResponseDTO != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaResponseDTO);
        else
            return ResponseEntity.badRequest().body(null);
    }

    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        if(categoriaService.findAll().isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());
    }

    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        // Chama o serviço para atualizar a categoria com o ID fornecido usando os dados recebidos no corpo da requisição
        CategoriaResponseDTO categoriaResponseDTO = categoriaService.update(id, categoriaRequestDTO);

        // Verifica se o serviço de atualização retornou um objeto. Se não for null, retorna a resposta com status HTTP 200 OK
        // e o corpo contendo o objeto atualizado. Se for null, retorna 404 Not Found, indicando que a categoria não foi encontrada.
        return categoriaResponseDTO != null ?
                ResponseEntity.ok(categoriaResponseDTO) :  // Retorna 200 OK e o objeto atualizado, caso exista
                ResponseEntity.notFound().build();  // Retorna 404 Not Found, caso a categoria com o ID não tenha sido encontrada
    }

    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
        //Se veradeiro, significa que a categoria foi deletada com sucesso e retorna um status HTTP 200 OK
        //Se retornar falso, indica que a categoria não foi encontrada e retorna um status HTTP 404 Not Found.
        return categoriaService.delete(id) ?
                ResponseEntity.ok().build() :  // Retorna 200 OK se a categoria foi deletada
                ResponseEntity.notFound().build();  // Retorna 404 Not Found se a categoria não foi encontrada
    }
    
}
