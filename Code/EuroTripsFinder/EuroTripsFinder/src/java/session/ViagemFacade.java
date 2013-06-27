/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Viagem;
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
public class ViagemFacade extends AbstractFacade<Viagem> {

    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViagemFacade() {
        super(Viagem.class);
    }

    public List<Viagem> getViagensByUtilizadorId(int userID) {
        List<Viagem> viagens;
        Query q = em.createNamedQuery("Viagem.findByUtilizadorId");
        q.setParameter("utilizadorid", userID);

        viagens = q.getResultList();

        return viagens;
    }

    public Viagem getViagemDados(int viagemId) {
        
        Query q  = em.createNamedQuery("Viagem.findById");
        q.setParameter("id", viagemId);
        Viagem x = (Viagem)q.getSingleResult();
        return x;
    }
}
