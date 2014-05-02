/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import models.DeclarationDeces;
import services.ActeDecesServices;
import util.MyUtil;
import util.Tools;


public class DecDCBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActeDecesServices dcServ = new ActeDecesServices();
    private DeclarationDeces decToAdd = null;
    private List<DeclarationDeces> filterDeces = null;
    @SuppressWarnings("unused")
	private DeclarationDeces decToConsult = null;
    private List<DeclarationDeces> allDec;
    private List<DeclarationDeces> allDecByUser;
    private List<DeclarationDeces> allActe;

    /**
     * Creates a new instance of decDCBean
     */
    public DecDCBean() {
        //decToUpdate = new Declaration();
        if (this.decToAdd == null) {
            this.decToAdd = new DeclarationDeces();
        }
    }

    public DeclarationDeces getDecToAdd() {
        return decToAdd;
    }

    public List<DeclarationDeces> getAllDec() {
        allDec = dcServ.getAllDeclarationDece();
        return allDec;
    }

    public List<DeclarationDeces> getAllDecByUser() {
        allDecByUser = dcServ.getAllDeclarationDeceByUser(MyUtil.getUserLogged());
        return allDecByUser;
    }

    public List<DeclarationDeces> getAllActe() {
        allActe = dcServ.getRegistreDece();
        return allActe;
    }
    
    
    @SuppressWarnings("static-access")
	public String saveDeclarationDeces(){
        String route = "";
        if(MyUtil.getProfil() != null){
        if("Jugement".equalsIgnoreCase(Tools.typeDeclarationDeces(Tools.formatDay(this.decToAdd.getDate_d())))){
        	if("".equals(this.decToAdd.getNum_jugement()) || "".equals(this.decToAdd.getDate_j()) || "".equals(this.decToAdd.getTribunal())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Cette déclaration est un jugement. Remplir les champs correspondants!");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if(dcServ.verifyNumeroJugement(this.decToAdd.getNum_jugement(), Tools.formatDay(this.decToAdd.getDate_j()).substring(6, 10))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Ce numéro de jugement est déjà attribué pour cette année.");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decToAdd.getDegre_parente().equalsIgnoreCase("Autre")) && ("".equals(this.decToAdd.getAdresse_declarant()) || "".equals(this.decToAdd.getPrenom_declarant()) || "".equals(this.decToAdd.getNom_declarant()) || "".equals(this.decToAdd.getProfession_declarant()) || "".equals(this.decToAdd.getNum_identification_declarant()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations du déclarant.");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decToAdd.getDegre_parente().equalsIgnoreCase("Pére")) && ("".equals(this.decToAdd.getPrenom_pere()) || "".equals(this.decToAdd.getNom_pere()) || "".equals(this.decToAdd.getDomicile_pere()) || "".equals(this.decToAdd.getProfession_pere()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations du pére.");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decToAdd.getDegre_parente().equalsIgnoreCase("Mére")) && ("".equals(this.decToAdd.getPrenom_mere()) || "".equals(this.decToAdd.getNom_mere()) || "".equals(this.decToAdd.getDomicile_mere()) || "".equals(this.decToAdd.getProfession_mere()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations de la mére.");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
        		this.decToAdd.setDate_deces(Tools.formatDay(this.decToAdd.getDate_d()));
                this.decToAdd.setDate_naissance_defunt(Tools.formatDay(this.decToAdd.getDate_naissanceDefunt()));
                this.decToAdd.setDate_jugement(Tools.formatDay(this.decToAdd.getDate_j()));
                this.decToAdd.setHeure_deces(this.decToAdd.getHeure_d().toString().substring(11, 16));
                this.decToAdd.setType_declaration(Tools.formatDay(this.decToAdd.getDate_naissanceDefunt()));
                this.decToAdd.setDate_creation(Tools.getCurrentDateTime());
                this.decToAdd.setEtat("Instance");
                this.decToAdd.setCreateurDeces(MyUtil.getUserLogged());
                
                
                if (dcServ.saveDeclarationDC(decToAdd)) {
                	
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Déclaration sauvegardée avec succés!");
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                    route="/views/" + MyUtil.getProfil() + "/liste-declaration/deces?faces-redirect=true";

                } else {
                	
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Erreur dans le sauvegarde de la déclaration!");
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                }
        	}
        	
        }

    	else if((this.decToAdd.getDegre_parente().equalsIgnoreCase("Autre")) && ("".equals(this.decToAdd.getAdresse_declarant()) || "".equals(this.decToAdd.getPrenom_declarant()) || "".equals(this.decToAdd.getNom_declarant()) || "".equals(this.decToAdd.getProfession_declarant()) || "".equals(this.decToAdd.getNum_identification_declarant()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations du déclarant.");
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else if((this.decToAdd.getDegre_parente().equalsIgnoreCase("Père")) && ("".equals(this.decToAdd.getPrenom_pere()) || "".equals(this.decToAdd.getNom_pere()) || "".equals(this.decToAdd.getDomicile_pere()) || "".equals(this.decToAdd.getProfession_pere()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations du pére.");
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else if((this.decToAdd.getDegre_parente().equalsIgnoreCase("Mère")) && ("".equals(this.decToAdd.getPrenom_mere()) || "".equals(this.decToAdd.getNom_mere()) || "".equals(this.decToAdd.getDomicile_mere()) || "".equals(this.decToAdd.getProfession_mere()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations de la mére.");
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
        else{
        	this.decToAdd.setDate_deces(Tools.formatDay(this.decToAdd.getDate_d()));
            this.decToAdd.setDate_naissance_defunt(Tools.formatDay(this.decToAdd.getDate_naissanceDefunt()));
            this.decToAdd.setDate_jugement(Tools.formatDay(this.decToAdd.getDate_j()));
            this.decToAdd.setHeure_deces(this.decToAdd.getHeure_d().toString().substring(11, 16));
            this.decToAdd.setType_declaration(Tools.formatDay(this.decToAdd.getDate_naissanceDefunt()));
            this.decToAdd.setDate_creation(Tools.getCurrentDateTime());
            this.decToAdd.setEtat("Instance");
            this.decToAdd.setCreateurDeces(MyUtil.getUserLogged());
            
            
            if (dcServ.saveDeclarationDC(decToAdd)) {
            	
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Déclaration sauvegardée avec succés!");
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
                route="/views/" + MyUtil.getProfil() + "/liste-declaration/deces?faces-redirect=true";

            } else {
            	
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Erreur dans le sauvegarde de la déclaration!");
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            }
        }
        }
        else
        	route =MyUtil.pathLogin();

        return route; 
        
    }

	public List<DeclarationDeces> getFilterDeces() {
		return filterDeces;
	}

	public void setFilterDeces(List<DeclarationDeces> filterDeces) {
		this.filterDeces = filterDeces;
	}


}
