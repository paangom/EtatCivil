/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import models.DeclarationMariage;
import services.ActeMariageServices;
import util.MyUtil;
import util.Tools;


public class DecAMBean {
    
    private ActeMariageServices mariageServ = new ActeMariageServices();
    private DeclarationMariage decToAdd = null;
    private List<DeclarationMariage> filterMariage = null;
    private List<DeclarationMariage> allDec;
    private List<DeclarationMariage> allDecByUser;
    private List<DeclarationMariage> allActe;
    

    /**
     * Creates a new instance of decAMBean
     */
    public DecAMBean() {
        
        if(decToAdd == null){
            decToAdd = new DeclarationMariage();
        }
        
        
    }

    public DeclarationMariage getDecToAdd() {
        return decToAdd;
    }

    public List<DeclarationMariage> getAllDec() {
        allDec = mariageServ.getAllDecMariage();
        return allDec;
    }

    public List<DeclarationMariage> getAllDecByUser() {
        allDecByUser = mariageServ.getAllDecMaraigeByUser(MyUtil.getUserLogged());
        return allDecByUser;
    }

    public List<DeclarationMariage> getAllActe() {
        allActe = mariageServ.getRegistreMariage();
        return allActe;
    }

    
    
    
    
    @SuppressWarnings("static-access")
	public String saveDeclarationMariage(){
        String route = "";
        if(MyUtil.getProfil() != null){
        if("Jugement".equalsIgnoreCase(Tools.typeDeclarationMariage(Tools.formatDay(this.decToAdd.getDateMariage())))){
        	if("".equals(this.decToAdd.getNumero_Jugement()) || "".equals(this.decToAdd.getDateJugement()) || "".equals(this.decToAdd.getTribunal())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cette déclaration est un jugement. Remplir les champs correspondants!", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if(mariageServ.verifyNumeroJugement(this.decToAdd.getNumero_Jugement(), this.decToAdd.getDateJugement().toString().substring(6, 10))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce numéro de jugement est déjé attribué pour cette année.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
        		this.decToAdd.setDate_creation(Tools.getCurrentDateTime());
                this.decToAdd.setEtat("Instance");
                this.decToAdd.setDate_Mariage(Tools.formatDay(this.decToAdd.getDateMariage()));
                this.decToAdd.setDate_Jugement(Tools.formatDay(this.decToAdd.getDateJugement()));
                this.decToAdd.setDate_Naissance_Epouse(Tools.formatDay(this.decToAdd.getDateNaissanceEpouse()));
                this.decToAdd.setDate_Naissance_Epoux(Tools.formatDay(this.decToAdd.getDateNaissanceMereEpoux()));
                this.decToAdd.setDate_Naissance_Mere_Epouse(Tools.formatDay(this.decToAdd.getDateNaissanceMereEpouse()));
                this.decToAdd.setDate_Naissance_Mere_Epoux(Tools.formatDay(this.decToAdd.getDateNaissanceMereEpoux()));
                this.decToAdd.setDate_Naissance_Pere_Epoux(Tools.formatDay(this.decToAdd.getDateNaissancePereEpoux()));
                this.decToAdd.setDate_Naissance_Pere_Epouse(Tools.formatDay(this.decToAdd.getDateNaissancePereEpouse()));
                this.decToAdd.setHeure_Mariage(this.decToAdd.getHeureMariage().toString().substring(11, 16));
                this.decToAdd.setCreateurMariage(MyUtil.getUserLogged());
                this.decToAdd.setType_Declaration(Tools.typeDeclarationMariage(Tools.formatDay(this.decToAdd.getDateMariage())));
                
                if (mariageServ.saveDeclarationMariage(decToAdd)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Déclaration sauvegardée avec succès!", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                    
                    	route = "/views/" + MyUtil.getProfil() + "/liste-declaration/mariage?faces-redirect=true";
                    

                } 
                else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur dans le sauvegarde de la déclaration!", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                
                }
        	}
        }
        else{
        	this.decToAdd.setDate_creation(Tools.getCurrentDateTime());
            this.decToAdd.setEtat("Instance");
            this.decToAdd.setDate_Mariage(Tools.formatDay(this.decToAdd.getDateMariage()));
            this.decToAdd.setDate_Jugement(Tools.formatDay(this.decToAdd.getDateJugement()));
            this.decToAdd.setDate_Naissance_Epouse(Tools.formatDay(this.decToAdd.getDateNaissanceEpouse()));
            this.decToAdd.setDate_Naissance_Epoux(Tools.formatDay(this.decToAdd.getDateNaissanceMereEpoux()));
            this.decToAdd.setDate_Naissance_Mere_Epouse(Tools.formatDay(this.decToAdd.getDateNaissanceMereEpouse()));
            this.decToAdd.setDate_Naissance_Mere_Epoux(Tools.formatDay(this.decToAdd.getDateNaissanceMereEpoux()));
            this.decToAdd.setDate_Naissance_Pere_Epoux(Tools.formatDay(this.decToAdd.getDateNaissancePereEpoux()));
            this.decToAdd.setDate_Naissance_Pere_Epouse(Tools.formatDay(this.decToAdd.getDateNaissancePereEpouse()));
            this.decToAdd.setHeure_Mariage(this.decToAdd.getHeureMariage().toString().substring(11, 16));
            this.decToAdd.setCreateurMariage(MyUtil.getUserLogged());
            this.decToAdd.setType_Declaration(Tools.typeDeclarationMariage(Tools.formatDay(this.decToAdd.getDateMariage())));
            
            if (mariageServ.saveDeclarationMariage(decToAdd)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Déclaration sauvegardée avec succès!", null);
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
                
                	route="/views/" + MyUtil.getProfil() + "/liste-declaration/mariage?faces-redirect=true";
                

            } 
            else {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur dans le sauvegarde de la déclaration!", null);
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

	public List<DeclarationMariage> getFilterMariage() {
		return filterMariage;
	}

	public void setFilterMariage(List<DeclarationMariage> filterMariage) {
		this.filterMariage = filterMariage;
	}


    
}
