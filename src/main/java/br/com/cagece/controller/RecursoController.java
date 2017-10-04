package br.com.cagece.controller;

import br.com.cagece.model.Recurso;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@ViewScoped
@Named("recursoBean")
public class RecursoController implements Serializable {
    
    @Inject
    private EntityManager manager;
    private Recurso recurso;
    private Integer recursoId;
    
    public RecursoController(){
        this.recurso = new Recurso();
    }
    
    // LISTAR RECURSO
    public List<Recurso> listarRecurso(){
        return manager.createNativeQuery("SELECT * FROM Recurso", Recurso.class).getResultList();
    }
    
    // CADASTRAR RECURSO
    public String cadastrarRecurso(){
        
        manager.getTransaction().begin();
                
        if (this.recurso.getId() == null) {
            manager.persist(recurso);
            manager.getTransaction().commit();
            manager.close();
            this.recurso = new Recurso();
        }
        else {
            manager.merge(recurso);
            manager.getTransaction().commit();
            manager.close();
        }       
        return "recurso?faces-redirect=true";
    }
    
    // EDITAR RECURSO
    public void editarRecurso(Recurso recurso){   
        this.recurso = recurso;
    }
    
    // EXCLUIR RECURSO
    public String excluirRecurso(Recurso recurso){
        manager.getTransaction().begin();
        recurso = porId(recurso.getId());
        manager.remove(recurso);
        manager.flush();
        manager.getTransaction().commit();
        return "recurso?faces-redirect=true";
    }
    
    // CONSULTA POR ID
    public Recurso porId(Integer id){
        return manager.find(Recurso.class, id);
    }

    // GETTERS AND SETTERS
    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Integer getRecursoId() {
        return recursoId;
    }

    public void setRecursoId(Integer recursoId) {
        this.recursoId = recursoId;
    }
}
