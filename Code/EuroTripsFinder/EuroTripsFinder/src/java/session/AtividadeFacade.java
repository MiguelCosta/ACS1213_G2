/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Atividade;
import entity.Cidade;
import entity.Coordenada;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import session.AbstractFacade;

/**
 *
 * @author JorgeMaia
 */
@Stateless
public class AtividadeFacade extends AbstractFacade<Atividade> {

    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtividadeFacade() {
        super(Atividade.class);
    }
    
    public boolean register(Atividade atividade) {

        boolean sucess = false;
        try {
            em.persist(atividade);
            em.flush();
            
            sucess = true;
        } catch (Exception e) {
            sucess = false;
            println(e.getMessage());
            return sucess;
}
        return sucess;

    }

    public boolean checkIfExistAtividade(String nome, String descricao) {
        Query q = em.createNamedQuery("Atividade.findByNomeAndDescricao");
        q.setParameter("nome", nome);
        q.setParameter("descricao", descricao);
        if (!q.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void println(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
