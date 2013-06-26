/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Pontointeresse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JorgeMaia
 */
@Stateless
public class PontointeresseFacade extends AbstractFacade<Pontointeresse> {
    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PontointeresseFacade() {
        super(Pontointeresse.class);
    }
    
}
