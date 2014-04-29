/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import dao.ActeDecesDAO;
import dao.ActeDecesDAOImp;
import dao.ActeMariageDAO;
import dao.ActeMariageDAOImp;
import dao.ActeNaissanceDAO;
import dao.ActeNaissanceDAOImp;
import dao.CentreDAO;
import dao.CentreDAOImp;
import dao.ConnectedDAO;
import dao.ConnectedDAOImp;
import dao.PieceDelivredDAO;
import dao.PieceDelivredDAOImp;
import dao.PiecesAnnexesDAO;
import dao.PiecesAnnexesDAOImp;
import dao.UserDAO;
import dao.UserDAOImp;


/**
 *
 * @author sambasow
 */
public class Factory {
	
	public static PieceDelivredDAO getPieceDelivredDAO(){
		return new PieceDelivredDAOImp();
	}

	public static UserDAO getUserUserDAO() {
		// TODO Auto-generated method stub
		return new UserDAOImp();
	}

	public static CentreDAO getCentreDAO() {
		// TODO Auto-generated method stub
		return new CentreDAOImp();
	}
	

    public static ActeNaissanceDAO getActeNaissanceDAO(){
        return new ActeNaissanceDAOImp();
    }
    
    public static ActeMariageDAO getActeMariageDAO(){
        return new ActeMariageDAOImp();
    }
    
    public static ActeDecesDAO getActeDecesDAO(){
        return new ActeDecesDAOImp();
    }
    
    
    public static PiecesAnnexesDAO getPiecesAnnexesDAO(){
        return new PiecesAnnexesDAOImp();
    }
    
    public static ConnectedDAO getConnectedDAO(){
    	return new ConnectedDAOImp();
    }
    
}
