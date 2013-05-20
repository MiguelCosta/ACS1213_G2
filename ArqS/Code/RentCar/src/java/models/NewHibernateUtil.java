/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Miguel
 */
public class NewHibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final Session session;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session openSession(){
        if(sessionFactory.isClosed()){
            return sessionFactory.openSession();
        }else{
             return sessionFactory.getCurrentSession();
        }
        //return sessionFactory.openSession();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
