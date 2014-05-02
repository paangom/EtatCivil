/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import models.DeclarationNaissance;

import org.primefaces.context.RequestContext;

import services.ActeNaissanceServices;
import util.MyUtil;
import util.Tools;

/**
 *
 * @author sambasow
 */

public class DecANBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActeNaissanceServices acteService = new ActeNaissanceServices();
	private DeclarationNaissance decToAdd = null;
    private DeclarationNaissance decToConsult = null;
    private List<DeclarationNaissance> filterNaissance = null;
    private List<DeclarationNaissance> allDec;
    private List<DeclarationNaissance> allDecByUser;
    private List<DeclarationNaissance> allActe;
    

    /**
     * Creates a new instance of decANBean
     */
    public DecANBean() {
        //decToUpdate = new Declaration();
        if (this.decToAdd == null) {
            this.decToAdd = new DeclarationNaissance();
        }
       
    }

    public DeclarationNaissance getDecToAdd() {
        return decToAdd;
    }

    public DeclarationNaissance getDecToConsult() {
        return decToConsult;
    }

    public void setDecToConsult(DeclarationNaissance decToConsult) {
        this.decToConsult = decToConsult;
    }

    
    public List<DeclarationNaissance> getAllDec() {
        allDec = acteService.getAllDeclaration();
        return allDec;
    }

    public List<DeclarationNaissance> getAllActe() {
        allActe = acteService.getAllActe();
        return allActe;
    }

    public List<DeclarationNaissance> getAllDecByUser() {
        allDecByUser = acteService.getAllDeclarationByUser(MyUtil.getUserLogged());
        return allDecByUser;
    }

    
    @SuppressWarnings("static-access")
	public String saveDeclaration() {
        String route = "";
        if(MyUtil.getProfil() != null){
        if("Jugement".equalsIgnoreCase(Tools.typeDeclarationNaissance(Tools.formatDay(this.decToAdd.getDate_naissanceE())))){
        	if("".equals(this.decToAdd.getNumero_jugement()) || "".equals(this.decToAdd.getDate_j()) || "".equals(this.decToAdd.getTribunal())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Cette déclaration est un jugement. Remplir les champs correspondants!");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if(acteService.verifyNumeroJugement(this.decToAdd.getNumero_jugement(), Tools.formatDay(this.decToAdd.getDate_j()).substring(6, 10))){
        		FacesMessage messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Ce numéro de jugement est déjà attribué pour cette année.");
                FacesContext.getCurrentInstance().addMessage(null, messages);
        	}
        	else if((this.decToAdd.getDeclarant().equalsIgnoreCase("Autre")) && ("".equals(this.decToAdd.getDomicile_declarant()) || "".equals(this.decToAdd.getPrenom_declarant()) || "".equals(this.decToAdd.getNom_declarant()) || "".equals(this.decToAdd.getProfession_declarant()) || "".equals(this.decToAdd.getAdresse_declarant()) || "".equals(this.decToAdd.getNum_identification_declarant()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations du déclarant.");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
        		this.decToAdd.setDate_naissance_enfant(Tools.formatDay(this.decToAdd.getDate_naissanceE()));
                this.decToAdd.setDate_naissance_mere(Tools.formatDay(this.decToAdd.getDate_naissanceM()));
                this.decToAdd.setDate_naissance_pere(Tools.formatDay(this.decToAdd.getDate_naissanceP()));
                this.decToAdd.setDate_jugement(Tools.formatDay(this.decToAdd.getDate_j()));
                this.decToAdd.setDate_creation(Tools.getCurrentDateTime());
                
                this.decToAdd.setHeure_naissance_enfant(this.decToAdd.getHeure_naissanceE().toString().substring(11, 16));
                this.decToAdd.setEtat("Instance");
                this.decToAdd.setType_declaration(Tools.typeDeclarationNaissance(Tools.formatDay(this.decToAdd.getDate_naissanceE())));
                this.decToAdd.setCreateurNaissance(MyUtil.getUserLogged());
                
                if (acteService.addDeclaration(decToAdd)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Déclaration sauvegardée avec succès!");
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                    route = "/views/" + MyUtil.getProfil() + "/liste-declaration/naissance?faces-redirect=true";
                    
                } else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Erreur dans le sauvegarde de la déclaration!");
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                }
        	}
        	
        }
        else if((this.decToAdd.getDeclarant().equalsIgnoreCase("Autre")) && ("".equals(this.decToAdd.getDomicile_declarant()) || "".equals(this.decToAdd.getPrenom_declarant()) || "".equals(this.decToAdd.getNom_declarant()) || "".equals(this.decToAdd.getProfession_declarant()) || "".equals(this.decToAdd.getAdresse_declarant()) || "".equals(this.decToAdd.getNum_identification_declarant()))){
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information", "Veuillez saisir les informations du déclarant.");
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
        else{
        	this.decToAdd.setDate_naissance_enfant(Tools.formatDay(this.decToAdd.getDate_naissanceE()));
            this.decToAdd.setDate_naissance_mere(Tools.formatDay(this.decToAdd.getDate_naissanceM()));
            this.decToAdd.setDate_naissance_pere(Tools.formatDay(this.decToAdd.getDate_naissanceP()));
            this.decToAdd.setDate_jugement(Tools.formatDay(this.decToAdd.getDate_j()));
            this.decToAdd.setDate_creation(Tools.getCurrentDateTime());
            this.decToAdd.setHeure_naissance_enfant(this.decToAdd.getHeure_naissanceE().toString().substring(11, 16));
            this.decToAdd.setEtat("Instance");
            this.decToAdd.setType_declaration(Tools.typeDeclarationNaissance(Tools.formatDay(this.decToAdd.getDate_naissanceE())));
            this.decToAdd.setCreateurNaissance(MyUtil.getUserLogged());
            
            if (acteService.addDeclaration(decToAdd)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Déclaration sauvegardée avec succès!");
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
                route = "/views/" + MyUtil.getProfil() + "/liste-declaration/naissance?faces-redirect=true";
                
            } else {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Erreur dans le sauvegarde de la déclaration!");
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            }

        }
        }
        else
        	route = MyUtil.pathLogin();
        
        return route;
    }


    public String cancel() {
    	if(MyUtil.getProfil() != null)
    		return  "/views/"+MyUtil.getProfil().toLowerCase()+"/liste-declaration/naissance?faces-redirect=true";
    	else
        	return MyUtil.pathLogin();
    }

    public String addDec() {
        if(MyUtil.getProfil() != null)
        	return  "/views/"+MyUtil.getProfil().toLowerCase()+"/declaration/naissance?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }

   

    public void updateDeclaration(ActionListener event) {
        
    }

    public void rejectDeclaration(ActionListener event) {
        RequestContext context = RequestContext.getCurrentInstance();
        String route = MyUtil.basePathLogin() + "views/declaration/naissance.xhtml";
        context.addCallbackParam("route", route);
    }

	public List<DeclarationNaissance> getFilterNaissance() {
		return filterNaissance;
	}

	public void setFilterNaissance(List<DeclarationNaissance> filterNaissance) {
		this.filterNaissance = filterNaissance;
	}

	
	
	   
}
