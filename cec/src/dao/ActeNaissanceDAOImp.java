/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import models.DeclarationNaissance;
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
public class ActeNaissanceDAOImp implements ActeNaissanceDAO {

    private Session session = null;

    @Override
    public boolean addDeclaration(DeclarationNaissance d) {
        boolean flag;
        if (session == null) {
            session = HibernateUtil.getSessionFactory();
        }
        try {
        	Transaction tx = session.beginTransaction();
			session.save(d);

			// Fin de transaction : synchronisation du contexte de persistance
			tx.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public DeclarationNaissance getDeclaration(int id) {
        DeclarationNaissance model = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery("from DeclarationNaissance a where a.id = :id")
				.setInteger("id", id);
		model = (DeclarationNaissance) q.uniqueResult();
        return model;
    }

    @Override
    public DeclarationNaissance getActeNaissance(int numActe) {
        DeclarationNaissance model = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery("from DeclarationNaissance a where a.numero_acte = :numActe and a.etat = :valider")
				.setInteger("numActe", numActe).setString("valider", "valider"); 
		model = (DeclarationNaissance) q.uniqueResult();
        return model;
    }


    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationNaissance> getAllDeclaration() {
    	List<DeclarationNaissance> list = null;
    	if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationNaissance a where a.etat= :etat")
				.setString("etat", "Instance");
		list = q.list();
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationNaissance> getAllActe() {
        List<DeclarationNaissance> list = null;
        if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationNaissance a where a.etat= :etat")
				.setString("etat", "Valider");
		list = q.list();
        return list;
    }

    @Override
    public List<DeclarationNaissance> getAllActeByRegistre(int registre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateActe(DeclarationNaissance dec) {
        boolean flag=false;
        if (session == null) {
            session = HibernateUtil.getSessionFactory();
        }
        DeclarationNaissance d = this.getDeclaration(dec.getId());
        try {
            session.beginTransaction();
            if (d != null) {
                session.merge(dec);
                session.beginTransaction().commit();
            }
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }
        return flag;
    }

//    @SuppressWarnings("unchecked")
//	@Override
    public List<DeclarationNaissance> getAllDeclarationInstance() {
        List<DeclarationNaissance> list = null;
//        String etat = "Instance";
//        if (session == null) {
//            session = HibernateUtil.getSessionFactory();
//        }
//        String sql = "FROM Declaration WHERE etat = '" + etat + "'";
//        try {
//            session.beginTransaction();
//            list = session.createQuery(sql).list();
//            session.beginTransaction().commit();
//        } catch (Exception e) {
//            session.beginTransaction().rollback();
//        }
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationNaissance> getAllDecByUser(Users u) {
        List<DeclarationNaissance> list = null;
       if (session == null)
		session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationNaissance a where a.createurNaissance= :createur_user and a.etat = :Instance")
				.setInteger("createur_user", u.getUserId()).setString("Instance", "Instance");;
			list = q.list();
       	return list;
    }

//    @SuppressWarnings("unchecked")
//	@Override
    public List<DeclarationNaissance> getAllDecByUserReject(Users u) {
        List<DeclarationNaissance> list = null;
//        String etat = "Rejeter";
//        if (session == null) {
//            session = HibernateUtil.getSessionFactory();
//        }
//        String sql = "FROM Declaration WHERE etat = '" + etat + "' and usersByCreateurUserId = '" + u.getUserId() + "'";
//        try {
//            session.beginTransaction();
//            list = session.createQuery(sql).list();
//            session.beginTransaction().commit();
//        } catch (Exception e) {
//            session.beginTransaction().rollback();
//        }
        return list;
    }

    @Override
    public boolean updateDeclaration(DeclarationNaissance dec) {
        boolean flag=false;
//       // R�cup�ration d'une session Hibernate
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		// D�but de la transaction Hibernate
		Transaction tx = session.beginTransaction();

		// Cherche s'il existe d�j� dans la BDD
		DeclarationNaissance a = this.getDeclaration(dec.getId());

		// Si l'acte n'existe pas dans la BDD : sauvegarde
		if (a == null){
			session.save(dec);
			flag=true;
		// Sinon on est dans le cas d'une modification de l'acte : merge
		}
		else{
			session.merge(dec);
			flag=true;
		}
		// Fin de transaction : synchronisation du contexte de persistance
		tx.commit();
        return flag;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<DeclarationNaissance> searchAN(Search s) {
        List<DeclarationNaissance> list = null;
        if (session == null) {
            session = HibernateUtil.getSessionFactory();
        }
        if(!"".equals(s.getNumActe()) && !"".equals(s.getNumReg())){
        	Query q = session.createQuery("FROM DeclarationNaissance n WHERE n.etat = :etat and n.numero_acte = :acte and n.date_creation like :date")
        			.setString("etat", "Valider").setString("acte", s.getNumActe()).setString("date", "%"+s.getNumReg()+"%");
    		list = q.list();
    		
    	}
    	else if(!"".equals(s.getNumActe()) && "".equals(s.getNumReg())){
    		Query q = session.createQuery("FROM DeclarationNaissance n WHERE n.etat = :etat and n.numero_acte = :acte")
        			.setString("etat", "Valider").setString("acte", s.getNumActe());
    		list = q.list();
    	}
    	else if("".equals(s.getNumReg()) && !"".equals(s.getNumActe())){
    		Query q = session.createQuery("FROM DeclarationNaissance n WHERE n.etat = :etat and n.date_creation like :date")
        			.setString("etat", "Valider").setString("date", "%"+s.getNumReg()+"%");
    		list = q.list();
    	}
    	        
        return list;
    }


	@Override
	public String numeroActe(String annee) {
		// TODO Auto-generated method stub
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery(" select count(*) from DeclarationNaissance a where a.date_creation like :annee and numero_acte != :num and etat = :valider ")
				.setString("annee", "%"+annee+"%").setString("num", "").setString("valider", "Valider");
		Long x = ((Long)q.uniqueResult())+1;
		return x.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyNumeroJugement(String num, String annee) {
		// TODO Auto-generated method stub
		boolean flag=false;
		 List<DeclarationNaissance> list = null;
	       if (session == null)
			session = HibernateUtil.getSessionFactory();
			
			Query q = session.createQuery("from DeclarationNaissance a where a.date_jugement like :dateJ and a.numero_jugement = :num")
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
		 List<DeclarationNaissance> list = null;
	       if (session == null)
			session = HibernateUtil.getSessionFactory();
			
			Query q = session.createQuery("from DeclarationNaissance a where a.date_jugement like :dateJ and a.numero_jugement = :num and id != :id")
					.setString("dateJ", "%"+annee+"%").setString("num", num).setInteger("id", id);
			list = q.list();
			if(list.size() > 0)
				flag=true;
			return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeclarationNaissance> registresCurrentYear(String year) {
		// TODO Auto-generated method stub
		List<DeclarationNaissance> list = null;
    	if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationNaissance a where a.date_creation like  :annee and etat = :etat")
				.setString("annee", "%"+year+"%").setString("etat", "Valider");
		list = q.list();
        return list;
	}

	@Override
	public int findInstanceByDate(String date) {
		// TODO Auto-generated method stub
		int x=0;
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationNaissance a where a.date_creation like  :annee and etat != :etat")
				.setString("annee", "%"+date+"%").setString("etat", "Valider");
		x = q.list().size();
        return x;
	}

	@Override
	public int findValidateByDate(String date) {
		// TODO Auto-generated method stub
		int x=0;
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationNaissance a where a.date_creation like  :annee and etat = :etat")
				.setString("annee", "%"+date+"%").setString("etat", "Valider");
		x = q.list().size();
        return x;
	}


}
