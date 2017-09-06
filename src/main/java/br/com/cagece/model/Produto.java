package br.com.cagece.model;

public enum Produto {
    
    AGUA("Água"),
    ESGOTO("Esgoto");
    
    private String descricao;
    
    Produto(String descricao){
        this.descricao = descricao;
    }
    
    public String descricao(){
        return descricao;
    }
   
}