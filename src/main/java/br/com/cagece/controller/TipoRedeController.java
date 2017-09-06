package br.com.cagece.controller;

import br.com.cagece.model.TipoRede;
import br.com.cagece.util.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ManagedBean(name = "tipoRedeBean")
@ViewScoped
public class TipoRedeController implements Serializable {
    
    @Inject
    private EntityManager manager;
    
    //private TipoRede tipoRede = new TipoRede();
    private Integer tipoRedeId;
    
    // LISTAR TIPOS DE REDE
    public List<TipoRede> listarTipoRede(){
        return manager.createNativeQuery("SELECT * FROM TipoRede", TipoRede.class).getResultList();
    }
    
    // CADASTRAR TIPO DE REDE
    public TipoRede cadastrarTipoRede(TipoRede tipoRede){
        return manager.merge(tipoRede);
    }
    
    // EDITAR TIPO DE REDE
    public void editarTipoRede(TipoRede tipoRede){   
    }
    
    // EXCLUIR TIPOS DE REDE
    public void excluirTipoRede(TipoRede tipoRede){
        try {
            tipoRede = porId(tipoRede.getId());
            manager.remove(tipoRede);
            manager.flush();       
        } catch (Exception e) {
            throw new NegocioException("Tipo de Rede não pode ser excluído!");
        }
        
    }
    
    // CONSULTA POR ID
    public TipoRede porId(Integer id){
        return manager.find(TipoRede.class, id);
    }
    
    // GETTERS AND SETTERS
    public Integer getTipoRedeId() {
        return tipoRedeId;
    }

    public void setTipoRedeId(Integer tipoRedeId) {
        this.tipoRedeId = tipoRedeId;
    }
    
}
