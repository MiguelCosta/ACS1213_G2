/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Transporteviagem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JorgeMaia
 */
@Stateless
public class TransporteviagemFacade extends AbstractFacade<Transporteviagem> {
    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransporteviagemFacade() {
        super(Transporteviagem.class);
    }
    
}
