package services;

import java.io.Serializable;
import java.util.List;

import models.Connected;
import models.Users;
import dao.ConnectedDAO;
import util.Factory;

public class ConnectedService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectedDAO conDAO = Factory.getConnectedDAO();
	
	public boolean addConnected(Users con){
		return conDAO.addConnected(con);
	}
	
	public boolean deleteConnected(Connected con){
		return conDAO.deleteConnected(con);
	}
	
	public boolean updateConnected(Users con){
		return conDAO.updateConnected(con);
	}
	
	public List<Connected> findAllUsers(){
		return conDAO.findAllUsers();
	}
	
	public Connected findUser(Users id){
		return conDAO.findUser(id);
	}

}
