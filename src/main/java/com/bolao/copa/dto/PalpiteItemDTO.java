package com.bolao.copa.dto;

public class PalpiteItemDTO {
    private String grupo;
    private String selecao;
    private int posicao;

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    
    public String getSelecao() { return selecao; }
    public void setSelecao(String selecao) { this.selecao = selecao; }
    
    public int getPosicao() { return posicao; }
    public void setPosicao(int posicao) { this.posicao = posicao; }
}