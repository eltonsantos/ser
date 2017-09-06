package br.com.cagece.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {
	
    private EntityManagerFactory emf;
    
    // Construtor
    public JPAUtil(){
        this.emf = Persistence.createEntityManagerFactory("SerPU");
    }
    
    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
    
    public void closeEntityManager(@Disposes EntityManager em){
        em.close();
    }
}