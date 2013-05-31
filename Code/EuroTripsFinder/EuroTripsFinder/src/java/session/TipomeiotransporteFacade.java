/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Tipomeiotransporte;
import entity.Utilizador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Miguel
 */
@Stateless
public class TipomeiotransporteFacade extends AbstractFacade<Tipomeiotransporte> {
    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipomeiotransporteFacade() {
        super(Tipomeiotransporte.class);
    }
    
    public Tipomeiotransporte TipoTransporteByNome(String nome){
        Query q = em.createNamedQuery("Utilizador.findByNome");
        q.setParameter("nome", nome);
        
        List<Tipomeiotransporte> transportes = q.getResultList();
        if (transportes.size() > 0) {
            return transportes.get(0);
        } else {
            return null;
        }

    }
    
}
