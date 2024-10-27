package com.example.mercearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cafe.security.entities.PedidoCompraCliente;

import java.util.Optional;

@Repository
public interface PedidoCompraClienteRepository extends JpaRepository<PedidoCompraCliente, Integer> {
    boolean existsByPedido(String pedido);
    boolean existsById(Integer id);
    PedidoCompraCliente findByPedido(String pedido);
    Optional<PedidoCompraCliente> findById(Integer id);
}
