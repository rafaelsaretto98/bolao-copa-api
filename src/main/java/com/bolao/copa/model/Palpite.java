package com.bolao.copa.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "palpites", uniqueConstraints = {
    // Garante na base de dados que o mesmo utilizador não repita palpites para a mesma seleção
    @UniqueConstraint(columnNames = {"usuario_id", "selecao"})
})
public class Palpite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitos palpites pertencem a um único utilizador
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Utilizador utilizador;

    @Column(nullable = false, length = 10)
    private String grupo;

    @Column(nullable = false, length = 50)
    private String selecao;

    @Column(nullable = false)
    private Integer posicao;

    // Construtor padrão obrigatório pelo JPA
    public Palpite() {}

    // Construtor auxiliar
    public Palpite(Utilizador utilizador, String grupo, String selecao, Integer posicao) {
        this.utilizador = utilizador;
        this.grupo = grupo;
        this.selecao = selecao;
        this.posicao = posicao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Utilizador getUtilizador() { return utilizador; }
    public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getSelecao() { return selecao; }
    public void setSelecao(String selecao) { this.selecao = selecao; }

    public Integer getPosicao() { return posicao; }
    public void setPosicao(Integer posicao) { this.posicao = posicao; }
}