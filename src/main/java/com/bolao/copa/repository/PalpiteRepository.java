package com.bolao.copa.repository;

import com.bolao.copa.model.Palpite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalpiteRepository extends JpaRepository<Palpite, Long> {
    // Útil caso o usuário queira refazer o bolão inteiro (apagamos os antigos e salvamos os novos)
    void deleteByUtilizadorId(Long utilizadorId);
}