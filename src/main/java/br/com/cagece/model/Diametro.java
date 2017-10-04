/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cagece.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Diametro implements Serializable {
    
    private Integer id;    
    @NotEmpty
    private String descricao;
    private Set<Material> materiais;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy = "diametros")
    public Set<Material> getMateriais() {
        return this.materiais;
    }

    public void setMateriais(Set<Material> materiais) {
        this.materiais = materiais;
    }
    
}