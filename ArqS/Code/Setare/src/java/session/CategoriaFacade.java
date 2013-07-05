/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Carro;
import entity.Categoria;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JorgeMaia
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {
    @PersistenceContext(unitName = "SetarePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public List<Carro> listacarros (int categoria){
        
        Categoria cat = em.find(Categoria.class, categoria);        
        List<Carro> lista = (List<Carro>)cat.getCarroCollection();
        return lista;
    }
    
}
