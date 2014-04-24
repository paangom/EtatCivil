package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import models.PiecesAnnexes;
import services.PiecesAnnexesServices;
import util.Tools;

public class PiecesAnnexesBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PiecesAnnexesServices pService = new PiecesAnnexesServices();
	private PiecesAnnexes pieceTOAdd = null;
	private PiecesAnnexes pieceToConsult = null;
	private List<PiecesAnnexes> allPieces = null;
	
	public PiecesAnnexesBean(){
		if(this.pieceTOAdd == null){
			this.pieceTOAdd = new PiecesAnnexes();
		}
	}

	public PiecesAnnexes getPieceTOAdd() {
		return pieceTOAdd;
	}

	public void setPieceTOAdd(PiecesAnnexes pieceTOAdd) {
		this.pieceTOAdd = pieceTOAdd;
	}

	public PiecesAnnexes getPieceToConsult() {
		return pieceToConsult;
	}

	public void setPieceToConsult(PiecesAnnexes pieceToConsult) {
		this.pieceToConsult = pieceToConsult;
	}

	public List<PiecesAnnexes> getAllPieces() {
		allPieces = pService.allPieces();
		return allPieces;
	}

	public void setAllPieces(List<PiecesAnnexes> allPieces) {
		this.allPieces = allPieces;
	}
	
	public void savePieces(){
		if(this.pieceTOAdd.getLibelle() != null){
			if(this.pieceTOAdd.getPrix() == null)
				this.pieceTOAdd.setPrix("0");
			this.pieceTOAdd.setDate_creation(Tools.getCurrentDateTime());
			pService.createPiece(this.pieceTOAdd);
			//allPieces = pService.allPieces();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Le document "+this.pieceTOAdd.getLibelle()+" a �t� ajout�e avec succès!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aucun libel� n'est attribu� pour ce document!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void updatePieces(){
		if(this.pieceToConsult.getLibelle() != null){
			if(this.pieceToConsult.getPrix() == null)
				this.pieceToConsult.setPrix("0");
			pService.updatePieces(this.pieceToConsult);
			//allPieces = pService.allPieces();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Le document "+this.pieceToConsult.getLibelle()+" a �t� mise � jour!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur de la modification de la pi�ce "+this.pieceToConsult.getLibelle(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void deletePieces(){
		pService.deletePieces(this.pieceToConsult.getId());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La pi�ce "+this.pieceToConsult.getLibelle()+" a �t� supprim�e avec succ�s!", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
