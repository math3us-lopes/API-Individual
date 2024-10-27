package com.example.mercearia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.cafe.security.dto.*;
import br.com.cafe.security.entities.Fornecedor;
import br.com.cafe.security.services.FornecedorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/lista")
	public ResponseEntity<List<Fornecedor>> buscarFornecedor() {
		List<Fornecedor> fornecedor = fornecedorService.findAll();
		if (fornecedor.isEmpty()) {
			return ResponseEntity.noContent().build();

		} else {

			return ResponseEntity.ok().body(fornecedor);
		}

	}

	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/atualizar")
	public ResponseEntity<String> atualizar(@RequestParam Integer id,
			@RequestBody FornecedorRequestDTO fornecedorRequestDTO) {
		FornecedorResponseDTO atualizadoFornecedor = fornecedorService.atualizarFornecedor(id, fornecedorRequestDTO);
		if (atualizadoFornecedor != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Cadastro do fornecedor atualizado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro do fornecedor não localizado");

		}
	}
	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deletar")
	public void delete(@RequestParam Integer id) {
		fornecedorService.deleteById(id);
	}

	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/cadastrar")
	public ResponseEntity<FornecedorResponseDTO> cadastrarFornecedor(@RequestBody FornecedorRequestDTO fornecedorRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.saveFornecedor(fornecedorRequestDTO));
	}

}
