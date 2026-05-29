package com.bolao.copa.repository;

import com.bolao.copa.model.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Utilizador, Long> {
    // O Spring cria o comando SQL automaticamente baseando-se no nome do método!
    Optional<Utilizador> findByNome(String nome);
}