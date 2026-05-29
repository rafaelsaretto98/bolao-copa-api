package com.bolao.copa.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios") // Mapeia para a tabela correspondente na base de dados
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 100)
    private String email;

    // Um utilizador pode ter muitos palpites. 
    // O CascadeType.ALL garante que se apagarmos um utilizador, os seus palpites também são apagados.
    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Palpite> palpites;

    // Construtor padrão obrigatório pelo JPA
    public Utilizador() {}

    // Construtor auxiliar
    public Utilizador(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Palpite> getPalpites() { return palpites; }
    public void setPalpites(List<Palpite> palpites) { this.palpites = palpites; }
}