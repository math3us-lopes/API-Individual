package com.example.mercearia.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mercearia.repositories.EnderecoRepository;
import com.example.mercearia.repositories.PedidoCompraClienteRepository;
import com.example.mercearia.repositories.ProdutoEstoqueRepository;
import com.example.mercearia.repositories.UserRepository;
import com.example.mercearia.security.dto.EnderecoRequestDTO;
import com.example.mercearia.security.dto.EnderecoResponseDTO;
import com.example.mercearia.security.dto.PedidoCompraClienteRequestDTO;
import com.example.mercearia.security.dto.ProdutoEstoqueResponseDTO;
import com.example.mercearia.security.entities.Endereco;
import com.example.mercearia.security.entities.PedidoCompraCliente;
import com.example.mercearia.security.entities.ProdutoEstoque;
import com.example.mercearia.security.entities.User;

import br.com.cafe.security.dto.PedidoCompraClienteResponseDTO;
import jakarta.transaction.Transactional;

@Service
public class PedidoCompraClienteService {

    @Autowired
    private PedidoCompraClienteRepository pedidoCompraClienteRepository;

    @Autowired
    private ProdutoEstoqueRepository produtoEstoqueRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ProdutoEstoqueService produtoEstoqueService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public PedidoCompraClienteResponseDTO savePedidoCompraCliente(String email, Integer idProduto, Integer idUsuario, PedidoCompraClienteRequestDTO pedidoCompraClienteRequestDTO) {
        // Salva o endereço
        EnderecoRequestDTO enderecoDTO = pedidoCompraClienteRequestDTO.getEndereco();
        EnderecoResponseDTO enderecoResponse = enderecoService.saveEndereco(enderecoDTO);

        // Cria o pedido
        PedidoCompraCliente pedidoCompraCliente = new PedidoCompraCliente();
        pedidoCompraCliente.setPedido(pedidoCompraClienteRequestDTO.getPedido());
        pedidoCompraCliente.setDataPedido(pedidoCompraClienteRequestDTO.getDataPedido());

        // Associa o usuário ao pedido
        br.com.cafe.security.entities.User user = userRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Adiciona o pedido ao Set de pedidos do usuário
        user.getPedidos().add(pedidoCompraCliente);  // Adiciona o pedido ao usuário
        pedidoCompraClienteRepository.save(pedidoCompraCliente);  // Salva o pedido primeiro

        // Salva a associação no repositório de usuários
        userRepository.save(user);

        // Busca o produto no estoque e diminui a quantidade
        ProdutoEstoque produtoEstoque = produtoEstoqueService.findById(idProduto);
        // Calcula o preço total
        double totalPrice = produtoEstoque.getPreco() * pedidoCompraClienteRequestDTO.getQuantidade();
        pedidoCompraCliente.setPreco(totalPrice);
        String nomeProduto = produtoEstoque.getNome();
        Double precoProduto = produtoEstoque.getPreco();

        // Associa o produto ao pedido
        Set<ProdutoEstoque> produtosEstoques = new HashSet<>();
        produtosEstoques.add(produtoEstoque);
        pedidoCompraCliente.setProdutosEstoques(produtosEstoques);

        // Associa o endereço ao pedido
        Endereco endereco = enderecoService.findById(enderecoResponse.getId());
        Set<Endereco> enderecos = new HashSet<>();
        enderecos.add(endereco);
        pedidoCompraCliente.setEnderecos(enderecos);

        // Salva o pedido com as associações corretas no banco
        PedidoCompraCliente savedPedidoCompraCliente = pedidoCompraClienteRepository.save(pedidoCompraCliente);

        // Monta o DTO de resposta pedido Cliente
        PedidoCompraClienteResponseDTO responseDTO = new PedidoCompraClienteResponseDTO();
        responseDTO.setId(savedPedidoCompraCliente.getId());
        responseDTO.setPedido(savedPedidoCompraCliente.getPedido());
        responseDTO.setDataPedido(savedPedidoCompraCliente.getDataPedido());
        responseDTO.setPrecoTotal(savedPedidoCompraCliente.getPreco());

        // Monta o DTO do produto
        ProdutoEstoqueResponseDTO produtoEstoqueResponse = new ProdutoEstoqueResponseDTO(
                produtoEstoque.getNome(),
                produtoEstoque.getPreco(),
                produtoEstoque.getQuantidade()
        );
        responseDTO.setProdutoEstoque(produtoEstoqueResponse);

        // Monta o DTO do endereço
        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();
        enderecoResponseDTO.setId(endereco.getId());
        enderecoResponseDTO.setCep(endereco.getCep());
        enderecoResponseDTO.setLogradouro(endereco.getLogradouro());
        responseDTO.setEndereco(enderecoResponseDTO);

        // Envia o email
        emailService.enviarEmailMercearia(email, pedidoCompraClienteRequestDTO.getPedido(), nomeProduto, precoProduto, pedidoCompraClienteRequestDTO.getQuantidade(), totalPrice);

        return responseDTO;
    }

    public PedidoCompraClienteResponseDTO atualizarPedidoCliente(Integer idPedido, Integer idProduto, Integer idEndereco, PedidoCompraClienteRequestDTO pedidoCompraClienteRequestDTO) {
        PedidoCompraCliente pedidoCompraCliente = pedidoCompraClienteRepository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Atualiza o pedido
        pedidoCompraCliente.setPedido(pedidoCompraClienteRequestDTO.getPedido());
        pedidoCompraCliente.setDataPedido(pedidoCompraClienteRequestDTO.getDataPedido());

        // Busca o produto no estoque e diminui a quantidade
        ProdutoEstoque produtoEstoque = produtoEstoqueService.diminuirQuantidade(idProduto, pedidoCompraClienteRequestDTO.getQuantidade());

        // Calcula o preço total
        double totalPrice = produtoEstoque.getPreco() * pedidoCompraClienteRequestDTO.getQuantidade();
        pedidoCompraCliente.setPreco(totalPrice);

        // Associa o produto ao pedido
        Set<ProdutoEstoque> produtosEstoques = new HashSet<>();
        produtosEstoques.add(produtoEstoque);
        pedidoCompraCliente.setProdutosEstoques(produtosEstoques);

        // Associa o endereço ao pedido
        Endereco endereco = enderecoService.findById(idEndereco);
        Set<Endereco> enderecos = new HashSet<>();
        enderecos.add(endereco);
        pedidoCompraCliente.setEnderecos(enderecos);

        // Salva o pedido com as associações corretas no banco
        PedidoCompraCliente savedPedidoCompraCliente = pedidoCompraClienteRepository.save(pedidoCompraCliente);

        // Monta o DTO de resposta pedido Cliente
        PedidoCompraClienteResponseDTO responseDTO = new PedidoCompraClienteResponseDTO();
        responseDTO.setId(savedPedidoCompraCliente.getId());
        responseDTO.setPedido(savedPedidoCompraCliente.getPedido());
        responseDTO.setDataPedido(savedPedidoCompraCliente.getDataPedido());
        responseDTO.setPrecoTotal(savedPedidoCompraCliente.getPreco());

        return responseDTO;
    }

    public List<PedidoCompraClienteResponseDTO> findAll() {
        List<PedidoCompraCliente> pedidos = pedidoCompraClienteRepository.findAll();

        return pedidos.stream().map(pedido -> {
            PedidoCompraClienteResponseDTO pedidoCompraClienteResponseDTO = new PedidoCompraClienteResponseDTO();
            pedidoCompraClienteResponseDTO.setId(pedido.getId());
            pedidoCompraClienteResponseDTO.setPedido(pedido.getPedido());
            pedidoCompraClienteResponseDTO.setDataPedido(pedido.getDataPedido());
            pedidoCompraClienteResponseDTO.setPrecoTotal(pedido.getPreco());

            return pedidoCompraClienteResponseDTO;
        }).collect(Collectors.toList());
    }

    public Optional<PedidoCompraCliente> findById(Integer id) {
        return pedidoCompraClienteRepository.findById(id);
    }

    public boolean existsById(Integer id) {
        return pedidoCompraClienteRepository.existsById(id);
    }

    public void delete(Integer id) {
        pedidoCompraClienteRepository.deleteById(id);
    }
}
