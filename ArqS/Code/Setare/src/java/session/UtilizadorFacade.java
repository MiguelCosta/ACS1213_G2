/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utilizador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
