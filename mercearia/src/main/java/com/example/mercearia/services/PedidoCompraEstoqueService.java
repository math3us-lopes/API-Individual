package com.example.mercearia.services;

import java.util.*;
import java.util.stream.Collectors;

import br.com.cafe.security.entities.Fornecedor;
import br.com.cafe.security.entities.ProdutoEstoque;
import br.com.cafe.security.entities.User;
import br.com.cafe.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.cafe.security.dto.PedidoCompraEstoqueRequestDTO;
import br.com.cafe.security.dto.PedidoCompraEstoqueResponseDTO;

import br.com.cafe.security.entities.PedidoCompraEstoque;
import br.com.cafe.security.repositories.PedidoCompraEstoqueRepository;
import jakarta.transaction.Transactional;

//Indica que a classe é um serviço
@Service
public class PedidoCompraEstoqueService {

	// Injeta FornecedorRepository em FornecedorService
	@Autowired
	// Repositorio que sera usado para interagir com banco de dados
	private PedidoCompraEstoqueRepository pedidoCompraEstoqueRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProdutoEstoqueService produtoEstoqueService;

	@Autowired
	FornecedorService fornecedorService;



	public PedidoCompraEstoqueResponseDTO savePedidoCompra(Integer IdUsuario, PedidoCompraEstoqueRequestDTO pedidoCompraEstoqueRequestDTO) {

		PedidoCompraEstoque pedidoCompraEstoque = new PedidoCompraEstoque();

		// Busca o produto e o fornecedor apenas uma vez
		ProdutoEstoque produtoEstoque = produtoEstoqueService.findById(pedidoCompraEstoqueRequestDTO.getIdProduto());
		Fornecedor fornecedor = fornecedorService.findById(pedidoCompraEstoqueRequestDTO.getIdFornecedor());

		// Preenche os dados do pedido de compra
		pedidoCompraEstoque.setProdutosEstoque(Collections.singleton(produtoEstoque)); // Usando Collections.singleton para criar o Set
		pedidoCompraEstoque.setFornecedores(fornecedor);
		pedidoCompraEstoque.setQuantidade(pedidoCompraEstoqueRequestDTO.getQuantidade());
		pedidoCompraEstoque.setStatusRecebido(pedidoCompraEstoqueRequestDTO.getStatus());
		pedidoCompraEstoque.setDataPedido(pedidoCompraEstoqueRequestDTO.getDataPedido());
		User user  = userRepository.findById(IdUsuario).get();
		pedidoCompraEstoque.setUser(user);

		// Salva o pedido de compra
		PedidoCompraEstoque savedPedidoCompra = pedidoCompraEstoqueRepository.save(pedidoCompraEstoque);

		// Prepara a resposta
		PedidoCompraEstoqueResponseDTO pedidoCompraEstoqueResponseDTO = new PedidoCompraEstoqueResponseDTO();
		pedidoCompraEstoqueResponseDTO.setId(savedPedidoCompra.getId());
		pedidoCompraEstoqueResponseDTO.setIdFornecedor(fornecedor.getId());
		pedidoCompraEstoqueResponseDTO.setIdProduto(produtoEstoque.getId());
		pedidoCompraEstoqueResponseDTO.setStatus(savedPedidoCompra.getStatusRecebido());
		pedidoCompraEstoqueResponseDTO.setQuantidade(savedPedidoCompra.getQuantidade());

		return pedidoCompraEstoqueResponseDTO;
	}

	// Busca no banco de dados
	public Optional<PedidoCompraEstoque> findById(Integer id) {
		return pedidoCompraEstoqueRepository.findById(id);
	}
	// Retorna uma lista dos fornecedores

	public List<PedidoCompraEstoqueResponseDTO> findAll() {
		List<PedidoCompraEstoque> pedidos = pedidoCompraEstoqueRepository.findAll();
		return pedidos.stream().map(pedido -> {
			PedidoCompraEstoqueResponseDTO pedidoCompraEstoqueResponseDTO = new PedidoCompraEstoqueResponseDTO(
					pedido.getId(),
					pedido.getFornecedores().getId(),
					pedido.getProdutosEstoque().iterator().next().getId(),
					pedido.getStatusRecebido(),
					pedido.getQuantidade()
			);
			return pedidoCompraEstoqueResponseDTO;
		}).collect(Collectors.toList());
	}

	public PedidoCompraEstoqueResponseDTO atualizarPedido(Integer id, PedidoCompraEstoqueRequestDTO pedidoCompraEstoqueRequestDTO){
		Optional<PedidoCompraEstoque> optionalPedido = pedidoCompraEstoqueRepository.findById(id);
		PedidoCompraEstoque pedidoCompraEstoque = optionalPedido.get();
		pedidoCompraEstoque.setQuantidade(pedidoCompraEstoqueRequestDTO.getQuantidade());
		pedidoCompraEstoque.setStatusRecebido(pedidoCompraEstoqueRequestDTO.getStatus());
		pedidoCompraEstoque.setDataPedido(pedidoCompraEstoqueRequestDTO.getDataPedido());
		pedidoCompraEstoqueRepository.save(pedidoCompraEstoque);
        return new PedidoCompraEstoqueResponseDTO(
				pedidoCompraEstoque.getId(),
				pedidoCompraEstoque.getFornecedores().getId(),
				pedidoCompraEstoque.getProdutosEstoque().iterator().next().getId(),
				pedidoCompraEstoque.getStatusRecebido(),
				pedidoCompraEstoque.getQuantidade()
		);
    }

	// Deletar um pedido por ID
	@Transactional
	public void delete(Integer id) {
		pedidoCompraEstoqueRepository.deleteById(id);
	}

	public boolean existsById(Integer idPedido) {
		return pedidoCompraEstoqueRepository.existsById(idPedido);
	}
}



