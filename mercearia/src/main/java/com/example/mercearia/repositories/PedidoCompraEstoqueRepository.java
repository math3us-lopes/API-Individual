package com.example.mercearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cafe.security.entities.PedidoCompraEstoque;

@Repository
public interface PedidoCompraEstoqueRepository extends JpaRepository<PedidoCompraEstoque, Integer> {

}
