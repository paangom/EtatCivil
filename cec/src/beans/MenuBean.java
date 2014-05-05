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
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import services.ActeDecesServices;
import services.ActeMariageServices;
import services.ActeNaissanceServices;
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
    private String stat;
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
    
    private PieChartModel pieModel;
	private ActeDecesServices dServices = new ActeDecesServices();
	private ActeMariageServices mService = new ActeMariageServices();
	private ActeNaissanceServices nService = new ActeNaissanceServices();
    
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
        stat = MyUtil.basePath()+"statistiques/statistiques.xhtml";
        createPieModel();
    }

    public String getDeclarerN() {
    	if(MyUtil.getProfil() != null)
    		return declarerN;
    	else
    		return MyUtil.pathLogin();
    }

    public String getListeDA() {
    	if(MyUtil.getProfil() != null)
    		return listeDA;
    	else return MyUtil.pathLogin();
    }

    public String getDeclarerM() {
    	if(MyUtil.getProfil() != null)
    		return declarerM;
    	else
    		return MyUtil.pathLogin();
    }

    public String getListeDM() {
    	if(MyUtil.getProfil() != null)
    		return listeDM;
    	else return MyUtil.pathLogin();
    }

    public String getDeclarerD() {
    	if(MyUtil.getProfil() != null)
    		return declarerD;
    	else
    		return MyUtil.pathLogin();
    }

    public String getListeDD() {
    	if(MyUtil.getProfil() != null)
    		return listeDD;
    	else
    		return MyUtil.pathLogin();
    }

    public String getHome() {
    	if(MyUtil.getProfil() != null)
    		return home;
    	else
    		return MyUtil.pathLogin();
    }

    public String getInfoCentre() {
    	if(MyUtil.getProfil() != null)
    		return infoCentre;
    	else
    		return MyUtil.pathLogin();
    }

    public String getUpdateInfoCentre() {
    	if(MyUtil.getProfil() != null)
    		return updateInfoCentre;
    	else
    		return MyUtil.pathLogin();
    }

    public String getUserList() {
    	if(MyUtil.getProfil() != null)
    		return userList;
    	else
    		return MyUtil.pathLogin();
    }
    
    public String userList() {
    	if(MyUtil.getProfil() != null)
    		return MyUtil.basePath()+"gerer_utilisateur/liste_utilisateur?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String updateInfoCentre() {
    	if(MyUtil.getProfil() != null)
    		return MyUtil.basePathAdmin()+"centre/modifier_information?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String getRegistreNaissance(){
    	if(MyUtil.getProfil() != null)
    		return MyUtil.basePath()+"registre/naissance.xhtml";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String getRegistreMariage(){
    	if(MyUtil.getProfil() != null)
    		return MyUtil.basePath()+"registre/mariage.xhtml";   
    	else
    		return MyUtil.pathLogin();
    }
    
    public String getRegistreDeces(){
    	if(MyUtil.getProfil() != null)
    		return MyUtil.basePath()+"registre/dece.xhtml";
    	else
    		return MyUtil.pathLogin();
    }

    public String getInfoProfil() {
    	if(MyUtil.getProfil() != null)
    		return infoProfil;
    	else
    		return MyUtil.pathLogin();
    }

    public String getUpdateInfoProfil() {
    	if(MyUtil.getProfil() != null)
    		return updateInfoProfil;
    	else
    		return MyUtil.pathLogin();
    }
    
    public String updateInfoProfil() {
    	if(MyUtil.getProfil() != null)
    		return MyUtil.basePathAdmin()+"profil/modifier_profil?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String infoProfil() {
    	if(MyUtil.getProfil() != null)
    		return MyUtil.basePathAdmin()+"profil/information?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }

    
    
    /**
     * 
     * @return  
     */
    public String declarerNaissance(){
       if(MyUtil.getProfil() != null)
    	   return MyUtil.pathDeclaration()+"naissance";
       else
    	   return MyUtil.pathLogin();
    }
    
    /**
     * 
     * @return 
     */
    public String listeDeclarationNaissance(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathDeclaration()+"liste-declaration/naissance";
        else
        	return MyUtil.pathLogin();
    }
    
    /**
     * 
     * @return 
     */
    public String declarerMariage(){
    	if(MyUtil.getProfil() != null)
    		return MyUtil.pathDeclaration()+"mariage";
    	else
    		return MyUtil.pathLogin();
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
		if(MyUtil.getProfil() != null)
			return listPieces;
		else
			return MyUtil.pathLogin();
	}

	public void setListPieces(String listPieces) {
		this.listPieces = listPieces;
	}

	public void setRegistreMariage(String registreMariage) {
		this.registreMariage = registreMariage;
	}

	public String getNonInscription() {
		if(MyUtil.getProfil() != null)
			return nonInscription;
		else
			return MyUtil.pathLogin();
	}

	public void setNonInscription(String nonInscription) {
		this.nonInscription = nonInscription;
	}

	public String getAttestationdecoutume() {
		if(MyUtil.getProfil() != null)
			return attestationdecoutume;
		else
			return MyUtil.pathLogin();
	}

	public void setAttestationdecoutume(String attestationdecoutume) {
		this.attestationdecoutume = attestationdecoutume;
	}

	public String getNonExistance() {
		if(MyUtil.getProfil() != null)
			return nonExistance;
		else
			return MyUtil.pathLogin();
	}

	public void setNonExistance(String nonExistance) {
		this.nonExistance = nonExistance;
	}

	public String getResidence() {
		if(MyUtil.getProfil() != null)
			return residence;
		else
			return MyUtil.pathLogin();
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getCelibat() {
		if(MyUtil.getProfil() != null)
			return celibat;
		else
			return MyUtil.pathLogin();
	}

	public void setCelibat(String celibat) {
		this.celibat = celibat;
	}

	public String getIndividualite() {
		if(MyUtil.getProfil() != null)
			return individualite;
		else
			return MyUtil.pathLogin();
	}

	public void setIndividualite(String individualite) {
		this.individualite = individualite;
	}

	public String getNonDivorceNonSeparation() {
		if(MyUtil.getProfil() != null)
			return nonDivorceNonSeparation;
		else
			return MyUtil.pathLogin();
	}

	public void setNonDivorceNonSeparation(String nonDivorceNonSeparation) {
		this.nonDivorceNonSeparation = nonDivorceNonSeparation;
	}

	public String getNonMariageNondivorce() {
		if(MyUtil.getProfil() != null)
			return nonMariageNondivorce;
		else
			return MyUtil.pathLogin();
	}

	public void setNonMariageNondivorce(String nonMariageNondivorce) {
		this.nonMariageNondivorce = nonMariageNondivorce;
	}

	/**
	 * @return the nonMariageNonRemariage
	 */
	public String getNonMariageNonRemariage() {
		if(MyUtil.getProfil() != null)
			return nonMariageNonRemariage;
		else
			return MyUtil.pathLogin();
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
		if(MyUtil.getProfil() != null)
			return nonInscriptionMariage;
		else
			return MyUtil.pathLogin();
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
		if(MyUtil.getProfil() != null)
			return collectif;
		else
			return MyUtil.pathLogin();
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
		if(MyUtil.getProfil() != null)
			return collectitFamille;
		else
			return MyUtil.pathLogin();
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
		if(MyUtil.getProfil() != null)
			return individuelFammile;
		else
			return MyUtil.pathLogin();
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
		if(MyUtil.getProfil() != null)
			return individuel;
		else
			return MyUtil.pathLogin();
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
		if(MyUtil.getProfil() != null)
			return inhumation;
		else
			return MyUtil.pathLogin();
	}

	/**
	 * @param inhumation the inhumation to set
	 */
	public void setInhumation(String inhumation) {
		this.inhumation = inhumation;
	}  
	

	/**
	 * @return the pieModel
	 */
	public PieChartModel getPieModel() {

		Number n = nService.getAllActe().size();
		Number m = mService.getRegistreMariage().size();
		Number d = dServices.getRegistreDece().size();

		pieModel.getData().put("Naissance", n);
		pieModel.getData().put("Mariage", m);
		pieModel.getData().put("Décès", d);

		return pieModel;
	}

	/**
	 * @param pieModel the pieModel to set
	 */
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	private void createPieModel() {  
        pieModel = new PieChartModel();  
  
        Number t=0;
        t = nService.getAllActe().size();
        pieModel.set("Naissance", t);
        t = mService.getRegistreMariage().size();
        pieModel.set("Mariage", t); 
        t =  dServices.getRegistreDece().size();
        pieModel.set("Décès", t);
    }

	 @SuppressWarnings("static-access")
	public void itemSelect(ItemSelectEvent event) {  
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",  
	                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());  

	        FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, msg);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);  
	}

	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * @param stat the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}  
	

}
