package com.example.mercearia.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.cafe.security.dto.CategoriaRequestDTO;
import br.com.cafe.security.dto.CategoriaResponseDTO;
import br.com.cafe.security.dto.ProdutoEstoqueRequestDTO;
import br.com.cafe.security.dto.ProdutoEstoqueResponseDTO;
import br.com.cafe.security.entities.Categoria;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cafe.security.entities.ProdutoEstoque;
import br.com.cafe.security.repositories.ProdutoEstoqueRepository;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ProdutoEstoqueService {
	@Autowired
	private ProdutoEstoqueRepository repository;

	@Autowired
	private CategoriaService categoriaService;

	@Transactional
	public ProdutoEstoqueResponseDTO save(ProdutoEstoqueRequestDTO produtoEstoqueRequestDTO) {
		// Criar categoria request DTO e pegar os dados do body da requisição
		CategoriaRequestDTO categoriaRequestDTO = new CategoriaRequestDTO(produtoEstoqueRequestDTO.getCategoria().getNome(),
		produtoEstoqueRequestDTO.getCategoria().getDescricao());

		// Salvar categoria
		CategoriaResponseDTO categoriaResponseDTO = categoriaService.save(categoriaRequestDTO);


		ProdutoEstoque produtoEstoque = new ProdutoEstoque();
		produtoEstoque.setNome(produtoEstoqueRequestDTO.getNome());
		produtoEstoque.setPreco(produtoEstoqueRequestDTO.getPreco());
		produtoEstoque.setQuantidade(produtoEstoqueRequestDTO.getQuantidade());

		// Setar a categoria no produto
		Categoria categoria = categoriaService.findById(categoriaResponseDTO.getId());
		produtoEstoque.setCategoria(categoria);

		ProdutoEstoque savedProdutoEstoque = repository.save(produtoEstoque);
		ProdutoEstoqueResponseDTO produtoEstoqueResponseDTO = new ProdutoEstoqueResponseDTO();
		produtoEstoqueResponseDTO.setNome(savedProdutoEstoque.getNome());
		produtoEstoqueResponseDTO.setPreco(savedProdutoEstoque.getPreco());
		produtoEstoqueResponseDTO.setQuantidade(savedProdutoEstoque.getQuantidade());
		produtoEstoqueResponseDTO.setCategoria(categoriaResponseDTO);

		return produtoEstoqueResponseDTO;
	}

	public ProdutoEstoque findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	public List<ProdutoEstoqueResponseDTO> findAll() {
		List<ProdutoEstoque> produtos = repository.findAll();

		return produtos.stream().map(produto -> {
			ProdutoEstoqueResponseDTO produtoEstoqueResponseDTO = new ProdutoEstoqueResponseDTO(
					produto.getNome(),
					produto.getPreco(),
					produto.getQuantidade(),
					new CategoriaResponseDTO(
							produto.getCategoria().getId(),
							produto.getCategoria().getNome(),
							produto.getCategoria().getDescricao()
					)
			);
			return produtoEstoqueResponseDTO;
		}).collect(Collectors.toList());
	}

	public ProdutoEstoqueResponseDTO updateProduto(Integer id, ProdutoEstoqueRequestDTO produtoEstoqueRequestDTO) {
		Optional<ProdutoEstoque> optionalProduto = repository.findById(id);

		ProdutoEstoque produto = optionalProduto.get();
		produto.setNome(produtoEstoqueRequestDTO.getNome());
		produto.setPreco(produtoEstoqueRequestDTO.getPreco());
		produto.setQuantidade(produtoEstoqueRequestDTO.getQuantidade());

		Categoria categoria = new Categoria(produtoEstoqueRequestDTO.getCategoria().getNome(),
				produtoEstoqueRequestDTO.getCategoria().getDescricao());

		repository.save(produto);

		return new ProdutoEstoqueResponseDTO(
				produto.getNome(),
				produto.getPreco(),
				produto.getQuantidade(),
				new CategoriaResponseDTO(
						categoria.getId(),
						categoria.getNome(),
						categoria.getDescricao()
				)
		);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	public Set<ProdutoEstoque> insert(Integer quantidade) {
		ProdutoEstoque produtoEstoque = new ProdutoEstoque();
		produtoEstoque.setQuantidade(quantidade);

		// Salva o produto no repositório
		repository.save(produtoEstoque);

		// Retorna um conjunto com o produto inserido
		Set<ProdutoEstoque> produtos = new HashSet<>();
		produtos.add(produtoEstoque);
		return produtos;
	}

	// Metodo para obter a quantidade de um produto com base no id
	public Integer getQuantidadeProduto(Integer id) {
		// Chama o metodo do repositório para buscar a quantidade pelo id
		return repository.findQuantidadeById(id);
	}

	@Transactional
	public ProdutoEstoque diminuirQuantidade(Integer id, Integer quantidade) {
		// Chama o metodo da repository para diminuir a quantidade
		repository.diminuirQuantidade(quantidade, id);

		// Busca e retorna o ProdutoEstoque atualizado
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
	}


}


