/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import util.MyUtil;

/**
 *
 * @author sambasow
 */


public class MenuBean implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String declarerN;
    private String listeDA;
    private String declarerM;
    private String listeDM;
    private String declarerD;
    private String listeDD;
    private String home;
    private String infoCentre;
    private String updateInfoCentre;
    private String infoProfil;
    private String updateInfoProfil;
    private String userList;
    @SuppressWarnings("unused")
	private String registreNaissance;
    @SuppressWarnings("unused")
	private String registreDeces;
    @SuppressWarnings("unused")
	private String registreMariage;
    private String listPieces;
    private String nonInscription;
    private String attestationdecoutume;
    private String nonExistance;
    private String residence;
    private String celibat;
    private String individualite;
    private String nonDivorceNonSeparation;
    private String nonMariageNondivorce;
    private String nonMariageNonRemariage;
    private String nonInscriptionMariage;
    private String collectif;
    private String collectitFamille;
    private String individuel;
    private String individuelFammile;
    private String inhumation;
    
    /**
     * Creates a new instance of menuBean
     */
    
    
    public MenuBean(){
        declarerN = MyUtil.pathDeclaration()+"naissance.xhtml";
        listeDA = MyUtil.basePath()+"liste-declaration/naissance.xhtml";
        declarerM = MyUtil.pathDeclaration()+"mariage.xhtml";
        listeDM = MyUtil.basePath()+"liste-declaration/mariage.xhtml";
        declarerD = MyUtil.pathDeclaration()+"deces.xhtml";
        listeDD = MyUtil.basePath()+"liste-declaration/deces.xhtml";
        home = MyUtil.basePath()+"home.xhtml";
        infoCentre = MyUtil.basePathAdmin()+"centre/informations.xhtml";
        updateInfoCentre = MyUtil.basePathAdmin()+"centre/modifier_information.xhtml";
        infoProfil = MyUtil.basePathAdmin()+"profil/information.xhtml";
        updateInfoProfil = MyUtil.basePathAdmin()+"profil/modifier_profil.xhtml";
        userList = MyUtil.basePath()+"gerer_utilisateur/liste_utilisateur.xhtml";
        listPieces = MyUtil.basePathAdmin()+"pieces/liste_pieces.xhtml";
        registreNaissance = MyUtil.basePath()+"registre/naissance.xhtml";
        registreMariage = MyUtil.basePath()+"registre/mariage.xhtml";
        registreDeces = MyUtil.basePath()+"registre/dece.xhtml";
        nonInscription = MyUtil.basePath()+"piecesAnnexes/nonInscription.xhtml";
        attestationdecoutume = MyUtil.basePath()+"piecesAnnexes/coutume.xhtml";
        nonExistance = MyUtil.basePath()+"piecesAnnexes/nonExistance.xhtml";
        residence = MyUtil.basePath()+"piecesAnnexes/residence.xhtml";
        celibat = MyUtil.basePath()+"piecesAnnexes/celibat.xhtml";
        individualite  = MyUtil.basePath()+"piecesAnnexes/individualite.xhtml";
        nonDivorceNonSeparation  = MyUtil.basePath()+"piecesAnnexes/nonDivorceNonSeparation.xhtml";
        nonMariageNondivorce  = MyUtil.basePath()+"piecesAnnexes/nonMariageNonDivorce.xhtml";
        nonMariageNonRemariage  = MyUtil.basePath()+"piecesAnnexes/nonMariageNonRemariage.xhtml";
        nonInscriptionMariage  = MyUtil.basePath()+"piecesAnnexes/nonInscriptionMariage.xhtml";
        collectif  = MyUtil.basePath()+"piecesAnnexes/collectif.xhtml";
        collectitFamille  = MyUtil.basePath()+"piecesAnnexes/collectifFamille.xhtml";
        individuel  = MyUtil.basePath()+"piecesAnnexes/individuel.xhtml";
        individuelFammile  = MyUtil.basePath()+"piecesAnnexes/individuelFamille.xhtml";
        inhumation  = MyUtil.basePath()+"piecesAnnexes/inhumation.xhtml";
    }

    public String getDeclarerN() {
        return declarerN;
    }

    public String getListeDA() {
        return listeDA;
    }

    public String getDeclarerM() {
        return declarerM;
    }

    public String getListeDM() {
        return listeDM;
    }

    public String getDeclarerD() {
        return declarerD;
    }

    public String getListeDD() {
        return listeDD;
    }

    public String getHome() {
        return home;
    }

    public String getInfoCentre() {
        return infoCentre;
    }

    public String getUpdateInfoCentre() {
        return updateInfoCentre;
    }

    public String getUserList() {
        return userList;
    }
    
    public String userList() {
        return MyUtil.basePath()+"gerer_utilisateur/liste_utilisateur?faces-redirect=true";
    }
    
    public String updateInfoCentre() {
        return MyUtil.basePathAdmin()+"centre/modifier_information?faces-redirect=true";
    }
    
    public String getRegistreNaissance(){
        return MyUtil.basePath()+"registre/naissance.xhtml";   
    }
    
    public String getRegistreMariage(){
        return MyUtil.basePath()+"registre/mariage.xhtml";   
    }
    
    public String getRegistreDeces(){
        return MyUtil.basePath()+"registre/dece.xhtml";   
    }

    public String getInfoProfil() {
        return infoProfil;
    }

    public String getUpdateInfoProfil() {
        return updateInfoProfil;
    }
    
    public String updateInfoProfil() {
        return MyUtil.basePathAdmin()+"profil/modifier_profil?faces-redirect=true";
    }
    
    public String infoProfil() {
        return MyUtil.basePathAdmin()+"profil/information?faces-redirect=true";
    }

    
    
    /**
     * 
     * @return  
     */
    public String declarerNaissance(){
        
       return MyUtil.pathDeclaration()+"naissance";
      
    }
    
    /**
     * 
     * @return 
     */
    public String listeDeclarationNaissance(){
        
        return MyUtil.pathDeclaration()+"liste-declaration/naissance";
       
    }
    
    /**
     * 
     * @return 
     */
    public String declarerMariage(){
        return MyUtil.pathDeclaration()+"mariage";
    }
    
    /**
     * 
     * @param event 
     */
    public void listeDeclarationMariage(ActionListener event){
        RequestContext context = RequestContext.getCurrentInstance();  
        String route = MyUtil.pathDeclaration()+"liste-declaration/mariage";
        context.addCallbackParam("route", route);
    }
    
    /**
     * 
     * @param event 
     */
    public void declarerDece(ActionListener event){
        RequestContext context = RequestContext.getCurrentInstance();  
        String route = MyUtil.pathDeclaration()+"dece";
        context.addCallbackParam("route", route);
    }
    
    

	/**
     * 
     * @param event 
     */
    public void listeDeclarationDece(ActionListener event){
        RequestContext context = RequestContext.getCurrentInstance();  
        String route = MyUtil.pathDeclaration()+"liste-declaration/dece";
        context.addCallbackParam("route", route);
    }
    
    /**
     * 
     * @param summary 
     */
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

	public String getListPieces() {
		return listPieces;
	}

	public void setListPieces(String listPieces) {
		this.listPieces = listPieces;
	}

	public void setRegistreMariage(String registreMariage) {
		this.registreMariage = registreMariage;
	}

	public String getNonInscription() {
		return nonInscription;
	}

	public void setNonInscription(String nonInscription) {
		this.nonInscription = nonInscription;
	}

	public String getAttestationdecoutume() {
		return attestationdecoutume;
	}

	public void setAttestationdecoutume(String attestationdecoutume) {
		this.attestationdecoutume = attestationdecoutume;
	}

	public String getNonExistance() {
		return nonExistance;
	}

	public void setNonExistance(String nonExistance) {
		this.nonExistance = nonExistance;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getCelibat() {
		return celibat;
	}

	public void setCelibat(String celibat) {
		this.celibat = celibat;
	}

	public String getIndividualite() {
		return individualite;
	}

	public void setIndividualite(String individualite) {
		this.individualite = individualite;
	}

	public String getNonDivorceNonSeparation() {
		return nonDivorceNonSeparation;
	}

	public void setNonDivorceNonSeparation(String nonDivorceNonSeparation) {
		this.nonDivorceNonSeparation = nonDivorceNonSeparation;
	}

	public String getNonMariageNondivorce() {
		return nonMariageNondivorce;
	}

	public void setNonMariageNondivorce(String nonMariageNondivorce) {
		this.nonMariageNondivorce = nonMariageNondivorce;
	}

	/**
	 * @return the nonMariageNonRemariage
	 */
	public String getNonMariageNonRemariage() {
		return nonMariageNonRemariage;
	}

	/**
	 * @param nonMariageNonRemariage the nonMariageNonRemariage to set
	 */
	public void setNonMariageNonRemariage(String nonMariageNonRemariage) {
		this.nonMariageNonRemariage = nonMariageNonRemariage;
	}

	/**
	 * @return the nonInscriptionMariage
	 */
	public String getNonInscriptionMariage() {
		return nonInscriptionMariage;
	}

	/**
	 * @param nonInscriptionMariage the nonInscriptionMariage to set
	 */
	public void setNonInscriptionMariage(String nonInscriptionMariage) {
		this.nonInscriptionMariage = nonInscriptionMariage;
	}

	/**
	 * @return the collectif
	 */
	public String getCollectif() {
		return collectif;
	}

	/**
	 * @param collectif the collectif to set
	 */
	public void setCollectif(String collectif) {
		this.collectif = collectif;
	}

	/**
	 * @return the collectitFamille
	 */
	public String getCollectitFamille() {
		return collectitFamille;
	}

	/**
	 * @param collectitFamille the collectitFamille to set
	 */
	public void setCollectitFamille(String collectitFamille) {
		this.collectitFamille = collectitFamille;
	}

	/**
	 * @return the individuelFammile
	 */
	public String getIndividuelFammile() {
		return individuelFammile;
	}

	/**
	 * @param individuelFammile the individuelFammile to set
	 */
	public void setIndividuelFammile(String individuelFammile) {
		this.individuelFammile = individuelFammile;
	}

	/**
	 * @return the individuel
	 */
	public String getIndividuel() {
		return individuel;
	}

	/**
	 * @param individuel the individuel to set
	 */
	public void setIndividuel(String individuel) {
		this.individuel = individuel;
	}

	/**
	 * @return the inhumation
	 */
	public String getInhumation() {
		return inhumation;
	}

	/**
	 * @param inhumation the inhumation to set
	 */
	public void setInhumation(String inhumation) {
		this.inhumation = inhumation;
	}  
}
