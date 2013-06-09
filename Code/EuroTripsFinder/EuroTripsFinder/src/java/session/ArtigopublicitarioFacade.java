/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Artigopublicitario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Miguel
 */
@Stateless
public class ArtigopublicitarioFacade extends AbstractFacade<Artigopublicitario> {
    @PersistenceContext(unitName = "EuroTripsFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtigopublicitarioFacade() {
        super(Artigopublicitario.class);
    }
    
    public Artigopublicitario ArtigoRandom() {
        try{
            Date datafim = new Date();
            Query q = em.createQuery("SELECT a FROM Artigopublicitario a, Contrato c WHERE a.contratoid = c and c.datafim >= :datafim");
            q.setParameter("datafim", datafim);
            List<Artigopublicitario> artigos = q.getResultList();
            if(artigos.isEmpty()){
                return null;
            }
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(artigos.size());
            return artigos.get(randomInt);
        } catch (Exception ex){
            return null;
        }
        
    }
    
}
