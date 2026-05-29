package com.bolao.copa.service;

import com.bolao.copa.dto.DadosPalpiteDTO;
import com.bolao.copa.dto.PalpiteItemDTO;
import com.bolao.copa.model.Palpite;
import com.bolao.copa.model.Utilizador;
import com.bolao.copa.repository.PalpiteRepository;
import com.bolao.copa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BolaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PalpiteRepository palpiteRepository;

    @Transactional // Garante que, se der erro no meio do processo, o banco desfaz tudo (rollback)
    public void processarESalvarPalpites(DadosPalpiteDTO dadosDTO) {
        
        // 1. Verifica se o usuário já existe no banco
        Optional<Utilizador> usuarioExistente = usuarioRepository.findByNome(dadosDTO.getNome());
        Utilizador usuario;

        if (usuarioExistente.isPresent()) {
            usuario = usuarioExistente.get();
            // Como ele já existe e está mandando novos palpites, apagamos os antigos para evitar duplicidade
            palpiteRepository.deleteByUtilizadorId(usuario.getId());
        } else {
            // Se for um usuário novo, nós o criamos
            usuario = new Utilizador();
            usuario.setNome(dadosDTO.getNome());
            // Salvamos o usuário novo para gerar o ID dele no banco
            usuario = usuarioRepository.save(usuario);
        }

        // 2. Transforma os dados que vieram do Frontend (DTO) em Entidades do Banco
        List<Palpite> novosPalpites = new ArrayList<>();
        
        for (PalpiteItemDTO item : dadosDTO.getPalpites()) {
            Palpite palpite = new Palpite();
            palpite.setUtilizador(usuario);
            palpite.setGrupo(item.getGrupo());
            palpite.setSelecao(item.getSelecao());
            palpite.setPosicao(item.getPosicao());
            
            novosPalpites.add(palpite);
        }

        // 3. Salva todos os palpites de uma vez no banco de dados
        palpiteRepository.saveAll(novosPalpites);
    }
}