/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utilizador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JorgeMaia
 */
@Stateless
public class UtilizadorFacade extends AbstractFacade<Utilizador> {
    @PersistenceContext(unitName = "SetarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilizadorFacade() {
        super(Utilizador.class);
    }
    
     public Utilizador UtilizadorLogin(String username, String password) {
        Query q = em.createNamedQuery("Utilizador.findByUsernameAndPassword");
        q.setParameter("username", username);
        q.setParameter("password", password);
       
        if(q.getResultList().isEmpty()) return null;
        else return (Utilizador)q.getSingleResult();
    }
    
    public Utilizador UtilizadorByUsername(String username){
        Query q = em.createNamedQuery("Utilizador.findByUsername");
        q.setParameter("username", username);
        
        Utilizador user = (Utilizador) q.getSingleResult();
        return user;

    }
    
    public Utilizador UtilizadorUltimo() {
        Query q = em.createNamedQuery("Utilizador.findAll");

        List<Utilizador> users = q.getResultList();
            return users.get(users.size()-1);
        
    }
    
     public List<Utilizador> UtilizadorPages(int page) {
        Query q = em.createNamedQuery("Utilizador.findAll");
        q.setFirstResult(page*limitepage);
        q.setMaxResults(limitepage);
        List<Utilizador> users = q.getResultList();
        return users;
    }
    
}
