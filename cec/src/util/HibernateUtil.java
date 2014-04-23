/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author sambasow
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            //populateDB();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSessionFactory() throws HibernateException{
        return sessionFactory.openSession();
    }
    
//public static void populateDB(){
//		
//		Centres c=new Centres("Malika", "Thiaroye", "0012", "194", "commune", "Pikine", "paangom@gmail.com", "Syscoweb SA", "774246535", "", "Dakar", "Principal");
//		Session s1 = getSessionFactory();
//		
//		
//		Transaction t1 = s1.beginTransaction();
//		s1.save(c);
//		t1.commit();
//		
//		s1.close();
//
//		
//		Users u1 = new Users(c, "Malika","NGOM", "00001", "778921429", "gayame", "Papa", "officier","papa",true);
//		
//		Session s = getSessionFactory();
//		
//		Transaction t = s.beginTransaction();
//		s.save(u1);
//		t.commit();
//		
//		s.close();
//		
//	}
}
