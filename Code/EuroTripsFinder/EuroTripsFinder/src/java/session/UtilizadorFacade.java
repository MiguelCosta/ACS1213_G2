/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utilizador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Miguel
 */
@Stateless
public class UtilizadorFacade extends AbstractFacade<Utilizador> {

    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilizadorFacade() {
        super(Utilizador.class);
    }

    public Utilizador UtilizadorLogin(String username, String password) {
        Query q = em.createNamedQuery("findByUsernameAndPassword");
        q.setParameter("username", username);
        q.setParameter("password", password);

        Utilizador u = (Utilizador) q.getSingleResult();
        return u;
    }
}
