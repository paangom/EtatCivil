package services;

import java.util.List;

import models.PiecesAnnexes;
import util.Factory;
import dao.PiecesAnnexesDAO;

public class PiecesAnnexesServices {
	
	private PiecesAnnexesDAO pieceDAO = Factory.getPiecesAnnexesDAO();
	
	public List<PiecesAnnexes> allPieces(){
		return pieceDAO.allPieces();
	}
	
	public PiecesAnnexes findById(int idPiece) {
		return pieceDAO.findById(idPiece);
	}
	
	public void createPiece(PiecesAnnexes p){
		pieceDAO.createPieces(p);
	}
	
	public void updatePieces(PiecesAnnexes p){
		pieceDAO.updatePieces(p);
	}
	
	public void deletePieces(int id){
		pieceDAO.deletePieces(id);
	}
	
	

}
