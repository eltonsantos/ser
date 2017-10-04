package br.com.cagece.controller;

import br.com.cagece.model.Material;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@ViewScoped
@Named("materialBean")
public class MaterialController implements Serializable {
    
    @Inject
    private EntityManager manager;
    private Material material;
    private Integer materialId;
    
    public MaterialController(){
        this.material = new Material();
    }
    
    // LISTAR MATERIAL
    public List<Material> listarMaterial(){
        return manager.createNativeQuery("SELECT * FROM Material", Material.class).getResultList();
    }
    
    // CADASTRAR MATERIAL
    public String cadastrarMaterial(){
        
        manager.getTransaction().begin();
                
        if (this.material.getId() == null) {
            manager.persist(material);
            manager.getTransaction().commit();
            manager.close();
            this.material = new Material();
        }
        else {
            manager.merge(material);
            manager.getTransaction().commit();
            manager.close();
        }       
        return "material?faces-redirect=true";
    }
    
    // EDITAR MATERIAL
    public void editarMaterial(Material material){   
        this.material = material;
    }
    
    // EXCLUIR MATERIAL
    public String excluirMaterial(Material material){
        manager.getTransaction().begin();
        material = porId(material.getId());
        manager.remove(material);
        manager.flush();
        manager.getTransaction().commit();
        return "material?faces-redirect=true";
    }
    
    // CONSULTA POR ID
    public Material porId(Integer id){
        return manager.find(Material.class, id);
    }

    // GETTERS AND SETTERS
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    
}
