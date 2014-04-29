package util;

import models.Connected;
import models.PiecesAnnexes;
import models.Users;

import org.hibernate.Query;
import org.hibernate.Session;

import services.DelivredPieceService;
import services.PiecesAnnexesServices;

public class Test {

	private static Session session = null;
	public DelivredPieceService dSevice = new DelivredPieceService();
	PiecesAnnexesServices pService = new PiecesAnnexesServices();
	
	public static PiecesAnnexes findByCode(int code) {
		// TODO Auto-generated method stub
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		Query q = session.createQuery("from PiecesAnnexes c where c.code = :code")
				.setInteger("code", code);
		PiecesAnnexes c = (PiecesAnnexes) q.uniqueResult();
		return c;
	}
	    
	public static void main(String[] args) {
		DelivredPieceService dService = new DelivredPieceService();
		PiecesAnnexesServices pService = new PiecesAnnexesServices();
		System.out.println(dService.addPiece(pService.findByCode(200)));
	}

}
