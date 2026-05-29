package com.bolao.copa.controller;

import com.bolao.copa.dto.DadosPalpiteDTO;
import com.bolao.copa.service.BolaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bolao")
@CrossOrigin(origins = "frabjous-begonia-4c6be0.netlify.app") // Muito importante: permite que o seu arquivo HTML local converse com a API (evita erro de CORS)
public class BolaoController {

    @Autowired
    private BolaoService bolaoService;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarPalpites(@RequestBody DadosPalpiteDTO dados) {
        try {
            // Verifica se o nome foi preenchido
            if (dados.getNome() == null || dados.getNome().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("O nome do usuário é obrigatório.");
            }

            // Chama o Service para fazer a mágica no banco de dados
            bolaoService.processarESalvarPalpites(dados);
            
            return ResponseEntity.ok("Palpites de " + dados.getNome() + " salvos com sucesso no banco de dados!");
            
        } catch (Exception e) {
            // Em caso de erro, devolve o motivo para o frontend
            return ResponseEntity.internalServerError().body("Erro ao salvar palpites: " + e.getMessage());
        }
    }

    @Autowired
    private com.bolao.copa.repository.UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listarResultados() {
        try {
            // Busca todos os usuários e seus palpites no banco
            return ResponseEntity.ok(usuarioRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar dados: " + e.getMessage());
        }
    }
}