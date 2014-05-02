package dao;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import models.Connected;
import models.Users;

public class ConnectedDAOImp implements ConnectedDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Session session = null;

	@Override
	public boolean addConnected(Users u) {
		// TODO Auto-generated method stub
		boolean flag;
        if(session == null)
            session = HibernateUtil.getSessionFactory();
        Connected con = new Connected();
        
        try {
        	long temps = System.currentTimeMillis()/1000;
    		con.setTemps_connexion(temps);
    		FacesContext context = FacesContext.getCurrentInstance();
    		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
    		String ip = request.getRemoteAddr();
    		con.setAdresseIp(ip);
    		con.setConnected(u);
            session.beginTransaction();
            session.save(con);
            session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }

       return flag;
	}

	@Override
	public boolean deleteConnected(Connected con) {
		// TODO Auto-generated method stub
		
		boolean flag;
        if(session == null)
            session = HibernateUtil.getSessionFactory();
        try {
       	 
            session.beginTransaction();
            session.delete(con);
            session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }

       return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Connected> findAllUsers() {
		// TODO Auto-generated method stub
		List<Connected> model = null;
		
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from Connected");
		model = q.list();
		return model;
	}

	@Override
	public Connected findUser(Users u) {
		// TODO Auto-generated method stub
		Connected model = null;
		 if (session == null)
				session = HibernateUtil.getSessionFactory();
			Query q = session.createQuery("from Connected a where a.connected = :id")
					.setParameter("id", u);
			
			model = (Connected)q.uniqueResult();
			
			return model;
					
	}

	@SuppressWarnings("null")
	@Override
	public boolean updateConnected(Users con) {
		// TODO Auto-generated method stub
		boolean flag;
        if(session == null)
            session = HibernateUtil.getSessionFactory();
        try {
        	session.beginTransaction();
       	 	Connected c = this.findUser(con);
            if(c == null){
            	long temps = System.currentTimeMillis()/1000;
        		c.setTemps_connexion(temps);
        		c.setAdresseIp(InetAddress.getLocalHost().getHostAddress());
        		c.setConnected(con);
            	session.save(con);
            }
            else{
            	long temps = System.currentTimeMillis()/1000;
        		c.setTemps_connexion(temps);
        		c.setAdresseIp(InetAddress.getLocalHost().getHostAddress());
            	session.merge(c);
            }
            
            session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }

       return flag;
	}

}
