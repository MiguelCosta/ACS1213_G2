/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Atividade;
import entity.Cidade;
import entity.Coordenada;
import java.util.Collection;
import java.util.List;
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
public class CidadeFacade extends AbstractFacade<Cidade> {

    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CidadeFacade() {
        super(Cidade.class);
    }
    
    public Cidade checkIfExistcidade(String nome) {
        Query q = em.createNamedQuery("Cidade.findByNome");
        q.setParameter("nome", nome);
        if (!q.getResultList().isEmpty()) {
            Cidade cidade = (Cidade) q.getSingleResult();

            return cidade;
        } else {
            return null;
}
    }

    public Collection<Atividade> atividades(Cidade cidade) {

        return cidade.getAtividadeCollection();

    }
    
    public Cidade cidade(int id)
    {
        Query q = em.createNamedQuery("Cidade.findById");
        q.setParameter("id", id);        
        return (Cidade)q.getSingleResult();
        
    }
    
    public Cidade cidade(String nome)
    {
        Query q = em.createNamedQuery("Cidade.findByNome");
        q.setParameter("nome", nome);        
        return (Cidade)q.getSingleResult();
        
    }

    public boolean register(Coordenada coordenada, Cidade cidade) {

        boolean sucess = false;
        try {
           
            em.persist(coordenada);
            em.flush();
            cidade.setCoordenadaid(coordenada);
            em.persist(cidade);
            sucess = true;
        } catch (Exception e) {
            sucess = false;
            return sucess;
        }
        return sucess;

    }
}
