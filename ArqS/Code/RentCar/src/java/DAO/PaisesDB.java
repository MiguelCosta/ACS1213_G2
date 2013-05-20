/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import models.Pais;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.mapping.List;

/**
 *
 * @author Miguel
 */
public class PaisesDB {
    
    public static Pais getByID(int id){
        
        Pais p;
        Session s = models.NewHibernateUtil.openSession();
        
        Query q = s.createQuery("FROM Pais WHERE id = :id");
        q.setParameter("id", id);
        p = (Pais) q.uniqueResult();
        
        return p;
    }
    
    public static List getAll(){
       List paises;
       
       paises = (List) models.NewHibernateUtil.openSession().createQuery("FROM Pais").list();
       
       return paises;
    }
    
    
}
