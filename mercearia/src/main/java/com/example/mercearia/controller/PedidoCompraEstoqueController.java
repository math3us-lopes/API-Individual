package com.example.mercearia.controller;

import java.util.List;
import java.util.Optional;

import br.com.cafe.security.dto.PedidoCompraEstoqueResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.cafe.exceptions.NomeException;
import br.com.cafe.security.dto.PedidoCompraEstoqueRequestDTO;
import br.com.cafe.security.services.PedidoCompraEstoqueService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidocompraestoque")
public class PedidoCompraEstoqueController {

	@Autowired
	private PedidoCompraEstoqueService pedidoCompraEstoqueService;

	// Cadastrar o pedido informando o id do usuario
	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cadastrar/{idUsuario}")
    public ResponseEntity<PedidoCompraEstoqueResponseDTO> cadastrarPedidoCompra(
            @Valid @RequestBody PedidoCompraEstoqueRequestDTO pedidoCompraEstoqueRequestDTO, @PathVariable Integer idUsuario) {
        try {
            PedidoCompraEstoqueResponseDTO responseDTO = pedidoCompraEstoqueService.savePedidoCompra(idUsuario,
                    pedidoCompraEstoqueRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (NomeException e) {
            throw new NomeException("Erro ao cadastrar pedido: " + e.getMessage());
        }
	}
	

	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarPedido(@PathVariable Integer id) {
		if (pedidoCompraEstoqueService.existsById(id)) {
			pedidoCompraEstoqueService.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	// Atualizar o pedido informando o id do pedido
	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPedido(@PathVariable Integer id,
            @Valid @RequestBody PedidoCompraEstoqueRequestDTO pedidoCompraEstoqueRequestDTO) {
        if (!pedidoCompraEstoqueService.existsById(id)) {
            throw new NomeException("Pedido não encontrado.");
        }

        try {
            PedidoCompraEstoqueResponseDTO pedidoAtualizado = pedidoCompraEstoqueService.atualizarPedido(id,
                    pedidoCompraEstoqueRequestDTO);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (Exception e) {
            throw new NomeException("Erro ao atualizar o pedido.");
        }
    }

	// Listar todos
	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/listar")
	public ResponseEntity<List<PedidoCompraEstoqueResponseDTO>> listarTodosPedidos() {
		List<PedidoCompraEstoqueResponseDTO> pedidos = pedidoCompraEstoqueService.findAll();
		return ResponseEntity.ok(pedidos);
	}
	@SecurityRequirement(name="Bearer Auth")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	// Buscar um pedido de compra de estoque por ID
	@GetMapping("/buscar/{id}")
	public ResponseEntity<PedidoCompraEstoqueResponseDTO> buscarPedidoPorId(@PathVariable Integer id) {
		Optional<PedidoCompraEstoqueResponseDTO> pedido = pedidoCompraEstoqueService.findById(id)
				.map(p -> new PedidoCompraEstoqueResponseDTO(p.getId(), p.getFornecedores().getId(),
						p.getProdutosEstoque().iterator().next().getId(), p.getStatusRecebido(), p.getQuantidade()));

		return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}

