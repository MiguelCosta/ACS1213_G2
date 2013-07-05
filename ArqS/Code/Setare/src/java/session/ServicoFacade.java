/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Carro;
import entity.Servico;
import java.util.Date;
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
public class ServicoFacade extends AbstractFacade<Servico> {

    @PersistenceContext(unitName = "SetarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicoFacade() {
        super(Servico.class);
    }

    public boolean verificaCarro(Carro carro, Date dataChegada, Date dataPartida) {

        Query q = em.createNamedQuery("Servico.findByDatas");
        q.setParameter("carroid", carro);
        q.setParameter("dataChegada", dataChegada);
        q.setParameter("dataPartida", dataPartida);
        
        List<Servico> servicos = q.getResultList();
        if (servicos.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
