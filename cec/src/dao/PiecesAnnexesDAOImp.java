package dao;

import java.util.List;

import models.PiecesAnnexes;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;



public class PiecesAnnexesDAOImp implements PiecesAnnexesDAO{
	
	private Session session = null;

	@Override
	public void createPieces(PiecesAnnexes p) {
		// TODO Auto-generated method stub
		if (session == null)
			session = HibernateUtil.getSessionFactory();
        // Debuter une transaction
		Transaction t = session.beginTransaction();

		// Sauvegarde du centre dans la base
		
		session.save(p);
		t.commit();
	}

	@Override
	public void updatePieces(PiecesAnnexes p) {
		// TODO Auto-generated method stub
		// Recuperation d'une session Hibernate
				if (session == null)
					session = HibernateUtil.getSessionFactory();
				// Début de la transaction Hibernate
				Transaction tx = session.beginTransaction();

				// Cherche s'il existe déjà dans la BDD
				PiecesAnnexes p2 = this.findById(p.getId());

				// Si l'acte n'existe pas dans la BDD : sauvegarde
				if (p2 == null)
					session.save(p);
				// Sinon on est dans le cas d'une modification de l'acte : merge
				else
					session.update(p);

				// Fin de transaction : synchronisation du contexte de persistance
				tx.commit();
	}

	@Override
	public void deletePieces(int p) {
		// TODO Auto-generated method stub
		// RecupŽration d'une session
		PiecesAnnexes piece=null;
				if (session == null)
					session = HibernateUtil.getSessionFactory();
				
				// Debut de la transaction
				Transaction tx = session.beginTransaction();
				
				piece = (PiecesAnnexes) session.get(PiecesAnnexes.class, p);
				session.delete(piece);
				
				// Fin de la transaction
				tx.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PiecesAnnexes> allPieces() {
		// TODO Auto-generated method stub
		if (session == null)
			session = HibernateUtil.getSessionFactory();
	
		 Query q = session.createQuery("from PiecesAnnexes");
		 List<PiecesAnnexes> l = q.list();
		 
		return l;
	}


	@Override
	public PiecesAnnexes findById(int id) {
		// TODO Auto-generated method stub
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery("from PiecesAnnexes c where c.id= :code")
				.setInteger("code", id);
		PiecesAnnexes c = (PiecesAnnexes) q.uniqueResult();
		return c;
	}

	@Override
	public PiecesAnnexes findByCode(int code) {
		// TODO Auto-generated method stub
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery("from PiecesAnnexes c where c.code = :code")
				.setInteger("code", code);
		PiecesAnnexes c = (PiecesAnnexes) q.uniqueResult();
		return c;
	}

}
