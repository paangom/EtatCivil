/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;

import models.DeclarationDeces;
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
public class ActeDecesDAOImp implements ActeDecesDAO, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Session session = null;

    @Override
    public boolean SaveDeclaration(DeclarationDeces acte) {
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

    @Override
    public DeclarationDeces findById(int idActe) {
        DeclarationDeces model = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery("from DeclarationDeces a where a.id= :id")
				.setInteger("id", idActe);
		model = (DeclarationDeces) q.uniqueResult();
        return model;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationDeces> getRegistre(String numRegistre) {
        List<DeclarationDeces> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationDeces a where a.etat= :etat and date_creation like :date")
				.setString("etat", "Valider").setString("date", "%"+numRegistre+"%");
		list = q.list();
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationDeces> getAllDeclaration() {
        List<DeclarationDeces> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationDeces a where a.etat = :etat")
				.setString("etat", "Instance");
		list = q.list();
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationDeces> getAllDeclarationByUser(Users u) {
        List<DeclarationDeces> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationDeces a where a.createurDeces = :createur_user and a.etat = :Instance")
				.setInteger("createur_user", u.getUserId()).setString("Instance", "Instance");
		list = q.list();
		return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationDeces> getAllDeclarationRejeterByUser(Users u) {
        List<DeclarationDeces> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationActeDece a where a.createurDeces = :createur_user and a.etat = :Instance")
				.setInteger("createur_user", u.getUserId()).setString("Instance", "Rejeter");
		list = q.list();
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationDeces> getAllDeclarationValider() {
        List<DeclarationDeces> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationDeces a where a.etat= :etat")
				.setString("etat", "valider");
		list = q.list();
        return list;
    }

//    @Override
//    public boolean validateDeclarationDece(DeclarationDeces dec) {
//        boolean flag;
//        if (session == null) {
//            session = HibernateUtil.getSessionFactory();
//        }
//        DeclarationDeces d = this.findById(dec.getId());
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
//    public boolean invalidateDeclarationDece(DeclarationDeces dec) {
//        boolean flag;
//        if (session == null) {
//            session = HibernateUtil.getSessionFactory();
//        }
//        DeclarationDeces d = this.findById(dec.getId());
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

    @Override
    public List<DeclarationDeces> getCinqLastDeclaration() {
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

    @Override
    public String returnNumActe(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String numActe(String annee) {
    	// TODO Auto-generated method stub
    	if (session == null)
    	session = HibernateUtil.getSessionFactory();
    	Query q = session.createQuery(" select count(*) from DeclarationDeces a where a.date_creation like :annee and numero_acte != :num and etat = :valider ")
    			.setString("annee", "%"+annee+"%").setString("num", "").setString("valider", "Valider");
    	Long x = ((Long)q.uniqueResult())+1;
    	return x.toString();
    }

    @Override
    public boolean deleteActeDeces(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateDeclaration(DeclarationDeces acte) {
    	boolean flag=false;
    	// R�cup�ration d'une session Hibernate
    			if (session == null)
    				session = HibernateUtil.getSessionFactory();
    			// D�but de la transaction Hibernate
    			Transaction tx = session.beginTransaction();

    			// Cherche s'il existe d�j� dans la BDD
    			DeclarationDeces a = this.findById(acte.getId());

    			// Si l'acte n'existe pas dans la BDD : sauvegarde
    			if (a == null){
    				session.save(acte);
    				flag=true;
    			}
    			// Sinon on est dans le cas d'une modification de l'acte : merge
    			else{
    				session.merge(acte);
    				flag=true;
    			}

    			// Fin de transaction : synchronisation du contexte de persistance
    			tx.commit();
    			
    			return flag;
    }

    @Override
    public boolean deleteActeDece(int idActe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeclarationDeces> findByActeNum(int numActe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeclarationDeces> getAllDeclarationByDate(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeclarationDeces> findDeclarationByIdUser(int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationDeces> searchAD(Search s) {
        List<DeclarationDeces> list = null;
        if (session == null) {
            session = HibernateUtil.getSessionFactory();
        }
        if(!"".equals(s.getNumActe()) && !"".equals(s.getNumReg())){
        	Query q = session.createQuery("FROM DeclarationDeces n WHERE n.etat = :etat and n.numero_acte = :acte and n.date_creation like :date")
        			.setString("etat", "Valider").setString("acte", s.getNumActe()).setString("date", "%"+s.getNumReg()+"%");
    		list = q.list();
    	}
    	else if(!"".equals(s.getNumActe()) && "".equals(s.getNumReg())){
    		Query q = session.createQuery("FROM DeclarationDeces n WHERE n.etat = :etat and n.numero_acte = :acte")
        			.setString("etat", "Valider").setString("acte", s.getNumActe());
    		list = q.list();
    	}
    	else if(!"".equals(s.getNumReg()) && "".equals(s.getNumActe())){
    		Query q = session.createQuery("FROM DeclarationDeces n WHERE n.etat = :etat and n.date_creation like :date")
        			.setString("etat", "Valider").setString("date", "%"+s.getNumReg()+"%");
    		list = q.list();
    	}
        return list;
    }

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyNumeroJugement(String num, String annee) {
		// TODO Auto-generated method stub
				boolean flag=false;
				 List<DeclarationDeces> list = null;
			       if (session == null)
					session = HibernateUtil.getSessionFactory();
					
					Query q = session.createQuery("from DeclarationDeces a where a.date_jugement like :dateJ and a.num_jugement = :num")
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
		 List<DeclarationDeces> list = null;
	       if (session == null)
			session = HibernateUtil.getSessionFactory();
			
			Query q = session.createQuery("from DeclarationDeces a where a.date_jugement like :dateJ and a.num_jugement = :num and id != :id")
					.setString("dateJ", "%"+annee+"%").setString("num", num).setInteger("id", id);
			list = q.list();
			if(list.size() > 0)
				flag=true;
			return flag;
	}

	@Override
	public int findInstanceByDate(String date) {
		// TODO Auto-generated method stub
		int x=0;
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationDeces a where a.date_creation like  :annee and etat = :etat")
				.setString("annee", "%"+date+"%").setString("etat", "Instance");
		x = q.list().size();
        return x;
	}

	@Override
	public int findValidateByDate(String date) {
		// TODO Auto-generated method stub
		int x=0;
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationDeces a where a.date_creation like  :annee and etat = :etat")
				.setString("annee", "%"+date+"%").setString("etat", "Valider");
		x = q.list().size();
        return x;
	}

}
