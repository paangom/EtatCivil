package dao;

import java.util.List;

import models.PiecesAnnexes;

public interface PiecesAnnexesDAO {
	
	public void createPieces(PiecesAnnexes p);
	
	public void updatePieces(PiecesAnnexes p);
	
	public void deletePieces(int p);
	
	public List<PiecesAnnexes> allPieces();
	
	public PiecesAnnexes findById(int id);

}
