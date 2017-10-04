package br.com.cagece.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Material implements Serializable {
    
    private Integer id;
    @NotEmpty
    private String descricao;
    private String observacao;
    private Set<Diametro> diametros;
    
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

    @Column(columnDefinition = "TEXT")
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "material_diametro",
               joinColumns = @JoinColumn(name = "material_id"),
               inverseJoinColumns = @JoinColumn(name = "diametro_id"))
    public Set<Diametro> getDiametros() {
        return this.diametros;
    }

    public void setDiametros(Set<Diametro> diametros) {
        this.diametros = diametros;
    }
 
}