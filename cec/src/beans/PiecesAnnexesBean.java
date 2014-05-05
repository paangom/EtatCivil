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
	
	@SuppressWarnings("static-access")
	public void savePieces(){
		if(this.pieceTOAdd.getLibelle() != null){
			if(this.pieceTOAdd.getPrix() == null)
				this.pieceTOAdd.setPrix("0");
			this.pieceTOAdd.setDate_creation(Tools.getCurrentDateTime());
			if(pService.createPiece(this.pieceTOAdd)){
				//allPieces = pService.allPieces();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Le document "+this.pieceTOAdd.getLibelle()+" a été ajoutée avec succès!");
				FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
	        	
	        	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
			else{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Impossible d'ajouter le document "+this.pieceTOAdd.getLibelle()+"!");
				FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
	        	
	        	context.getExternalContext().getFlash().setKeepMessages(true);
			}
        	
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Aucun libelé n'est attribué pour ce document!");
			FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}
	
	@SuppressWarnings("static-access")
	public void updatePieces(){
		if(this.pieceToConsult.getLibelle() != null){
			if(this.pieceToConsult.getPrix() == null)
				this.pieceToConsult.setPrix("0");
			pService.updatePieces(this.pieceToConsult);
			//allPieces = pService.allPieces();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Le document "+this.pieceToConsult.getLibelle()+" a été mise à jour!");
			FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Erreur de la modification de la pièce "+this.pieceToConsult.getLibelle());
			FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}
	
	@SuppressWarnings("static-access")
	public void deletePieces(){
		pService.deletePieces(this.pieceToConsult.getId());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Le document "+this.pieceToConsult.getLibelle()+" a été supprimé avec succès!");
		FacesContext context = FacesContext.getCurrentInstance();
        context.getCurrentInstance().addMessage(null, message);
    	
    	context.getExternalContext().getFlash().setKeepMessages(true);
	}

}
