/**
 * 
 */
package dao;

import java.io.Serializable;
import java.util.List;

import models.DelivredPieces;
import models.PiecesAnnexes;
import models.Users;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import util.MyUtil;
import util.Tools;

/**
 * @author admin
 *
 */
public class PieceDelivredDAOImp implements PieceDelivredDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Session session = null;

	@Override
	public boolean addDelivred(PiecesAnnexes p) {
		// TODO Auto-generated method stub
		boolean flag;
		DelivredPieces d = new DelivredPieces();
        if(session == null)
            session = HibernateUtil.getSessionFactory();
        try {
        	
        	d.setPiecedelivred(p);
        	d.setDate_delivre(Tools.getCurrentDateTime());
        	d.setPaiement(false);
        	d.setUserdelivred(MyUtil.getUserLogged());
            session.beginTransaction();
            session.save(d);
            session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }

       return flag;
	}

	@Override
	public boolean updateDelivred(DelivredPieces d) {
		// TODO Auto-generated method stub
		boolean flag;
        if(session == null)
            session = HibernateUtil.getSessionFactory();
        try {
        	session.merge(d);
        	session.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            session.beginTransaction().rollback();
        }

       return flag;
	}

	@Override
	public boolean deleteDelivred(DelivredPieces d) {
		// TODO Auto-generated method stub

		boolean flag;
        if(session == null)
            session = HibernateUtil.getSessionFactory();
        try {
       	 
            session.beginTransaction();
            session.delete(d);
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
	public List<DelivredPieces> findByUser(Users u) {
		// TODO Auto-generated method stub
		List<DelivredPieces> model = null;
		 if (session == null)
				session = HibernateUtil.getSessionFactory();
			Query q = session.createQuery("from DelivredPieces a where a.userdelivred = :id and a.paiement = :paie")
					.setParameter("id", u).setBoolean("paie", false);
			
			model = q.list();
			
			return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelivredPieces> findByPiece(PiecesAnnexes p) {
		// TODO Auto-generated method stub
		List<DelivredPieces> model = null;
		 if (session == null)
				session = HibernateUtil.getSessionFactory();
			Query q = session.createQuery("from DelivredPieces a where a.piecedelivred = :id and a.paiement = :paie")
					.setParameter("id", p).setBoolean("paie", false);
			
			model = q.list();
			
			return model;
	}

	@Override
	public boolean updateByUser(Users u) {
		// TODO Auto-generated method stub
		boolean flag=true;
		List<DelivredPieces> l = this.findByUser(u);
		for(DelivredPieces list:l){
			list.setDate_paiement(Tools.getCurrentDateTime());
			list.setPaiement(true);
			if(!this.updateDelivred(list))
				flag = false;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelivredPieces> findAll() {
		// TODO Auto-generated method stub
		List<DelivredPieces> model = null;
		
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DelivredPieces where paiement = :paie").setBoolean("paie", false);
		model = q.list();
		return model;
	}

}
