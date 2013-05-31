/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Coordenada;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Miguel
 */
@Stateless
public class CoordenadaFacade extends AbstractFacade<Coordenada> {
    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoordenadaFacade() {
        super(Coordenada.class);
    }
    
       public boolean checkIfExists(Double lat, Double lon, String name) {
        Query q = em.createNamedQuery("Coordenada.findByLongAndLat");
        q.setParameter("longitude", lon);
        q.setParameter("latitude", lat);
        List<Coordenada> coordenadas = q.getResultList();
        
        Query q1 = em.createNamedQuery("Coordenada.findByNome");
        q1.setParameter("nome", name);       
        List<Coordenada> coordenadas1 = q1.getResultList();
        
        
        if (coordenadas.size() > 0 || coordenadas1.size() > 0){
            return true;
        } else {
            return false;
        }
    }
       
       public Coordenada verifica(int id)
       {
            Query q = em.createNamedQuery("Coordenada.findById");
            q.setParameter("id", id);                  
            List<Coordenada> coordenada = q.getResultList();
            if (coordenada.size() > 0){                
            return coordenada.get(0);
        } else {
            return null;
        }
          
       }

  

   
      
       
       
    
}
