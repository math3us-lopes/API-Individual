package com.example.mercearia.controller;

import br.com.cafe.security.dto.ProdutoEstoqueRequestDTO;
import br.com.cafe.security.dto.ProdutoEstoqueResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.cafe.security.services.ProdutoEstoqueService;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoEstoqueController {

	@Autowired
	ProdutoEstoqueService produtoEstoqueService;

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<String> atualizarProduto(@PathVariable Integer id,
			@RequestBody ProdutoEstoqueRequestDTO produtoEstoqueRequestDTO) {
		ProdutoEstoqueResponseDTO produtoEstoqueResponseDTO = produtoEstoqueService.updateProduto(id,
				produtoEstoqueRequestDTO);
		if (produtoEstoqueResponseDTO != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
		}
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/buscar")
	public ResponseEntity<List<ProdutoEstoqueResponseDTO>> buscarProduto() {
		List<ProdutoEstoqueResponseDTO> produtos = produtoEstoqueService.findAll();
		if (produtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(produtos);
		}
	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/apagar/{id}")
	public ResponseEntity<String> apagarProduto(@PathVariable Integer id) {
		if (produtoEstoqueService.existsById(id)) {
			produtoEstoqueService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
		}

	}

	@SecurityRequirement(name = "Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/cadastrar")
	public ResponseEntity<ProdutoEstoqueResponseDTO> salvarProduto(
			@RequestBody ProdutoEstoqueRequestDTO produtoEstoqueRequestDTO) {
		if (produtoEstoqueRequestDTO != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoEstoqueService.save(produtoEstoqueRequestDTO));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
