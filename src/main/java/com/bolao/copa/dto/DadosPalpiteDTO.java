package com.bolao.copa.dto;

import java.util.List;

public class DadosPalpiteDTO {
    private String nome;
    private List<PalpiteItemDTO> palpites;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public List<PalpiteItemDTO> getPalpites() { return palpites; }
    public void setPalpites(List<PalpiteItemDTO> palpites) { this.palpites = palpites; }
}