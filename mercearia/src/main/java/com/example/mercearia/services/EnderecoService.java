package com.example.mercearia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cafe.security.dto.EnderecoRequestDTO;
import br.com.cafe.security.dto.EnderecoResponseDTO;
import br.com.cafe.security.entities.Endereco;
import br.com.cafe.security.repositories.EnderecoRepository;
import br.com.cafe.utils.Util;

@Service
public class EnderecoService {
	@Autowired
	Util util;

	@Autowired
	EnderecoRepository enderecoRepository;

	public EnderecoResponseDTO saveEndereco(EnderecoRequestDTO enderecoRequestDTO) {

		EnderecoResponseDTO viaCepRequest = util.buscarEndereco(enderecoRequestDTO.getCep());

		EnderecoResponseDTO endereco = new EnderecoResponseDTO();
		endereco.setCep(viaCepRequest.getCep());
		endereco.setLogradouro(viaCepRequest.getLogradouro());
		endereco.setBairro(viaCepRequest.getBairro());
		endereco.setUf(viaCepRequest.getUf());
		endereco.setLocalidade(viaCepRequest.getLocalidade());

		EnderecoResponseDTO enderecoResponse = new EnderecoResponseDTO ();
		enderecoResponse.setCep(endereco.getCep());
		enderecoResponse.setLogradouro(endereco.getLogradouro());
		enderecoResponse.setBairro(endereco.getBairro());
		enderecoResponse.setUf(endereco.getUf());
		enderecoResponse.setLocalidade(endereco.getLocalidade());

		Endereco convertidoEndereco = EnderecoResponseDTO.toEndereco(enderecoResponse);
		Endereco enderecoSalvo = enderecoRepository.save(convertidoEndereco);
		endereco.setId(enderecoSalvo.getId());

		return endereco;

	}

	public Endereco findById(Integer id) {
		Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
		return optionalEndereco.orElseThrow(() -> new RuntimeException("Endereco não encontrado com id: " + id));
	}

	public com.example.mercearia.security.dto.EnderecoResponseDTO saveEndereco(
			com.example.mercearia.security.dto.EnderecoRequestDTO enderecoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
