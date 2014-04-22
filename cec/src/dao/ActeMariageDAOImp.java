/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.Serializable;
import java.util.List;

import models.DeclarationMariage;
import models.Search;
import models.Users;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 *
 * @author sambasow
 */
public class ActeMariageDAOImp implements ActeMariageDAO, Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Session session = null;

    @Override
    public boolean saveDeclarationMariage(DeclarationMariage acte) {
        boolean flag;
        if(session == null)
            session = HibernateUtil.getSessionFactory();
        try {
            session.beginTransaction();
            session.save(acte);
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
    public List<DeclarationMariage> findDeclarationByIdUser(Users u) {
    	if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationMariage a where a.createurMariage= :createur_user and a.etat = :Instance")
				.setInteger("createur_user", u.getUserId()).setString("Instance", "Instance");
		List<DeclarationMariage> l = q.list();
		return l;
    }


    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationMariage> getAllDeclarationMariage() {
        List<DeclarationMariage> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
	
		 Query q = session.createQuery("from DeclarationMariage d where d.etat = :etat").setString("etat", "Instance");
		 list = q.list();
		return list;
    }

    @Override
    public DeclarationMariage findById(int idActe) {
        DeclarationMariage model = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery("from DeclarationMariage a where a.id = :id")
				.setInteger("id", idActe);
		model = (DeclarationMariage) q.uniqueResult();
        return model;
    }


    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationMariage> getRegistreMariage() {
        List<DeclarationMariage> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationMariage a where a.etat = :valider" )
				.setString("valider", "Valider");
		list = q.list();
		return list;
    }


//    @Override
//    public boolean validateDeclarationMariage(DeclarationMariage dec) {
//        boolean flag;
//        if(session == null)
//            session = HibernateUtil.getSessionFactory();
//        DeclarationMariage d = this.findById(dec.getId());
//        try {
//            session.beginTransaction();
//            d.setEtat("Valider");
//            session.update(d);
//            session.beginTransaction().commit();
//            flag = true;
//        } catch (Exception e) {
//            flag = false;
//            session.beginTransaction().rollback();
//        }
//        return flag;
//    }
//
//    @Override
//    public boolean invalidateDeclarationMariage(DeclarationMariage dec) {
//        boolean flag;
//        if(session == null)
//            session = HibernateUtil.getSessionFactory();
//        DeclarationMariage d = this.findById(dec.getId());
//        try {
//            session.beginTransaction();
//            d.setEtat("Rejeter");
//            session.update(d);
//            session.beginTransaction().commit();
//            flag = true;
//        } catch (Exception e) {
//            flag = false;
//            session.beginTransaction().rollback();
//        }
//        return flag;
//    }
    
//     @SuppressWarnings("unchecked")
//	@Override
//    public List<DeclarationMariage> getAllDecByUserReject(Users u) {
//        List<DeclarationMariage> list = null;
//        String etat = "Rejeter";
//        if(session == null)
//            session = HibernateUtil.getSessionFactory();
//        String sql = "FROM Declaration WHERE etat = '" + etat + "' and usersByCreateurUserId = '" + u.getUserId() + "'";
//        try {
//            session.beginTransaction();
//            list = session.createQuery(sql).list();
//            session.beginTransaction().commit();
//        } catch (Exception e) {
//            session.beginTransaction().rollback();
//        }
//        return list;
//    }

    @Override
    public boolean deleteDeclarationMariage(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String numActe(String annee) {
    	if (session == null)
	    	session = HibernateUtil.getSessionFactory();
	    	Query q = session.createQuery(" select count(*) from DeclarationMariage a where a.date_creation like :annee and numero_acte != :num and etat = :valider ")
	    			.setString("annee", "%"+annee+"%").setString("num", "").setString("valider", "Valider");
	    	Long x = ((Long)q.uniqueResult())+1;
	    	return x.toString();
    }

    @Override
    public boolean updateRegistreMariage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String returnNumActe(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteActeMariage(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean jugement(String num, String annee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean jugementDeclaration(String num, String annee, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationMariage> searchAM(Search s) {
        List<DeclarationMariage> list = null;
        if (session == null) {
            session = HibernateUtil.getSessionFactory();
        }
        if(!"".equals(s.getNumActe()) && !"".equals(s.getNumReg())){
        	Query q = session.createQuery("FROM DeclarationMariage n WHERE n.etat = :etat and n.numero_acte = :acte and n.date_creation like :date")
        			.setString("etat", "Valider").setString("acte", s.getNumActe()).setString("date", "%"+s.getNumReg()+"%");
    		list = q.list();
    	}
    	else if(!"".equals(s.getNumActe()) && "".equals(s.getNumReg())){
    		Query q = session.createQuery("FROM DeclarationMariage n WHERE n.etat = :etat and n.numero_acte = :acte")
        			.setString("etat", "Valider").setString("acte", s.getNumActe());
    		list = q.list();
    	}
    	else if(!"".equals(s.getNumReg()) && "".equals(s.getNumActe())){
    		Query q = session.createQuery("FROM DeclarationMariage n WHERE n.etat = :etat and n.date_creation like :date")
        			.setString("etat", "Valider").setString("date", "%"+s.getNumReg()+"%");
    		list = q.list();
    	}
        return list;
    }


	@Override
	public boolean updateDeclarationMariage(DeclarationMariage dec) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			if (session == null)
				session = HibernateUtil.getSessionFactory();
			// D�but de la transaction Hibernate
			Transaction tx = session.beginTransaction();

			// Cherche s'il existe d�j� dans la BDD
			DeclarationMariage a = this.findById(dec.getId());

			// Si l'acte n'existe pas dans la BDD : sauvegarde
			if (a == null){
				session.save(dec);
				flag=true;
			}
			// Sinon on est dans le cas d'une modification de l'acte : merge
			else{
				session.merge(dec);
				flag = true;
			}
			

			// Fin de transaction : synchronisation du contexte de persistance
			tx.commit();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            flag = false;
        }
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyNumeroJugement(String num, String annee) {
		// TODO Auto-generated method stub
				boolean flag=false;
				 List<DeclarationMariage> list = null;
			       if (session == null)
					session = HibernateUtil.getSessionFactory();
					
					Query q = session.createQuery("from DeclarationMariage a where a.date_Jugement like :dateJ and a.numero_Jugement = :num")
							.setString("dateJ", "%"+annee+"%").setString("num", num);
					list = q.list();
					if(list.size() > 0)
						flag=true;
					return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyNumeroJugement(String num, String annee, Integer id) {
		// TODO Auto-generated method stub
		boolean flag=false;
		 List<DeclarationMariage> list = null;
	       if (session == null)
			session = HibernateUtil.getSessionFactory();
			
			Query q = session.createQuery("from DeclarationMariage a where a.date_Jugement like :dateJ and a.numero_Jugement = :num and id != :id")
					.setString("dateJ", "%"+annee+"%").setString("num", num).setInteger("id", id);
			list = q.list();
			if(list.size() > 0)
				flag=true;
			return flag;
	}

   
    
}
