package com.example.mercearia.controller;

import br.com.cafe.security.dto.PedidoCompraClienteRequestDTO;
import br.com.cafe.security.dto.PedidoCompraClienteResponseDTO;
import br.com.cafe.security.dto.PedidoCompraEstoqueResponseDTO;
import br.com.cafe.security.services.EmailService;
import br.com.cafe.security.services.PedidoCompraClienteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidocliente")
public class PedidoCompraClienteController {

    @Autowired
    PedidoCompraClienteService pedidoCompraClienteService;

    @Autowired
    EmailService emailService;

    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasRole('ADMIN')")
    // Criar um novo pedido
    @PostMapping("/cadastrar/{email}/{idProduto}/{idUsuario}")
    public ResponseEntity<PedidoCompraClienteResponseDTO> salvarPedido(@PathVariable String email,@PathVariable Integer idProduto, @PathVariable Integer idUsuario, @RequestBody PedidoCompraClienteRequestDTO pedidoRequestDTO) {
        if (pedidoRequestDTO != null) {
            PedidoCompraClienteResponseDTO pedidoSalvo = pedidoCompraClienteService.savePedidoCompraCliente(email, idProduto, idUsuario, pedidoRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Atualizar um pedido existente
    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/atualizar/{idPedido}/{idProduto}/{idEndereco}")
    public ResponseEntity<String> atualizarPedido(@PathVariable Integer idPedido, @PathVariable Integer idProduto, @PathVariable Integer idEndereco, @RequestBody PedidoCompraClienteRequestDTO pedidoRequestDTO) {
        PedidoCompraClienteResponseDTO pedidoAtualizado = pedidoCompraClienteService.atualizarPedidoCliente(idPedido, idProduto, idEndereco, pedidoRequestDTO);
        if (pedidoAtualizado != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Pedido atualizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
    }

    // Buscar todos os pedidos
    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/buscar")
    public ResponseEntity<List<PedidoCompraClienteResponseDTO>> buscarPedidos() {
        List<PedidoCompraClienteResponseDTO> pedidos = pedidoCompraClienteService.findAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(pedidos);
        }
    }

    /// Buscar um pedido de compra de estoque por ID
    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PedidoCompraEstoqueResponseDTO> buscarPedidoPorId(@PathVariable Integer id) {
        Optional<ResponseEntity<PedidoCompraEstoqueResponseDTO>> pedido = pedidoCompraClienteService.findById(id).map(pedidoCompraEstoque -> {
            PedidoCompraEstoqueResponseDTO pedidoCliente = new PedidoCompraEstoqueResponseDTO(
                    pedidoCompraEstoque.getId(),
                    pedidoCompraEstoque.getPedido(),
                    pedidoCompraEstoque.getDataPedido(),
                    pedidoCompraEstoque.getPreco()
            );
            return ResponseEntity.status(HttpStatus.OK).body(pedidoCliente);
        });

        return pedido.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Deletar um pedido
    @SecurityRequirement(name="Bearer Auth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<String> apagarPedido(@PathVariable Integer id) {
        if (pedidoCompraClienteService.existsById(id)) {
            pedidoCompraClienteService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
    }
}
