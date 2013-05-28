/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utilizador;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
        Query q = em.createNamedQuery("Utilizador.findByUsernameAndPassword");
        q.setParameter("username", username);
        q.setParameter("password", password);

        List<Utilizador> users = q.getResultList();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean UtilizadorInsert(Utilizador user) {

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();

        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
