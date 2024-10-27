package com.example.mercearia.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cafe.security.entities.ProdutoEstoque;

@Repository
public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Integer> {
    boolean existsById(Integer id);
/*
    @Modifying
    @Transactional // Necessário para operações de escrita
    @Query("insert into ProdutoEstoque (quantidade) values (:quantidade)")
    void insert(Integer quantidade);*/

    @Modifying
    @Query("update ProdutoEstoque p set p.quantidade = p.quantidade - :quantidade where p.id = :id")
    void diminuirQuantidade(@Param("id") Integer id, @Param("quantidade") Integer quantidade);

    @Query("select quantidade from ProdutoEstoque where id = :id")
    Integer findQuantidadeById(Integer id);
}