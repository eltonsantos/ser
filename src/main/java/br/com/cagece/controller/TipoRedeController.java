package br.com.cagece.controller;

import br.com.cagece.model.TipoRede;
import br.com.cagece.util.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ViewScoped
@Named("tipoRedeBean")
public class TipoRedeController implements Serializable {
    
    @Inject
    private EntityManager manager;
    
    private TipoRede tipoRede;
    private Integer tipoRedeId;

    public TipoRedeController() {
        this.tipoRede = new TipoRede();
    }
    
    // LISTAR TIPOS DE REDE
    public List<TipoRede> listarTipoRede(){
        return manager.createNativeQuery("SELECT * FROM TipoRede", TipoRede.class).getResultList();
    }
    
    // CADASTRAR TIPO DE REDE
    public String cadastrarTipoRede(){
        
        manager.getTransaction().begin();
                
        if (this.tipoRede.getId() == null) {
            manager.persist(tipoRede);
            manager.getTransaction().commit();
            manager.close();
            this.tipoRede = new TipoRede();
        }
        else {
            manager.merge(tipoRede);
            manager.getTransaction().commit();
            manager.close();
        }       
        return "tipo_rede?faces-redirect=true";
    }
    
    // EDITAR TIPO DE REDE
    public void editarTipoRede(TipoRede tipoRede){   
    }
    
    // EXCLUIR TIPOS DE REDE
    public String excluirTipoRede(TipoRede tipoRede){
        manager.getTransaction().begin();
        tipoRede = porId(tipoRede.getId());
        manager.remove(tipoRede);
        manager.flush();
        manager.getTransaction().commit();
        return "tipo_rede?faces-redirect=true";
    }
    
    // CONSULTA POR ID
    public TipoRede porId(Integer id){
        return manager.find(TipoRede.class, id);
    }
    
    // GETTERS AND SETTERS
    public TipoRede getTipoRede() {
        return tipoRede;
    }
    
    public Integer getTipoRedeId() {
        return tipoRedeId;
    }

    public void setTipoRedeId(Integer tipoRedeId) {
        this.tipoRedeId = tipoRedeId;
    }
    
}