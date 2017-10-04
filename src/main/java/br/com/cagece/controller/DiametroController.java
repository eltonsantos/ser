package br.com.cagece.controller;

import br.com.cagece.model.Diametro;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@ViewScoped
@Named("diametroBean")
public class DiametroController implements Serializable {
    
    @Inject
    private EntityManager manager;
    private Diametro diametro;
    private Integer diametroId;
    
    public DiametroController(){
        this.diametro = new Diametro();
    }
    
    // LISTAR DIAMETRO
    public List<Diametro> listarDiametro(){
        return manager.createNativeQuery("SELECT * FROM Diametro", Diametro.class).getResultList();
    }
    
    // CADASTRAR DIAMETRO
    public String cadastrarDiametro(){
        
        manager.getTransaction().begin();
                
        if (this.diametro.getId() == null) {
            manager.persist(diametro);
            manager.getTransaction().commit();
            manager.close();
            this.diametro = new Diametro();
        }
        else {
            manager.merge(diametro);
            manager.getTransaction().commit();
            manager.close();
        }       
        return "diametro?faces-redirect=true";
    }
    
    // EDITAR DIAMETRO
    public void editarDiametro(Diametro diametro){   
        this.diametro = diametro;
    }
    
    // EXCLUIR DIAMETRO
    public String excluirDiametro(Diametro diametro){
        manager.getTransaction().begin();
        diametro = porId(diametro.getId());
        manager.remove(diametro);
        manager.flush();
        manager.getTransaction().commit();
        return "diametro?faces-redirect=true";
    }
    
    // CONSULTA POR ID
    public Diametro porId(Integer id){
        return manager.find(Diametro.class, id);
    }

    // GETTERS AND SETTERS
    public Diametro getDiametro() {
        return diametro;
    }

    public void setDiametro(Diametro diametro) {
        this.diametro = diametro;
    }

    public Integer getDiametroId() {
        return diametroId;
    }

    public void setDiametroId(Integer diametroId) {
        this.diametroId = diametroId;
    }
    
}