package com.example.mercearia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cafe.security.dto.EnderecoRequestDTO;
import br.com.cafe.security.dto.EnderecoResponseDTO;
import br.com.cafe.security.dto.FornecedorRequestDTO;
import br.com.cafe.security.dto.FornecedorResponseDTO;
import br.com.cafe.security.entities.Endereco;
import br.com.cafe.security.entities.Fornecedor;
import br.com.cafe.security.repositories.FornecedorRepository;

//Indica que a classe é um serviço
@Service
public class FornecedorService {

	// Injeta FornecedorRepository em FornecedorService
	@Autowired
	// Repositorio que sera usado para interagir com banco de dados
	private FornecedorRepository fornecedorRepository;

	@Autowired
	EnderecoService enderecoService;

	// Indica que os métodos devem ser executados dentro da transação
	@Transactional
	// Metodo para salvar objeto do tipo fornecedor
	public FornecedorResponseDTO saveFornecedor(FornecedorRequestDTO fornecedorRequestDTO) {
		// Cria o objeto do Request do fornecedor a partir do DTO recebido
		EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
		enderecoRequestDTO.setCep(fornecedorRequestDTO.getEndereco().getCep());

		// Salva o endereco e obtem o objeto EnderecoResponseDTO
		EnderecoResponseDTO enderecoResponseDTO = enderecoService.saveEndereco(enderecoRequestDTO);

		// Cria o objeto Fornecedor
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(fornecedorRequestDTO.getNome());
		fornecedor.setEmail(fornecedorRequestDTO.getEmail());
		fornecedor.setCnpj(fornecedorRequestDTO.getCnpj());

		// Obtem o endereco salvo usando o ID do EnderecoResponseDTO
		Endereco endereco = enderecoService.findById(enderecoResponseDTO.getId());
		fornecedor.setEndereco(endereco);

		// Salva o fornecedor
		Fornecedor savedFornecedor = fornecedorRepository.save(fornecedor);

		// Converte o objeto Fornecedor salvo para FornecedorResponseDTO
		FornecedorResponseDTO fornecedorResponseDTO = new FornecedorResponseDTO();
		fornecedorResponseDTO.setNome(savedFornecedor.getNome());
		fornecedorResponseDTO.setEmail(savedFornecedor.getEmail());
		fornecedorResponseDTO.setCnpj(savedFornecedor.getCnpj());

		// Cria e define o endereco na resposta do fornecedor
		EnderecoResponseDTO responseEndereco = new EnderecoResponseDTO();
		responseEndereco.setId(savedFornecedor.getEndereco().getId());
		responseEndereco.setCep(savedFornecedor.getEndereco().getCep());
		responseEndereco.setLogradouro(savedFornecedor.getEndereco().getLogradouro());
		responseEndereco.setBairro(savedFornecedor.getEndereco().getBairro());
		responseEndereco.setUf(savedFornecedor.getEndereco().getUf());
		responseEndereco.setLocalidade(savedFornecedor.getEndereco().getLocalidade());

		// Retorna o DTO final de resposta
		fornecedorResponseDTO.setEndereco(responseEndereco);
		return fornecedorResponseDTO;
	}

	// Busca no banco de dados
	public Fornecedor findById(Integer id) {
		Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);
		return optionalFornecedor.orElseThrow(() -> new RuntimeException("Fornecedor not found with id: " + id));
	}

	// Retorna uma lista dos fornecedores
	public List<Fornecedor> findAll() {
		return fornecedorRepository.findAll();
	}

	// Indica que os métodos devem ser executados dentro da transação
	@Transactional
	public void deleteById(Integer id) {
		fornecedorRepository.deleteById(id);
	}

	public FornecedorResponseDTO atualizarFornecedor(Integer id, FornecedorRequestDTO fornecedorRequestDTO) {
		Optional<Fornecedor> optionalFornecedor = fornecedorRepository.findById(id);
		if (optionalFornecedor.isPresent()) {
			Fornecedor fornecedor = optionalFornecedor.get();
			fornecedor.setNome(fornecedorRequestDTO.getNome());
			fornecedor.setEmail(fornecedorRequestDTO.getEmail());
			fornecedor.setCnpj(fornecedorRequestDTO.getCnpj());
			
			if (fornecedorRequestDTO.getEndereco()!= null) {
				Endereco endereco = fornecedor.getEndereco();
				endereco.setCep(fornecedorRequestDTO.getEndereco().getCep());	
			}
			fornecedorRepository.save(fornecedor);
			return new FornecedorResponseDTO(fornecedor);
		}
		return null;
	}
}
