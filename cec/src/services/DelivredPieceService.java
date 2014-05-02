/**
 * 
 */
package services;

import java.util.List;

import models.DelivredPieces;
import models.PiecesAnnexes;
import models.Users;
import util.Factory;
import dao.PieceDelivredDAO;

/**
 * @author admin
 *
 */
public class DelivredPieceService {

	private PieceDelivredDAO daoPiece = Factory.getPieceDelivredDAO();
	
	public boolean addPiece(PiecesAnnexes p){
		return daoPiece.addDelivred(p);
	}
	
	public List<DelivredPieces> getAllPiecesDel(){
		return daoPiece.findAll();
	}
	
	public List<DelivredPieces> getAllPieceBU(Users u){
		return daoPiece.findByUser(u);
	}
	
	public boolean updatePiece(DelivredPieces p){
		return daoPiece.updateDelivred(p);
	}
}
