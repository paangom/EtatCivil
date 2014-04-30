/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import models.DeclarationDeces;
import models.DeclarationMariage;
import models.DeclarationNaissance;
import models.DelivredPieces;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.ActeDecesServices;
import services.ActeMariageServices;
import services.ActeNaissanceServices;
import services.CentreServices;
import services.DelivredPieceService;
import util.MyUtil;
import util.NombreEnLettre;
import util.Tools;

/**
 *
 * @author sambasow
 */


public class ConsultBean implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DeclarationNaissance decToConsult;
    private DeclarationDeces decDCToConsult;
    private DeclarationMariage decMarToConsult;
    private ActeNaissanceServices acteService = new ActeNaissanceServices();
    private ActeDecesServices deceService = new ActeDecesServices();
    private ActeMariageServices marService = new ActeMariageServices();
    private DelivredPieceService delService = new DelivredPieceService(); 
    private CentreServices cenServ = new CentreServices();
    @SuppressWarnings("rawtypes")
	private Map parameter = new HashMap();
    
    private DeclarationNaissance filterNaissance;
    private DeclarationDeces filterDeces;
    private DeclarationMariage filterMariage;
    
    
    private List<DeclarationNaissance> registreCurrentYear;
    private List<DeclarationNaissance> registreYear = null;
    
    private List<DeclarationMariage> registreMariageCurrentYear;
    private List<DeclarationMariage> registreMariageYear = null;
    
    private List<DeclarationDeces> registreDecesCurrentYear;
    private List<DeclarationDeces> registreDecesYear = null;
    private List<DelivredPieces> pdByUser = null;
    private DelivredPieces pieceDel;
    
    private String mention;
    private String mentionN;
    private String mentionD;
 
    private String selectedAnnees;
    public String type;
    private List<String> annees;
    
    private int decN;
    private int decM;
    private int decD;
    
    private int decNBU;
    private int decMBU;
    private int decDBU;
    private int piecDel;
    private int pieceDelBU;
  
    /**
     * Creates a new instance of consultBean
     */
    public ConsultBean() {
    	 annees= new ArrayList<String>(); 
         int a = Integer.parseInt(cenServ.getCentre().getAnneeRegistre());
 		
 		for(int i = anneeCourant ; i >= a; i--){
 			annees.add(i+""); 
 		} 
 		
 				
    }
    
   

    public DeclarationNaissance getDecToConsult() {
        return decToConsult;
    }

    public DeclarationDeces getDecDCToConsult() {
        return decDCToConsult;
    }

    public DeclarationMariage getDecMarToConsult() {
        return decMarToConsult;
    }

    public void setDecMarToConsult(DeclarationMariage decMarToConsult) {
        this.decMarToConsult = decMarToConsult;
    }
    
    

    public void setDecDCToConsult(DeclarationDeces decDCToConsult) {
        this.decDCToConsult = decDCToConsult;
    }
    
    

    public void setDecToConsult(DeclarationNaissance decToConsult) {
        this.decToConsult = decToConsult;
    }
    
    public String viewDecclaration(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathConsultationDeclaration()+"naissance?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    public String updateRegistreN(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathModificationActe()+"naissance?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    public String updateRegistreM(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathModificationActe()+"mariage?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    public String updateRegistreD(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathModificationActe()+"deces?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    
    public String viewDecclarationDeces(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathConsultationDeclaration()+"deces?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    public String viewDeclarationMariage(){
    	if(MyUtil.getProfil() != null)
    		return MyUtil.pathConsultationDeclaration()+"mariage?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String viewActeNaissance(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathConsultationActe()+"naissance?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    public String viewActeDeces(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathConsultationActe()+"deces?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    public String viewActeMariage(){
        if(MyUtil.getProfil() != null)
        	return MyUtil.pathConsultationActe()+"mariage?faces-redirect=true";
        else
        	return MyUtil.pathLogin();
    }
    
    @SuppressWarnings("static-access")
	public String validateDeclarationNaissance() {
        String route = "";
        if(MyUtil.getProfil() != null){
        this.decToConsult.setEtat("Valider");
        this.decToConsult.setValidateurNaissance(MyUtil.getUserLogged());
        this.decToConsult.setNumero_acte(acteService.numeroActe(this.decToConsult.getDate_creation().substring(6, 10)));
        this.decToConsult.setDate_modification(Tools.getCurrentDateTime());
        if (acteService.updateDeclarationNaissance(this.decToConsult)) {
            route =   MyUtil.basePath() + "registre/naissance?faces-redirect=true";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La déclaration a été bien validée avec succès!", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
        }
        else{
            route =  MyUtil.basePathLogin() + "views/" + MyUtil.getProfil() + "/consultation/declaration/naissance?faces-redirect=true";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La déclaration n'a pas été validée!", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
        }
        }
        else
        	route  = MyUtil.pathLogin();
        
        return  route;
    }
    
    @SuppressWarnings("static-access")
	public String validateDeclarationDeces() {
        String route = "";
        if(MyUtil.getProfil() != null){
        this.decDCToConsult.setEtat("Valider");
        this.decDCToConsult.setNumero_acte(deceService.numeroActe(this.decDCToConsult.getDate_creation().substring(6, 10)));
        this.decDCToConsult.setDate_modification(Tools.getCurrentDateTime());
        this.decDCToConsult.setValidateurDeces(MyUtil.getUserLogged());
        if (deceService.updateDeclarationDeces(decDCToConsult)) {
        	
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La déclaration de décès a été bien validé!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =   MyUtil.basePath() + "registre/dece?faces-redirect=true";
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La déclaration n'a pas pu être valider!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
        	route =  MyUtil.basePathLogin() + "views/" + MyUtil.getProfil() + "/consultation/declaration/deces?faces-redirect=true";
        
        }
        }
        else
        	route = MyUtil.pathLogin();
        return route;
    }
    
    @SuppressWarnings("static-access")
	public String validateDeclarationMariage() {
        String route = "";
        if(MyUtil.getProfil() != null){
        this.decMarToConsult.setEtat("Valider");
        this.decMarToConsult.setNumero_Acte(marService.numeroActe(this.decMarToConsult.getDate_creation().substring(6, 10)));
        this.decMarToConsult.setDate_modification(Tools.getCurrentDateTime());
        this.decMarToConsult.setValidateurMariage(MyUtil.getUserLogged());
        if (marService.updateDeclarationMariage(this.decMarToConsult)) {
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La déclaration de mariage est validée avec succès!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =   MyUtil.basePath() + "registre/mariage?faces-redirect=true";
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La déclaration n'a pas pu être valider!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
        	route =  MyUtil.basePathLogin() + "views/" + MyUtil.getProfil() + "/consultation/declaration/mariage?faces-redirect=true";
        }
        }
        else
        	route = MyUtil.pathLogin();
        return route;
    }

    @SuppressWarnings("static-access")
	public String rejectDeclarationNaissance() {
    	String route = "";
    	if(MyUtil.getProfil() != null){
        this.decToConsult.setEtat("Rejeter");
        this.decToConsult.setValidateurNaissance(MyUtil.getUserLogged());
        this.decToConsult.setDate_modification(Tools.getCurrentDateTime());
        if (acteService.updateDeclarationNaissance(this.decToConsult)) {
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "La déclaration de naissance est bien rejetée!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =  MyUtil.basePath() + "views/" + MyUtil.getProfil() + "/liste-declaration/naissance?faces-redirect=true";
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La déclaration n'a pas pu être rejeter!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =  MyUtil.basePath() + "views/" + MyUtil.getProfil() + "/consultation/declaration/naissance?faces-redirect=true";
        }
    	}
    	else
    		route =  MyUtil.pathLogin();
        //context.addCallbackParam("route", route);
        return route;
    }
    
    @SuppressWarnings("static-access")
	public String rejectDeclarationDece() {
        String route = "";
        if(MyUtil.getProfil() != null){
        this.decDCToConsult.setEtat("Rejeter");
        this.decDCToConsult.setDate_modification(Tools.getCurrentDateTime());
        this.decDCToConsult.setModificateurDeces(MyUtil.getUserLogged());
        if (deceService.updateDeclarationDeces(decDCToConsult)) {
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "La déclaration de décès est bien rejetée!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =  MyUtil.basePathLogin() + "views/" + MyUtil.getProfil() + "/liste-declaration/deces?faces-redirect=true";
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La déclaration n'a pas pu être rejeter!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =  MyUtil.basePathLogin() + "views/" + MyUtil.getProfil() + "/consultation/declaration/deces?faces-redirect=true";
        }
        }
        else
        	route = MyUtil.pathLogin();
        //context.addCallbackParam("route", route);
        return route;
    }
    
    @SuppressWarnings("static-access")
	public String rejectDeclarationMariage() {
    	String route = "";
    	if(MyUtil.getProfil() != null){
        this.decMarToConsult.setEtat("Rejeter");
        this.decMarToConsult.setDate_modification(Tools.getCurrentDateTime());
        this.decMarToConsult.setModificateurMariage(MyUtil.getUserLogged());
        if (marService.updateDeclarationMariage(this.decMarToConsult)) {
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "La déclaration mariage est bien rejetée!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =  MyUtil.basePathLogin() + "views/" + MyUtil.getProfil() + "/liste-declaration/mariage?faces-redirect=true";
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La déclaration n'a pas pu être rejeter!", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
            route =  MyUtil.basePathLogin() + "views/" + MyUtil.getProfil() + "/consultation/declaration/mariage?faces-redirect=true";
        }
    	}
    	else
    		route = MyUtil.pathLogin();
            
        return route;
    }
    
    //Liste des méthodes pour effectuer les modifications des déclarations
    
    @SuppressWarnings("static-access")
	public String updateDeclarationNaissance() {
        String route = "";
        if(MyUtil.getProfil() != null){
        if("Jugement".equalsIgnoreCase(Tools.typeDeclarationNaissance(Tools.formatDay(this.decToConsult.getDate_naissanceE())))){
        	if("".equals(this.decToConsult.getNumero_jugement()) || "".equals(this.decToConsult.getDate_j()) || "".equals(this.decToConsult.getTribunal())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cette déclaration est un jugement. Remplir les champs correspondants!", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if(acteService.verifyNumeroJugement(this.decToConsult.getNumero_jugement(), Tools.formatDay(this.decToConsult.getDate_j()).substring(6, 10), this.decToConsult.getId())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce numéro de jugement est déjà  attribué pour cette année.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decToConsult.getDeclarant().equalsIgnoreCase("Autre")) && ("".equals(this.decToConsult.getDomicile_declarant()) || "".equals(this.decToConsult.getPrenom_declarant()) || "".equals(this.decToConsult.getNom_declarant()) || "".equals(this.decToConsult.getProfession_declarant()) || "".equals(this.decToConsult.getAdresse_declarant()) || "".equals(this.decToConsult.getNum_identification_declarant()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du déclarant.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
        		this.decToConsult.setDate_naissance_enfant(Tools.formatDay(this.decToConsult.getDate_naissanceE()));
                this.decToConsult.setDate_naissance_mere(Tools.formatDay(this.decToConsult.getDate_naissanceM()));
                this.decToConsult.setDate_naissance_pere(Tools.formatDay(this.decToConsult.getDate_naissanceP()));
                this.decToConsult.setDate_jugement(Tools.formatDay(this.decToConsult.getDate_j()));
                
                this.decToConsult.setHeure_naissance_enfant(this.decToConsult.getHeure_naissanceE().toString().substring(11, 16));
                this.decToConsult.setType_declaration(Tools.typeDeclarationNaissance(Tools.formatDay(this.decToConsult.getDate_naissanceE())));
                
                
                if (acteService.updateActe(decToConsult)) {
                	route = "/views/" + MyUtil.getProfil() + "/consultation/declaration/naissance?faces-redirect=true";
                	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La modification a été effectuée avec succès.", null);
                	FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                }
                
                else{
                	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible de modifier la déclaration. Vérifiez toutes les informations saisies.", null);
                	FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                }
                        	}
        	
        }
        else if((this.decToConsult.getDeclarant().equalsIgnoreCase("Autre")) && ("".equals(this.decToConsult.getDomicile_declarant()) || "".equals(this.decToConsult.getPrenom_declarant()) || "".equals(this.decToConsult.getNom_declarant()) || "".equals(this.decToConsult.getProfession_declarant()) || "".equals(this.decToConsult.getAdresse_declarant()) || "".equals(this.decToConsult.getNum_identification_declarant()))){
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du déclarant.", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
        else{
        	this.decToConsult.setDate_naissance_enfant(Tools.formatDay(this.decToConsult.getDate_naissanceE()));
            this.decToConsult.setDate_naissance_mere(Tools.formatDay(this.decToConsult.getDate_naissanceM()));
            this.decToConsult.setDate_naissance_pere(Tools.formatDay(this.decToConsult.getDate_naissanceP()));
            this.decToConsult.setDate_jugement(Tools.formatDay(this.decToConsult.getDate_j()));
            this.decToConsult.setHeure_naissance_enfant(this.decToConsult.getHeure_naissanceE().toString().substring(11, 16));
            this.decToConsult.setType_declaration(Tools.typeDeclarationNaissance(Tools.formatDay(this.decToConsult.getDate_naissanceE())));
            
            if (acteService.updateActe(decToConsult)) {
            	route = "/views/" + MyUtil.getProfil() + "/consultation/declaration/naissance?faces-redirect=true";
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La modification a été effectuée avec succès.", null);
            	FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            }
            else{
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible de modifier la déclaration. Vérifiez toutes les informations saisies.", null);
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
    
    @SuppressWarnings("static-access")
	public String updateDeclarationMariage() {
        String route = "";
        if(MyUtil.getProfil() != null){
        if("Jugement".equalsIgnoreCase(Tools.typeDeclarationMariage(Tools.formatDay(this.decMarToConsult.getDateMariage())))){
        	if("".equals(this.decMarToConsult.getNumero_Jugement()) || "".equals(this.decMarToConsult.getDateJugement()) || "".equals(this.decMarToConsult.getTribunal())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cette déclaration est un jugement. Remplir les champs correspondants!", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if(marService.verifyNumeroJugement(this.decMarToConsult.getNumero_Jugement(), this.decMarToConsult.getDateJugement().toString().substring(6, 10), this.decMarToConsult.getId())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce numéro de jugement est déjà  attribué pour cette année.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
                this.decMarToConsult.setDate_Mariage(Tools.formatDay(this.decMarToConsult.getDateMariage()));
                this.decMarToConsult.setDate_Jugement(Tools.formatDay(this.decMarToConsult.getDateJugement()));
                this.decMarToConsult.setDate_Naissance_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissanceEpouse()));
                this.decMarToConsult.setDate_Naissance_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpoux()));
                this.decMarToConsult.setDate_Naissance_Mere_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpouse()));
                this.decMarToConsult.setDate_Naissance_Mere_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpoux()));
                this.decMarToConsult.setDate_Naissance_Pere_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissancePereEpoux()));
                this.decMarToConsult.setDate_Naissance_Pere_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissancePereEpouse()));
                this.decMarToConsult.setHeure_Mariage(this.decMarToConsult.getHeureMariage().toString().substring(11, 16));
                
                if (marService.updateDeclarationMariage(decMarToConsult)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Les modifications ont été effectué avec succès.", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                    route="/views/" + MyUtil.getProfil() + "/liste-declaration/mariage?faces-redirect=true";

                } 
                else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Les modifications n'ont pas été effecuté normalement.", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                
                }
        	}
        }
        else{
            this.decMarToConsult.setDate_Mariage(Tools.formatDay(this.decMarToConsult.getDateMariage()));
            this.decMarToConsult.setDate_Jugement(Tools.formatDay(this.decMarToConsult.getDateJugement()));
            this.decMarToConsult.setDate_Naissance_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissanceEpouse()));
            this.decMarToConsult.setDate_Naissance_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpoux()));
            this.decMarToConsult.setDate_Naissance_Mere_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpouse()));
            this.decMarToConsult.setDate_Naissance_Mere_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpoux()));
            this.decMarToConsult.setDate_Naissance_Pere_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissancePereEpoux()));
            this.decMarToConsult.setDate_Naissance_Pere_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissancePereEpouse()));
            this.decMarToConsult.setHeure_Mariage(this.decMarToConsult.getHeureMariage().toString().substring(11, 16));
            
            if (marService.updateDeclarationMariage(decMarToConsult)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Les modifications ont été effectué avec succès.", null);
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
                route="/views/" + MyUtil.getProfil() + "/liste-declaration/mariage?faces-redirect=true";

            } 
            else {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Les modifications n'ont pas été effecuté normalement.", null);
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
    
    @SuppressWarnings("static-access")
	public String updateDeclarationDeces() {
        String route = "";
        if(MyUtil.getProfil() != null){
        if("Jugement".equalsIgnoreCase(Tools.typeDeclarationDeces(Tools.formatDay(this.decDCToConsult.getDate_d())))){
        	if("".equals(this.decDCToConsult.getNum_jugement()) || "".equals(this.decDCToConsult.getDate_j()) || "".equals(this.decDCToConsult.getTribunal())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cette déclaration est un jugement. Remplir les champs correspondants!", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if(deceService.verifyNumeroJugement(this.decDCToConsult.getNum_jugement(), Tools.formatDay(this.decDCToConsult.getDate_j()).substring(6, 10), this.decDCToConsult.getId())){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce numéro de jugement est déjé attribué pour cette année.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Autre")) && ("".equals(this.decDCToConsult.getAdresse_declarant()) || "".equals(this.decDCToConsult.getPrenom_declarant()) || "".equals(this.decDCToConsult.getNom_declarant()) || "".equals(this.decDCToConsult.getProfession_declarant()) || "".equals(this.decDCToConsult.getNum_identification_declarant()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du déclarant.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Pére")) && ("".equals(this.decDCToConsult.getPrenom_pere()) || "".equals(this.decDCToConsult.getNom_pere()) || "".equals(this.decDCToConsult.getDomicile_pere()) || "".equals(this.decDCToConsult.getProfession_pere()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du pére.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Mére")) && ("".equals(this.decDCToConsult.getPrenom_mere()) || "".equals(this.decDCToConsult.getNom_mere()) || "".equals(this.decDCToConsult.getDomicile_mere()) || "".equals(this.decDCToConsult.getProfession_mere()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations de la mére.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
        		this.decDCToConsult.setDate_deces(Tools.formatDay(this.decDCToConsult.getDate_d()));
                this.decDCToConsult.setDate_naissance_defunt(Tools.formatDay(this.decDCToConsult.getDate_naissanceDefunt()));
                this.decDCToConsult.setDate_jugement(Tools.formatDay(this.decDCToConsult.getDate_j()));
                this.decDCToConsult.setHeure_deces(this.decDCToConsult.getHeure_d().toString().substring(11, 16));
                
                
                if (deceService.updateDeclarationDeces(decDCToConsult)) {
                	
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La modification a été effectué avec succés!", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                    route="/views/" + MyUtil.getProfil() + "/liste-declaration/deces?faces-redirect=true";

                } else {
                	
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La modification n'a pas été effectué!", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                }
        	}
        	
        }

    	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Autre")) && ("".equals(this.decDCToConsult.getAdresse_declarant()) || "".equals(this.decDCToConsult.getPrenom_declarant()) || "".equals(this.decDCToConsult.getNom_declarant()) || "".equals(this.decDCToConsult.getProfession_declarant()) || "".equals(this.decDCToConsult.getNum_identification_declarant()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du déclarant.", null);
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Pére")) && ("".equals(this.decDCToConsult.getPrenom_pere()) || "".equals(this.decDCToConsult.getNom_pere()) || "".equals(this.decDCToConsult.getDomicile_pere()) || "".equals(this.decDCToConsult.getProfession_pere()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du pére.", null);
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Mére")) && ("".equals(this.decDCToConsult.getPrenom_mere()) || "".equals(this.decDCToConsult.getNom_mere()) || "".equals(this.decDCToConsult.getDomicile_mere()) || "".equals(this.decDCToConsult.getProfession_mere()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations de la mére.", null);
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
        else{
        	this.decDCToConsult.setDate_deces(Tools.formatDay(this.decDCToConsult.getDate_d()));
            this.decDCToConsult.setDate_naissance_defunt(Tools.formatDay(this.decDCToConsult.getDate_naissanceDefunt()));
            this.decDCToConsult.setDate_jugement(Tools.formatDay(this.decDCToConsult.getDate_j()));
            this.decDCToConsult.setHeure_deces(this.decDCToConsult.getHeure_d().toString().substring(11, 16));
            
            
            if (deceService.updateDeclarationDeces(decDCToConsult)) {
            	
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La modification a été effectué avec succés!", null);
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
                route="/views/" + MyUtil.getProfil() + "/liste-declaration/deces?faces-redirect=true";

            } else {
            	
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La modification n'a pas été effectué!", null);
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
    
    public String updateDecNaissance(){
    	if(MyUtil.getProfil() != null)
    		return  MyUtil.pathModificationDeclaration()+"naissance?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String updateDecMariage(){
    	if(MyUtil.getProfil() != null)
    		return  MyUtil.pathModificationDeclaration()+"mariage?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String updateDecDeces(){
    	if(MyUtil.getProfil() != null)
    		return  MyUtil.pathModificationDeclaration()+"deces?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    //Fin de la liste des méthodes
    
    
    //Liste des méthodes pour les retours
    public String retourRegistreNaissance(){
    	if(MyUtil.getProfil() != null)
    		return "/views/" + MyUtil.getProfil() + "/registre/naissance?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String retourRegistreMariage(){
    	if(MyUtil.getProfil() != null)
    		return "/views/" + MyUtil.getProfil() + "/registre/mariage?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String retourRegistreDeces(){
    	if(MyUtil.getProfil() != null)
    		return "/views/" + MyUtil.getProfil() + "/registre/dece?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    
    public String retourDeclarationNaissance(){
    	if(MyUtil.getProfil() != null)
    		return "/views/" + MyUtil.getProfil() + "/liste-declaration/naissance?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String retourDeclarationMariage(){
    	if(MyUtil.getProfil() != null)
    		return "/views/" + MyUtil.getProfil() + "/liste-declaration/mariage?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    public String retourDeclarationDeces(){
    	if(MyUtil.getProfil() != null)
    		return "/views/" + MyUtil.getProfil() + "/liste-declaration/deces?faces-redirect=true";
    	else
    		return MyUtil.pathLogin();
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
  	public void printActeNaissance() throws JRException{
          String num=decToConsult.getNumero_acte()+"";
  		char[] t=num.toCharArray();
  		if(t.length==1){

  			parameter.put("num1", Character.toString(t[t.length-1]));
  			parameter.put("num2", "");
  			parameter.put("num3", "");
  			parameter.put("num4", "");
  			parameter.put("num5", "");
  			parameter.put("num6", "");
  		}else if(t.length==2){
  			parameter.put("num1", Character.toString(t[t.length-1]));
  			parameter.put("num2", Character.toString(t[t.length-2]));
  			parameter.put("num3", "");
  			parameter.put("num4", "");
  			parameter.put("num5", "");
  			parameter.put("num6", "");
  		}else if(t.length==3){
  			parameter.put("num1", Character.toString(t[t.length-1]));
  			parameter.put("num2", Character.toString(t[t.length-2]));
  			parameter.put("num3", Character.toString(t[t.length-3]));
  			parameter.put("num4", "");
  			parameter.put("num5", "");
  			parameter.put("num6", "");
  		}else if(t.length==4){
  			parameter.put("num1", Character.toString(t[t.length-1]));
  			parameter.put("num2", Character.toString(t[t.length-2]));
  			parameter.put("num3", Character.toString(t[t.length-3]));
  			parameter.put("num4", Character.toString(t[t.length-4]));
  			parameter.put("num5", "");
  			parameter.put("num6", "");
  		}else if(t.length==5){
  			parameter.put("num1", Character.toString(t[t.length-1]));
  			parameter.put("num2", Character.toString(t[t.length-2]));
  			parameter.put("num3", Character.toString(t[t.length-3]));
  			parameter.put("num4", Character.toString(t[t.length-4]));
  			parameter.put("num5", Character.toString(t[t.length-5]));
  			parameter.put("num6", "");
  		}else if(t.length==6){
  			parameter.put("num1", Character.toString(t[t.length-1]));
  			parameter.put("num2", Character.toString(t[t.length-2]));
  			parameter.put("num3", Character.toString(t[t.length-3]));
  			parameter.put("num4", Character.toString(t[t.length-4]));
  			parameter.put("num5", Character.toString(t[t.length-5]));
  			parameter.put("num6", Character.toString(t[t.length-6]));
  		}
  		
  		parameter.put("anneeChiffre", Tools.getYearForDateTime(decToConsult.getDate_creation()));
		parameter.put("numVolet", "");
		parameter.put("region", decToConsult.getCreateurNaissance().getCentre().getCenterRegion().toUpperCase());
		parameter.put("departement", decToConsult.getCreateurNaissance().getCentre().getCenterDepartement().toUpperCase());
		parameter.put("arrondissement", decToConsult.getCreateurNaissance().getCentre().getCenterArrondissement().toUpperCase());
		parameter.put("commune", decToConsult.getCreateurNaissance().getCentre().getCenterCollectivite().toUpperCase());
		parameter.put("type", decToConsult.getCreateurNaissance().getCentre().getCenterType().toUpperCase());
		parameter.put("collectivite", decToConsult.getCreateurNaissance().getCentre().getCenterName().toUpperCase());
		parameter.put("prenomEnfant", decToConsult.getPrenom_enfant().toUpperCase());
		parameter.put("nomEnfant", decToConsult.getNom_enfant().toUpperCase());
		parameter.put("sexeEnfant", decToConsult.getSexe());
		parameter.put("dateNaissEnfant",  Tools.getformatDate(decToConsult.getDate_naissance_enfant()));
		parameter.put("heureNaissEnfant", decToConsult.getHeure_naissance_enfant());
		parameter.put("lieuNaissEnfant", decToConsult.getLieu_naissance_enfant());
		parameter.put("formationSanitaire", decToConsult.getFormation_sanitaire());
		parameter.put("jourEnfant", Tools.getDayForDate(decToConsult.getDate_naissance_enfant()));
		parameter.put("moisEnfant", Tools.getMonthForDate(decToConsult.getDate_naissance_enfant()));
		parameter.put("anneeEnfant", Tools.getYearForDate(decToConsult.getDate_naissance_enfant()));
		parameter.put("prenomPere", decToConsult.getPrenom_pere());
		parameter.put("nomPere", decToConsult.getNom_pere());
		parameter.put("jourPere", Tools.getDayForDate(decToConsult.getDate_naissance_pere()));
		parameter.put("moisPere", Tools.getMonthForDate(decToConsult.getDate_naissance_pere()));
		parameter.put("anneePere", Tools.getYearForDate(decToConsult.getDate_naissance_pere()));
		parameter.put("dateNaissPere",  Tools.getformatDate(decToConsult.getDate_naissance_pere()));
		parameter.put("lieuNaissPere", decToConsult.getLieu_naissance_pere());
		parameter.put("professionPere", decToConsult.getProfession_pere());
		parameter.put("domicilePere", decToConsult.getDomicile_pere());
		parameter.put("prenomMere", decToConsult.getPrenom_mere());
		parameter.put("nomMere", decToConsult.getNom_mere());
		parameter.put("jourMere", Tools.getDayForDate(decToConsult.getDate_naissance_mere()));
		parameter.put("moisMere", Tools.getMonthForDate(decToConsult.getDate_naissance_mere()));
		parameter.put("anneeMere", Tools.getYearForDate(decToConsult.getDate_naissance_mere()));
		parameter.put("dateNaissMere", Tools.getformatDate(decToConsult.getDate_naissance_mere()));
		parameter.put("lieuNaissMere", decToConsult.getLieu_naissance_mere());
		parameter.put("professionMere", decToConsult.getProfession_mere());
		parameter.put("domicileMere", decToConsult.getDomicile_mere());
			parameter.put("prenomDeclarant", decToConsult.getPrenom_declarant());
			parameter.put("nomDeclarant", decToConsult.getNom_declarant());
			parameter.put("professionDeclarant", decToConsult.getProfession_declarant());
			parameter.put("adresseDeclarant", decToConsult.getAdresse_declarant());
			parameter.put("cniDeclarant", decToConsult.getNum_identification_declarant());
	
			parameter.put("declarant", decToConsult.getDeclarant());
			
		
		if("".equals(decToConsult.getNumero_jugement()) && "".equals(this.decToConsult.getDate_jugement())){
			parameter.put("numJugement", "");
			parameter.put("dateJugement", "");
		}
		else{
			parameter.put("numJugement", decToConsult.getNumero_jugement());
			parameter.put("dateJugement", Tools.getformatDate(decToConsult.getDate_jugement()));

		}
		parameter.put("jourDeclaration", Tools.getDayForDateTime(Tools.toHumanDate(decToConsult.getDate_creation())));
		parameter.put("moisDeclaration",  Tools.getMonthForDateTime(Tools.toHumanDate(decToConsult.getDate_creation())));
		parameter.put("anneeDeclaration",  Tools.getYearForDateTime(Tools.toHumanDate(decToConsult.getDate_creation())));
		parameter.put("dateDeclaration", Tools.getformatDate(decToConsult.getDate_creation()));
		parameter.put("dateInscrit", Tools.getCurrentDateDDMMYYYY());
		parameter.put("lieuInscrit", decToConsult.getCreateurNaissance().getCentre().getCenterName());
		if(decToConsult.getMention_marginale()==null){
			parameter.put("mentionsMarginales", "");
		}
		else{
			parameter.put("mentionsMarginales", decToConsult.getMention_marginale());
		}
		parameter.put("typeDeclaration", decToConsult.getType_declaration());
		
                  
               
  		FacesContext context = FacesContext.getCurrentInstance();
  		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/acte/Acte.jrxml");
                  
  		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
  		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
  		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
  		JasperViewer.viewReport(jasperPrint,false);
      }
    
    
    
    @SuppressWarnings("unchecked")
	public void printActeMariage() throws JRException{
    	parameter.put("annee", Tools.getYearForDateTime(decMarToConsult.getDate_creation()));
		parameter.put("anneeMariage", Tools.getYearForDate(decMarToConsult.getDate_Mariage()));
		parameter.put("anneeNaisEpouse", Tools.getYearForDate(decMarToConsult.getDate_Naissance_Epouse()));
		parameter.put("anneeNaisEpoux", Tools.getYearForDate(decMarToConsult.getDate_Naissance_Epoux()));
		parameter.put("anneeNaisMereEpouse", Tools.getYearForDate(decMarToConsult.getDate_Naissance_Mere_Epouse()));
		parameter.put("anneeNaisMereEpoux", Tools.getYearForDate(decMarToConsult.getDate_Naissance_Mere_Epoux()));
		parameter.put("anneeNaisPereEpouse", Tools.getYearForDate(decMarToConsult.getDate_Naissance_Pere_Epouse()));
		parameter.put("anneeNaisPereEpoux", Tools.getYearForDate(decMarToConsult.getDate_Naissance_Pere_Epoux()));
		parameter.put("arrondissement",decMarToConsult.getCreateurMariage().getCentre().getCenterArrondissement().toUpperCase());
		parameter.put("autoritePatternelle","");
		
		parameter.put("commune", decMarToConsult.getCreateurMariage().getCentre().getCenterCollectivite().toUpperCase());
		
		parameter.put("dateCreation",Tools.toHumanDate(decMarToConsult.getDate_creation()));
		parameter.put("dateJugement",decMarToConsult.getDate_Jugement());
		parameter.put("dateMariage",decMarToConsult.getDate_Mariage());
		parameter.put("dateMariageLettre",NombreEnLettre.convert(Tools.getDayForDateTime(decMarToConsult.getDate_creation()))+" "
				+Tools.getMoisLettre(decMarToConsult.getDate_Mariage())+" "
				+NombreEnLettre.convert(Tools.getYearForDate(decMarToConsult.getDate_Mariage())));
		parameter.put("dateNaissEpouse",decMarToConsult.getDate_Naissance_Epouse());
		parameter.put("dateNaissEpoux",decMarToConsult.getDate_Naissance_Epoux());
		parameter.put("dateNaissMereEpouse",decMarToConsult.getDate_Naissance_Mere_Epouse());
		parameter.put("dateNaissMereEpoux",decMarToConsult.getDate_Naissance_Mere_Epoux());
		parameter.put("dateNaissPereEpouse",decMarToConsult.getDate_Naissance_Pere_Epouse());
		parameter.put("dateNaissPereEpoux",decMarToConsult.getDate_Naissance_Pere_Epoux());
		parameter.put("departement",decMarToConsult.getCreateurMariage().getCentre().getCenterDepartement().toUpperCase());
		parameter.put("domicileEpouse",decMarToConsult.getDomicile_Epouse());
		parameter.put("domicileEpoux",decMarToConsult.getDomicile_Epoux());
		parameter.put("domicileMereEpouse",decMarToConsult.getDomicile_Mere_Epouse());
		parameter.put("domicileMereEpoux",decMarToConsult.getDomicile_Mere_Epoux());
		parameter.put("domicilePereEpouse",decMarToConsult.getDomicile_Pere_Epouse());
		parameter.put("domicilePereEpoux",decMarToConsult.getDomicile_Pere_Epoux());
		parameter.put("dot",decMarToConsult.getDot_Mariage());
		
		parameter.put("genre",decMarToConsult.getGenre_Mariage());
		
		parameter.put("heureMariage",decMarToConsult.getHeure_Mariage());
		
		parameter.put("infosOfficier",decMarToConsult.getValidateurMariage().getUserPrenom()+" "
				+decMarToConsult.getValidateurMariage().getUserNom());
		
		parameter.put("jourMariage",Tools.getDayForDate(decMarToConsult.getDate_Mariage()));
		parameter.put("jourNaisEpouse",Tools.getDayForDate(decMarToConsult.getDate_Naissance_Epouse()));
		parameter.put("jourNaisEpoux",Tools.getDayForDate(decMarToConsult.getDate_Naissance_Epoux()));
		parameter.put("jourNaisMereEpouse",Tools.getDayForDate(decMarToConsult.getDate_Naissance_Mere_Epouse()));
		parameter.put("jourNaisMereEpoux",Tools.getDayForDate(decMarToConsult.getDate_Naissance_Mere_Epoux()));
		parameter.put("jourNaisPereEpouse",Tools.getDayForDate(decMarToConsult.getDate_Naissance_Pere_Epouse()));
		parameter.put("jourNaisPereEpoux",Tools.getDayForDate(decMarToConsult.getDate_Naissance_Pere_Epoux()));
		
		parameter.put("lieuMariage",decMarToConsult.getLieu_Mariage());
		parameter.put("lieuNaissEpouse",decMarToConsult.getLieu_Naissance_Epouse());
		parameter.put("lieuNaissEpoux",decMarToConsult.getLieu_Naissance_Epoux());
		parameter.put("lieuNaissMereEpouse",decMarToConsult.getLieu_Naissance_Mere_Epouse());
		parameter.put("lieuNaissMereEpoux",decMarToConsult.getLieu_Naissance_Mere_Epoux());
		parameter.put("lieuNaissPereEpouse",decMarToConsult.getLieu_Naissance_Pere_Epouse());
		parameter.put("lieuNaissPereEpoux",decMarToConsult.getLieu_Naissance_Pere_Epoux());
		
		parameter.put("mentionMarginale",decMarToConsult.getMentions_Marginales());
		parameter.put("moisMariage",Tools.getMonthForDate(decMarToConsult.getDate_Mariage()));
		parameter.put("moisNaisEpouse",Tools.getMonthForDate(decMarToConsult.getDate_Naissance_Epouse()));
		parameter.put("moisNaisEpoux",Tools.getMonthForDate(decMarToConsult.getDate_Naissance_Epoux()));
		parameter.put("moisNaisMereEpouse",Tools.getMonthForDate(decMarToConsult.getDate_Naissance_Mere_Epouse()));
		parameter.put("moisNaisMereEpoux",Tools.getMonthForDate(decMarToConsult.getDate_Naissance_Mere_Epoux()));
		parameter.put("moisNaisPereEpouse",Tools.getMonthForDate(decMarToConsult.getDate_Naissance_Pere_Epouse()));
		parameter.put("moisNaisPereEpoux",Tools.getMonthForDate(decMarToConsult.getDate_Naissance_Pere_Epoux()));
		
		parameter.put("nomCentre",decMarToConsult.getCreateurMariage().getCentre().getCenterName().toUpperCase());
		parameter.put("nomEpouse",decMarToConsult.getNom_Epouse());
		parameter.put("nomEpoux",decMarToConsult.getNom_Epoux());
		parameter.put("nomMereEpouse",decMarToConsult.getNom_Mere_Epouse());
		parameter.put("nomMereEpoux",decMarToConsult.getNom_Mere_Epoux());
		parameter.put("nomPereEpouse",decMarToConsult.getNom_Pere_Epouse());
		parameter.put("nomPereEpoux",decMarToConsult.getNom_Pere_Epoux());
		String num=decMarToConsult.getNumero_Acte()+"";
		char[] t=num.toCharArray();
		if(t.length==1){

			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", "");
			parameter.put("num3", "");
			parameter.put("num4", "");
			parameter.put("num5", "");
		}else if(t.length==2){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", "");
			parameter.put("num4", "");
			parameter.put("num5", "");
		}else if(t.length==3){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", Character.toString(t[t.length-3]));
			parameter.put("num4", "");
			parameter.put("num5", "");
		}else if(t.length==4){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", Character.toString(t[t.length-3]));
			parameter.put("num4", Character.toString(t[t.length-4]));
			parameter.put("num5", "");
		}else if(t.length==5){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", Character.toString(t[t.length-3]));
			parameter.put("num4", Character.toString(t[t.length-4]));
			parameter.put("num5", Character.toString(t[t.length-5]));
		}
		parameter.put("numJugement", decMarToConsult.getNumero_Jugement());
		
		parameter.put("precedentConjoint", decMarToConsult.getPrecedent_Conjoint());
		parameter.put("prenomsEpouse", decMarToConsult.getPrenom_Epouse());
		parameter.put("prenomsEpoux", decMarToConsult.getPrenom_Epoux());
		parameter.put("prenomsMereEpouse", decMarToConsult.getPrenom_Mere_Epouse());
		parameter.put("prenomsMereEpoux", decMarToConsult.getPrenom_Mere_Epoux());
		parameter.put("prenomsPereEpouse", decMarToConsult.getPrenom_Pere_Epouse());
		parameter.put("prenomsPereEpoux", decMarToConsult.getPrenom_Pere_Epoux());
		parameter.put("professionEpouse", decMarToConsult.getProfession_Epouse());
		parameter.put("professionEpoux", decMarToConsult.getProfession_Epoux());
		parameter.put("professionMereEpouse", decMarToConsult.getProfession_Mere_Epouse());
		parameter.put("professionMereEpoux", decMarToConsult.getProfession_Mere_Epoux());
		parameter.put("professionPereEpouse", decMarToConsult.getProfession_Pere_Epouse());
		parameter.put("professionPereEpoux", decMarToConsult.getProfession_Pere_Epoux());
		
		parameter.put("regimeMatrimonial", decMarToConsult.getRegime_Mariage());
		parameter.put("region", decMarToConsult.getCreateurMariage().getCentre().getCenterRegion().toUpperCase());
		parameter.put("residenceEpouse", decMarToConsult.getResidence_Epouse());
		parameter.put("residenceEpoux", decMarToConsult.getResidence_Epoux());
		
		parameter.put("temoin1", decMarToConsult.getPrenom_Temoin1_Epoux()+" "
				+decMarToConsult.getNom_Temoin1_Epoux()+" "
				+decMarToConsult.getProfession_Temoin1_Epoux()+" "
				+decMarToConsult.getDomicile_Temoin1_Epoux()+" "
				+decMarToConsult.getCni_Temoin1_Epoux());
		parameter.put("temoin2", decMarToConsult.getPrenom_Temoin2_Epoux()+" "
				+decMarToConsult.getNom_Temoin2_Epoux()+" "
				+decMarToConsult.getProfession_Temoin2_Epoux()+" "
				+decMarToConsult.getDomicile_Temoin2_Epoux()+" "
				+decMarToConsult.getCni_Temoin2_Epoux());
		parameter.put("temoin3", decMarToConsult.getPrenom_Temoin1_Epouse()+" "
				+decMarToConsult.getNom_Temoin1_Epouse()+" "
				+decMarToConsult.getProfession_Temoin1_Epouse()+" "
				+decMarToConsult.getDomicile_Temoin1_Epouse()+" "
				+decMarToConsult.getCni_Temoin1_Epouse());
		parameter.put("temoin4", decMarToConsult.getPrenom_Temoin2_Epouse()+" "
				+decMarToConsult.getNom_Temoin2_Epouse()+" "
				+decMarToConsult.getProfession_Temoin2_Epouse()+" "
				+decMarToConsult.getDomicile_Temoin2_Epouse()+" "
				+decMarToConsult.getCni_Temoin2_Epouse());
		parameter.put("typeCentre", decMarToConsult.getCreateurMariage().getCentre().getCenterType());
		
		FacesContext context = FacesContext.getCurrentInstance();
		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/acte/Acte_de_Mariage.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
    }
    
    
    @SuppressWarnings("unchecked")
   	public void printActeDeces() throws JRException{

		String num=decDCToConsult.getNumero_acte()+"";
		char[] t=num.toCharArray();
		if(t.length==1){

			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", "");
			parameter.put("num3", "");
			parameter.put("num4", "");
			parameter.put("num5", "");
			parameter.put("num6", "");
		}else if(t.length==2){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", "");
			parameter.put("num4", "");
			parameter.put("num5", "");
			parameter.put("num6", "");
		}else if(t.length==3){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", Character.toString(t[t.length-3]));
			parameter.put("num4", "");
			parameter.put("num5", "");
			parameter.put("num6", "");
		}else if(t.length==4){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", Character.toString(t[t.length-3]));
			parameter.put("num4", Character.toString(t[t.length-4]));
			parameter.put("num5", "");
			parameter.put("num6", "");
		}else if(t.length==5){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", Character.toString(t[t.length-3]));
			parameter.put("num4", Character.toString(t[t.length-4]));
			parameter.put("num5", Character.toString(t[t.length-5]));
			parameter.put("num6", "");
		}else if(t.length==6){
			parameter.put("num1", Character.toString(t[t.length-1]));
			parameter.put("num2", Character.toString(t[t.length-2]));
			parameter.put("num3", Character.toString(t[t.length-3]));
			parameter.put("num4", Character.toString(t[t.length-4]));
			parameter.put("num5", Character.toString(t[t.length-5]));
			parameter.put("num6", Character.toString(t[t.length-6]));
		}
		parameter.put("anneeChiffre", Tools.getYearForDateTime(decDCToConsult.getDate_creation()));
		parameter.put("numVolet", "");
		parameter.put("region", decDCToConsult.getCreateurDeces().getCentre().getCenterRegion().toUpperCase());
		parameter.put("departement", decDCToConsult.getCreateurDeces().getCentre().getCenterDepartement().toUpperCase());
		parameter.put("arrondissement", decDCToConsult.getCreateurDeces().getCentre().getCenterArrondissement().toUpperCase());
		parameter.put("commune", decDCToConsult.getCreateurDeces().getCentre().getCenterCollectivite().toUpperCase());
		parameter.put("type", decDCToConsult.getCreateurDeces().getCentre().getCenterType().toUpperCase());
		parameter.put("collectivite", decDCToConsult.getCreateurDeces().getCentre().getCenterName().toUpperCase());
		
		
		parameter.put("dateDeces", Tools.getformatDate(decDCToConsult.getDate_deces()));
		parameter.put("jourDeces", Tools.getDayForDate(decDCToConsult.getDate_deces()));
		parameter.put("moisDeces", Tools.getMonthForDate(decDCToConsult.getDate_deces()));
		parameter.put("anneeDeces", Tools.getYearForDate(decDCToConsult.getDate_deces()));
		parameter.put("heureDeces", decDCToConsult.getHeure_deces());
		parameter.put("lieuDeces", decDCToConsult.getLieu_deces());
		parameter.put("formationSanitaire", decDCToConsult.getFormation_sanitaire());
		
		
		
		//Informations du défunt
		parameter.put("prenomDefunt", decDCToConsult.getPrenom_defunt().toUpperCase());
		parameter.put("nomDefunt", decDCToConsult.getNom_defunt().toUpperCase());
		parameter.put("sexeDefunt", decDCToConsult.getSexe_defunt());
		parameter.put("dateNaissDefunt",  Tools.getformatDate(decDCToConsult.getDate_naissance_defunt()));
		parameter.put("lieuNaissDefunt", decDCToConsult.getLieu_naissance_defunt());
		parameter.put("jourEnfant", Tools.getDayForDate(decDCToConsult.getDate_naissance_defunt()));
		parameter.put("moisEnfant", Tools.getMonthForDate(decDCToConsult.getDate_naissance_defunt()));
		parameter.put("anneeEnfant", Tools.getYearForDate(decDCToConsult.getDate_naissance_defunt()));
		parameter.put("professionDefunt", decDCToConsult.getProfession_defunt());
		parameter.put("domicileDefunt", decDCToConsult.getDomicile_defunt());
		parameter.put("epouseDefunt", decDCToConsult.getMarie_a());
		
		
		//Information Pére du défunt
		parameter.put("prenomPere", decDCToConsult.getPrenom_pere());
		parameter.put("nomPere", decDCToConsult.getNom_pere());
		parameter.put("professionPere", decDCToConsult.getProfession_pere());
		parameter.put("domicilePere", decDCToConsult.getDomicile_pere());
		
		//Informations Mére du défunt
		parameter.put("prenomMere", decDCToConsult.getPrenom_mere());
		parameter.put("nomMere", decDCToConsult.getNom_mere());
		parameter.put("professionMere", decDCToConsult.getProfession_mere());
		parameter.put("domicileMere", decDCToConsult.getDomicile_mere());
		parameter.put("prenomDeclarant", decDCToConsult.getPrenom_declarant());
		parameter.put("nomDeclarant", decDCToConsult.getNom_declarant());
		parameter.put("professionDeclarant", decDCToConsult.getProfession_declarant());
		parameter.put("adresseDeclarant", decDCToConsult.getAdresse_declarant());
		parameter.put("degreParente", decDCToConsult.getDegre_parente());
		parameter.put("numJugement", decDCToConsult.getNum_jugement());
		parameter.put("dateJugement", decDCToConsult.getDate_jugement());
		parameter.put("jourDeclaration", Tools.getDayForDateTime(Tools.toHumanDate(decDCToConsult.getDate_creation())));
		parameter.put("moisDeclaration",  Tools.getMonthForDateTime(Tools.toHumanDate(decDCToConsult.getDate_creation())));
		parameter.put("anneeDeclaration",  Tools.getYearForDateTime(Tools.toHumanDate(decDCToConsult.getDate_creation())));
		parameter.put("dateDeclaration", Tools.getformatDate(Tools.toHumanDate(decDCToConsult.getDate_creation())));
		parameter.put("dateInscrit", Tools.getformatDate(Tools.getCurrentDateDDMMYYYY()));
		parameter.put("lieuInscrit", decDCToConsult.getCreateurDeces().getCentre().getCenterName());
		parameter.put("mentionsMarginales", decDCToConsult.getMention_marginale());
		parameter.put("typeDeclaration", decDCToConsult.getType_declaration());
		parameter.put("officier", decDCToConsult.getValidateurDeces().getUserPrenom()+" "
				+decDCToConsult.getValidateurDeces().getUserNom());
		FacesContext context = FacesContext.getCurrentInstance();
		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/acte/Acte_deces.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);

    }
    
    
    @SuppressWarnings("unchecked")
	public void printExtraitNaissance() throws IOException, JRException {
		parameter.put("region", decToConsult.getCreateurNaissance().getCentre().getCenterRegion().toUpperCase());
		parameter.put("departement", decToConsult.getCreateurNaissance().getCentre().getCenterDepartement().toUpperCase());
		parameter.put("collectivite", decToConsult.getCreateurNaissance().getCentre().getCenterCollectivite().toUpperCase());
		parameter.put("nomcentre", decToConsult.getCreateurNaissance().getCentre().getCenterName().toUpperCase());
		parameter.put("typecentre", decToConsult.getCreateurNaissance().getCentre().getCenterType().toUpperCase());
		parameter.put("anneeEnLettre",NombreEnLettre.convert(decToConsult.getDate_creation().substring(6, 10)));
		parameter.put("anneereg", decToConsult.getDate_creation().substring(6, 10));
		parameter.put("numRegEnLettre", NombreEnLettre.convert(decToConsult.getNumero_acte()+""));
		parameter.put("numActe", decToConsult.getNumero_acte());
		System.out.println(decToConsult.getNumero_acte());
		parameter.put("dateNaissEnLettre", NombreEnLettre.convert(Tools.getDayForDate(decToConsult.getDate_naissance_enfant()))+
				" "+Tools.getMoisLettre(decToConsult.getDate_naissance_enfant())+
				" "+NombreEnLettre.convert(decToConsult.getDate_naissance_enfant().substring(6, 10)));
		parameter.put("heures", Tools.getHeures(decToConsult.getHeure_naissance_enfant())+"");
		parameter.put("minutes", Tools.getMinutes(decToConsult.getHeure_naissance_enfant())+"");
		parameter.put("lieuNaissance", decToConsult.getLieu_naissance_enfant());
		parameter.put("sexe", decToConsult.getSexe());
		parameter.put("prenomEnfant", decToConsult.getPrenom_enfant());
		parameter.put("nomEnfant",decToConsult.getNom_enfant());
		parameter.put("prenomPere", decToConsult.getPrenom_pere());
		parameter.put("prenomMere", decToConsult.getPrenom_mere());
		parameter.put("nomMere", decToConsult.getNom_mere());
		if(decToConsult.getPays_naissance_enfanr()!=null)
			parameter.put("paysNaissance", decToConsult.getPays_naissance_enfanr());
		else
			parameter.put("paysNaissance","");
		
		if("".equals(decToConsult.getNumero_jugement())){
			parameter.put("numJugementEnLettre","");
			parameter.put("numJugement", "");
			parameter.put("dateInscrit", "");
			parameter.put("dateJugementEnLettre", "");
			parameter.put("anneeSurJug", "");
			parameter.put("anneeCreationJug", "");
			parameter.put("tribunal", "");
		}
		else{
			parameter.put("numJugementEnLettre", NombreEnLettre.convert(decToConsult.getNumero_jugement()));
			parameter.put("numJugement", decToConsult.getNumero_jugement());
			parameter.put("dateInscrit", decToConsult.getDate_creation());
			parameter.put("tribunal", decToConsult.getTribunal());
			parameter.put("dateJugementEnLettre", NombreEnLettre.convert(Tools.getDayForDate(decToConsult.getDate_jugement()))+
					" "+Tools.getMoisLettre(decToConsult.getDate_jugement())+
					" "+NombreEnLettre.convert(Tools.getYearForDate(decToConsult.getDate_jugement())));
			parameter.put("anneeSurJug", Tools.getYearForDate(decToConsult.getDate_jugement()));
			parameter.put("anneeCreationJug", decToConsult.getDate_creation().substring(6, 10));

		}

		parameter.put("dateCreation", Tools.getCurrentDate());
		parameter.put("nomAutorite", decToConsult.getValidateurNaissance().getUserNom());
		parameter.put("prenomAutorite", decToConsult.getValidateurNaissance().getUserPrenom());
		parameter.put("dateCreation",  Tools.getCurrentDate());
		if(decToConsult.getMention_marginale()==null){
			parameter.put("mentions", "");
		}
		else{
			parameter.put("mentions", decToConsult.getMention_marginale());
		}
		FacesContext context = FacesContext.getCurrentInstance();

		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/extrait/ExtraitOriginal.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);

	}
    
    
    @SuppressWarnings("unchecked")
	public void printExtraitDeces() throws IOException, JRException {
        
		parameter.put("region", decDCToConsult.getCreateurDeces().getCentre().getCenterRegion().toUpperCase());
   		parameter.put("departement", decDCToConsult.getCreateurDeces().getCentre().getCenterDepartement().toUpperCase());
   		parameter.put("arrondissement", decDCToConsult.getCreateurDeces().getCentre().getCenterArrondissement().toUpperCase());
   		parameter.put("typeCentre", decDCToConsult.getCreateurDeces().getCentre().getCenterType().toUpperCase());
   		parameter.put("nomCentre", decDCToConsult.getCreateurDeces().getCentre().getCenterName().toUpperCase());
   		parameter.put("numero", decDCToConsult.getNumero_acte()+"");
   		parameter.put("annee", Tools.getYearForDateTime(decDCToConsult.getDate_creation()));
   		parameter.put("dateDecesEnLettre", NombreEnLettre.convert(Tools.getDayForDate(decDCToConsult.getDate_deces()))+" "
   				+Tools.getMoisLettre(decDCToConsult.getDate_deces())+" "
   				+NombreEnLettre.convert(Tools.getYearForDate(decDCToConsult.getDate_deces())));
   		parameter.put("lieuDeces", decDCToConsult.getLieu_deces());
   		parameter.put("infosOfficier", decDCToConsult.getValidateurDeces().getUserPrenom()+" "
   				+decDCToConsult.getValidateurDeces().getUserNom());
   		parameter.put("dateCreationEnLettre", NombreEnLettre.convert(Tools.getDayForDateTime(decDCToConsult.getDate_creation()))+" "
   				+Tools.getMoisLettre(decDCToConsult.getDate_creation())+" "
   				+NombreEnLettre.convert(Tools.getYearForDateTime(decDCToConsult.getDate_creation())));
   		parameter.put("heureDeces", decDCToConsult.getHeure_deces().substring(0, 2));
   		parameter.put("minutesDeces", decDCToConsult.getHeure_deces().substring(3, 5));
   		parameter.put("defunt", decDCToConsult.getPrenom_defunt()+" "+decDCToConsult.getNom_defunt());
   		String sexe;
   		if(decDCToConsult.getSexe_defunt().equalsIgnoreCase("masculin"))
   			sexe="fils";
   		else
   			sexe="fille";
   		String infos="";
   		if(!"".equals(decDCToConsult.getPrenom_pere()) && !"".equals(decDCToConsult.getPrenom_mere()) && !"".equals(decDCToConsult.getNom_pere()) && !"".equals(decDCToConsult.getNom_mere())){
   			infos=", de son vivant était "+sexe+" de "+decDCToConsult.getPrenom_pere()+" "+decDCToConsult.getNom_pere()+" et de "+decDCToConsult.getPrenom_mere()+" "+decDCToConsult.getNom_mere();
   		}
   		parameter.put("infosDefunt", decDCToConsult.getPrenom_defunt()+" "
   				+decDCToConsult.getNom_defunt()+" né(e) le "
   				+decDCToConsult.getDate_naissance_defunt()+" é "
   				+decDCToConsult.getLieu_naissance_defunt()+infos);
   		parameter.put("anneeLettre", NombreEnLettre.convert(Tools.getYearForDateTime(decDCToConsult.getDate_creation())));
   		parameter.put("heureCreation", Tools.getCurrentDateTime().substring(11, 13));
   		parameter.put("minuteCreation", Tools.getCurrentDateTime().substring(14, 16));
   		parameter.put("dateCourant", Tools.getformatDate(Tools.getCurrentDateDDMMYYYY()));
   		
   		FacesContext context = FacesContext.getCurrentInstance();
		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/extrait/Extrait_du_registre_acte_de_Deces.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
   		
	}
    
    
    
	@SuppressWarnings("unchecked")
	public void printBulletinNaissance() throws IOException, JRException {
		parameter.put("region", decToConsult.getCreateurNaissance().getCentre().getCenterRegion().toUpperCase());
		parameter.put("departement", decToConsult.getCreateurNaissance().getCentre().getCenterDepartement().toUpperCase());
		parameter.put("arrondissement", decToConsult.getCreateurNaissance().getCentre().getCenterArrondissement().toUpperCase());
		parameter.put("typecentre", decToConsult.getCreateurNaissance().getCentre().getCenterType().toUpperCase());
		parameter.put("nomcentre", decToConsult.getCreateurNaissance().getCentre().getCenterName().toUpperCase());
		parameter.put("numActe", Integer.parseInt(decToConsult.getNumero_acte()));
		parameter.put("datedeclaration", decToConsult.getDate_creation());
		parameter.put("heures", decToConsult.getHeure_naissance_enfant());
		parameter.put("sexe", decToConsult.getSexe());
		parameter.put("infosEnfant", decToConsult.getLieu_naissance_enfant()+",  "+decToConsult.getPrenom_enfant());
		if(!"".equals(decToConsult.getPrenom_pere()) && !"".equals(decToConsult.getNom_pere())){
			parameter.put("infosPere", decToConsult.getPrenom_pere()+" "+decToConsult.getNom_pere());
		}
		else{
			parameter.put("infosPere", "");
		}
		if(!"".equals(decToConsult.getPrenom_mere()) && !"".equals(decToConsult.getNom_mere())){
			parameter.put("infosMere", decToConsult.getPrenom_mere()+" "+decToConsult.getNom_mere());
		}
		else{
			parameter.put("infosMere", "");
		}

		if(!"".equals(decToConsult.getDate_naissance_enfant()) && decToConsult.getDate_naissance_enfant()!=null){
			String annee=NombreEnLettre.convert(decToConsult.getDate_naissance_enfant().substring(6, 10));
			String mois=Tools.getMoisLettre(decToConsult.getDate_naissance_enfant());
			String jour=NombreEnLettre.convert(Tools.getDayForDate(decToConsult.getDate_naissance_enfant()));
			parameter.put("dateNaissEnLettre", jour+" "+mois+" "+annee);
		}
		else{
			parameter.put("dateNaissEnLettre", "");
		}
		parameter.put("dateCreation",  Tools.getCurrentDateDDMMYYYY());

		FacesContext context = FacesContext.getCurrentInstance();
		
		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/bulletin/BulletinNaissance.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
	}
    
	
   	@SuppressWarnings("unchecked")
	public void printBulletinDeces() throws IOException, JRException {
   		parameter.put("region", decDCToConsult.getCreateurDeces().getCentre().getCenterRegion().toUpperCase());
   		parameter.put("departement", decDCToConsult.getCreateurDeces().getCentre().getCenterDepartement().toUpperCase());
   		parameter.put("arrondissement", decDCToConsult.getCreateurDeces().getCentre().getCenterArrondissement().toUpperCase());
   		parameter.put("typecentre", decDCToConsult.getCreateurDeces().getCentre().getCenterType().toUpperCase());
   		parameter.put("nomcentre", decDCToConsult.getCreateurDeces().getCentre().getCenterName().toUpperCase());
   		parameter.put("numActe", decDCToConsult.getNumero_acte()+"");
   		parameter.put("heures", Tools.getHeures(decDCToConsult.getHeure_deces())+"");
   		parameter.put("minutes", Tools.getMinutes(decDCToConsult.getHeure_deces())+"");
   		parameter.put("age", Tools.getAge(decDCToConsult.getDate_naissance_defunt()));
   		parameter.put("lieuNaissance", decDCToConsult.getLieu_naissance_defunt());
   		parameter.put("dateNaissance", Tools.getformatDate(decDCToConsult.getDate_naissance_defunt()));
   		parameter.put("annee", Tools.getYearForDateTime(Tools.toHumanDate(decDCToConsult.getDate_creation())));
   		parameter.put("lieuDeces", decDCToConsult.getLieu_deces());
   		parameter.put("defunt", decDCToConsult.getPrenom_defunt()+" "+decDCToConsult.getNom_defunt());
   		parameter.put("pays", decDCToConsult.getPays_deces());
   		if(decDCToConsult.getSexe_defunt().equalsIgnoreCase("masculin"))
   			parameter.put("sexe", "fils");
   		else
   			parameter.put("sexe", "fille");
   		parameter.put("infosPere", decDCToConsult.getPrenom_pere()+" "+decDCToConsult.getNom_pere());
   		parameter.put("infosMere", decDCToConsult.getPrenom_mere()+" "+decDCToConsult.getNom_mere());
   		parameter.put("infosAutorite", decDCToConsult.getValidateurDeces().getUserPrenom()+" "+decDCToConsult.getValidateurDeces().getUserNom());
   		parameter.put("dateDecesEnLettre", NombreEnLettre.convert(Tools.getDayForDate(decDCToConsult.getDate_deces()))+" "
   				+Tools.getMoisLettre(decDCToConsult.getDate_deces())+" "
   				+NombreEnLettre.convert(Tools.getYearForDate(decDCToConsult.getDate_deces())));
   		parameter.put("dateCreation", Tools.getCurrentDateDDMMYYYY());
   		
   		FacesContext context = FacesContext.getCurrentInstance();
		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/bulletin/BulletinDecesV2.jrxml");
		
		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
   	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws JRException
	 */
	@SuppressWarnings("unchecked")
	public void printCopieLitteraleNaissance() throws IOException, JRException {
		parameter.put("region", decToConsult.getCreateurNaissance().getCentre().getCenterRegion().toUpperCase());
		parameter.put("departement", decToConsult.getCreateurNaissance().getCentre().getCenterDepartement().toUpperCase());
		parameter.put("arrond", decToConsult.getCreateurNaissance().getCentre().getCenterCollectivite().toUpperCase());
		parameter.put("typecentre", decToConsult.getCreateurNaissance().getCentre().getCenterType().toUpperCase());
		parameter.put("nomcentre", decToConsult.getCreateurNaissance().getCentre().getCenterName().toUpperCase());
		parameter.put("numActe", Integer.parseInt(decToConsult.getNumero_acte()));
		parameter.put("dateNaissEnLettre", NombreEnLettre.convert(Tools.getDayForDate(decToConsult.getDate_naissance_enfant()))+" "
				+Tools.getMoisLettre(decToConsult.getDate_naissance_enfant())+" "
				+NombreEnLettre.convert(decToConsult.getDate_naissance_enfant().substring(6, 10)));
		parameter.put("dateCreation",  Tools.getformatDate(Tools.getCurrentDate()));
		parameter.put("annee",  Tools.getYearForDateTime(decToConsult.getDate_creation()));
		parameter.put("datenaissance", Tools.getformatDate(decToConsult.getDate_naissance_enfant()));
		parameter.put("prenomEnfant",  decToConsult.getPrenom_enfant());
		parameter.put("nomEnfant", decToConsult.getNom_enfant());
		parameter.put("heures", decToConsult.getHeure_naissance_enfant());
		parameter.put("lieuNaissance", decToConsult.getLieu_naissance_enfant());
		parameter.put("sexe", decToConsult.getSexe());
		if(!"".equals(decToConsult.getPrenom_pere()) && !"".equals(decToConsult.getNom_pere())){
			parameter.put("infosPere", decToConsult.getPrenom_pere()+" "+decToConsult.getNom_pere()+", né le "
					+decToConsult.getDate_naissance_pere()+" é "+decToConsult.getLieu_naissance_pere()+", "
					+decToConsult.getProfession_pere()+" et domicilié é "+decToConsult.getDomicile_pere());
		}
		else{
			parameter.put("infosPere", "");
		}
		if(!"".equals(decToConsult.getPrenom_mere()) && !"".equals(decToConsult.getNom_mere())){
			parameter.put("infosMere",  decToConsult.getPrenom_mere()+" "
					+decToConsult.getNom_mere()+", née le "+decToConsult.getDate_naissance_mere()+" é "
					+decToConsult.getLieu_naissance_mere()+", "+decToConsult.getProfession_mere()+" et domiciliée é "
					+decToConsult.getDomicile_mere());
		}
		else{
			parameter.put("infosMere", "");
		}

		if(decToConsult.getDeclarant().equalsIgnoreCase("Autre")){
			parameter.put("declarant", decToConsult.getPrenom_declarant()+" "+decToConsult.getNom_declarant()+" de numéro de carte nationale d'identité "+decToConsult.getNum_identification_declarant());
		}
		else{
			parameter.put("declarant", decToConsult.getDeclarant());
		}
		if( decToConsult.getMention_marginale()==null){
			parameter.put("mentions", "");
		}
		else{
			parameter.put("mentions", decToConsult.getMention_marginale());
		}
		parameter.put("centre", decToConsult.getCreateurNaissance().getCentre().getCenterType()+" de "+decToConsult.getCreateurNaissance().getCentre().getCenterName());
		parameter.put("nomAutorite", decToConsult.getValidateurNaissance().getUserNom());
		parameter.put("prenomAutorite", decToConsult.getValidateurNaissance().getUserPrenom());
		parameter.put("heureCreation", Tools.getCurrentDateTime().substring(11,13));
		parameter.put("minuteCreation", Tools.getCurrentDateTime().substring(14,16));
		Long jour=Long.parseLong(Tools.getDayForDate(Tools.getCurrentDate()));
		String mois=Tools.getMoisLettre(Tools.getCurrentDate());
		Long annee=Long.parseLong(Tools.getYearForDate(Tools.getCurrentDate()));
		parameter.put("dateCreationEnLettre", NombreEnLettre.convert(jour+"")+" "+mois+" "+NombreEnLettre.convert(annee+""));

		FacesContext context = FacesContext.getCurrentInstance();

		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/copie/CopieLitterale.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
	}
	
	@SuppressWarnings("unchecked")
	public void printCopieLitteraleMariage() throws IOException, JRException {
		parameter.put("region", decMarToConsult.getCreateurMariage().getCentre().getCenterRegion().toUpperCase());
		parameter.put("departement", decMarToConsult.getCreateurMariage().getCentre().getCenterDepartement().toUpperCase());
		parameter.put("arrondissement", decMarToConsult.getCreateurMariage().getCentre().getCenterArrondissement().toUpperCase());
		parameter.put("region", decMarToConsult.getCreateurMariage().getCentre().getCenterRegion().toUpperCase());
		parameter.put("typeCentre", decMarToConsult.getCreateurMariage().getCentre().getCenterType().toUpperCase());
		parameter.put("nomCentre", decMarToConsult.getCreateurMariage().getCentre().getCenterName().toUpperCase());
		parameter.put("numero", decMarToConsult.getNumero_Acte());
		parameter.put("annee", decMarToConsult.getDate_creation().substring(6, 10));
		//parameter.put("dateCreation",Tools.toHumanDate(decMarToConsult.getDate_creation()));
		parameter.put("dateCreation", NombreEnLettre.convert(Tools.getDayForDate(Tools.getCurrentDateDDMMYYYY()))+" "
				+Tools.getMoisLettre(Tools.getCurrentDateDDMMYYYY())+" "
				+NombreEnLettre.convert(Tools.getYearForDate(Tools.getCurrentDateDDMMYYYY())));
		parameter.put("infosOfficier",decMarToConsult.getValidateurMariage().getUserPrenom()+" "
				+decMarToConsult.getValidateurMariage().getUserNom());
		parameter.put("heures",decMarToConsult.getHeure_Mariage().substring(0, 2));
		parameter.put("minutes",decMarToConsult.getHeure_Mariage().substring(3, 5));
		
		parameter.put("dateMariageLettre", NombreEnLettre.convert(Tools.getDayForDate(decMarToConsult.getDate_Mariage()))+" "
				+Tools.getMoisLettre(decMarToConsult.getDate_Mariage())+" "
				+NombreEnLettre.convert(Tools.getYearForDate(decMarToConsult.getDate_Mariage())));
		parameter.put("dot",decMarToConsult.getDot_Mariage());
		parameter.put("genre",decMarToConsult.getGenre_Mariage());
				
		
		parameter.put("infosEpoux", decMarToConsult.getPrenom_Epoux()+" "
				+decMarToConsult.getNom_Epoux());
		parameter.put("professionEpoux", decMarToConsult.getProfession_Epoux());
		parameter.put("dateNaissEpoux", decMarToConsult.getDate_Naissance_Epoux());
		parameter.put("lieuNaissEpoux", decMarToConsult.getLieu_Naissance_Epoux());
		parameter.put("precedentConjoint", decMarToConsult.getPrecedent_Conjoint());
		parameter.put("domicileEpoux", decMarToConsult.getDomicile_Epoux());
		parameter.put("residenceEpoux", decMarToConsult.getResidence_Epoux());
		
		
		parameter.put("infosEpouse", decMarToConsult.getPrenom_Epouse()+" "
				+decMarToConsult.getNom_Epouse());
		parameter.put("professionEpouse", decMarToConsult.getProfession_Epouse());
		parameter.put("dateNaissEpouse", decMarToConsult.getDate_Naissance_Epouse());
		parameter.put("lieuNaissEpouse", decMarToConsult.getLieu_Naissance_Epouse());
		parameter.put("domicileEpouse", decMarToConsult.getDomicile_Epouse());
		parameter.put("residenceEpouse", decMarToConsult.getResidence_Epouse());
		parameter.put("precedentConjointEpouse","");
		
		parameter.put("pereEpouse", decMarToConsult.getPrenom_Pere_Epouse()+" "
				+decMarToConsult.getNom_Pere_Epouse());
		parameter.put("professionPereEpouse", decMarToConsult.getProfession_Pere_Epouse());
		parameter.put("dateNaissPereEpouse", decMarToConsult.getDate_Naissance_Pere_Epouse());
		parameter.put("lieuNaissPereEpouse", decMarToConsult.getLieu_Naissance_Pere_Epouse());
		parameter.put("domicilePereEpouse", decMarToConsult.getDomicile_Pere_Epouse());
		
		parameter.put("mereEpouse", decMarToConsult.getPrenom_Mere_Epouse()+" "+decMarToConsult.getNom_Mere_Epouse());
		parameter.put("professionMereEpouse", decMarToConsult.getProfession_Mere_Epouse());
		parameter.put("dateNaissMereEpouse", decMarToConsult.getDate_Naissance_Mere_Epouse());
		parameter.put("lieuNaissMereEpouse", decMarToConsult.getLieu_Naissance_Mere_Epouse());
		parameter.put("domicileMereEpouse", decMarToConsult.getDomicile_Mere_Epouse());
		
		parameter.put("infosPereEpoux", decMarToConsult.getPrenom_Pere_Epoux()+" "+decMarToConsult.getNom_Pere_Epoux());
		parameter.put("professionPereEpoux", decMarToConsult.getProfession_Pere_Epoux());
		parameter.put("dateNaissPereEpoux", decMarToConsult.getDate_Naissance_Pere_Epoux());
		parameter.put("lieuNaissPereEpoux", decMarToConsult.getLieu_Naissance_Pere_Epoux());
		parameter.put("domicilePereEpoux", decMarToConsult.getDomicile_Pere_Epoux());
		
		parameter.put("infosMereEpoux", decMarToConsult.getPrenom_Mere_Epoux()+" "+decMarToConsult.getNom_Mere_Epoux());
		parameter.put("professionMereEpoux", decMarToConsult.getProfession_Mere_Epoux());
		parameter.put("dateNaissMereEpoux", decMarToConsult.getDate_Naissance_Mere_Epoux());
		parameter.put("lieuNaissMereEpoux", decMarToConsult.getLieu_Naissance_Mere_Epoux());
		parameter.put("domicileMereEpoux", decMarToConsult.getDomicile_Mere_Epoux());
		
		
		parameter.put("temoin1", decMarToConsult.getPrenom_Temoin1_Epoux()+" "+decMarToConsult.getNom_Temoin1_Epoux()+" - "+decMarToConsult.getProfession_Temoin1_Epoux()+" - "+decMarToConsult.getDomicile_Temoin1_Epoux()+" - "+decMarToConsult.getCni_Temoin1_Epoux());
		parameter.put("temoin2", decMarToConsult.getPrenom_Temoin1_Epouse()+" "+decMarToConsult.getNom_Temoin1_Epouse()+" - "+decMarToConsult.getProfession_Temoin1_Epouse()+" - "+decMarToConsult.getDomicile_Temoin1_Epouse()+" - "+decMarToConsult.getCni_Temoin1_Epouse());
		
		FacesContext context = FacesContext.getCurrentInstance();
		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/copie/CopieLitteraleMariage.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
	}
	
	
  	@SuppressWarnings("unchecked")
	public void printCopieLitteraleDeces() throws IOException, JRException {
    
   		parameter.put("region", decDCToConsult.getCreateurDeces().getCentre().getCenterRegion().toUpperCase());
   		parameter.put("departement", decDCToConsult.getCreateurDeces().getCentre().getCenterDepartement().toUpperCase());
   		parameter.put("arrond", decDCToConsult.getCreateurDeces().getCentre().getCenterArrondissement().toUpperCase());
   		parameter.put("typecentre", decDCToConsult.getCreateurDeces().getCentre().getCenterType().toUpperCase());
   		parameter.put("nomcentre", decDCToConsult.getCreateurDeces().getCentre().getCenterName().toUpperCase());
   		parameter.put("numActe", decDCToConsult.getNumero_acte());
   		parameter.put("annee", Tools.getYearForDateTime(decDCToConsult.getDate_creation()));
   		parameter.put("dateDeces", Tools.getDayForDate(decDCToConsult.getDate_deces()));
   		parameter.put("prenomDefunt", decDCToConsult.getPrenom_defunt());
   		parameter.put("nomDefunt", decDCToConsult.getNom_defunt());
   		parameter.put("heures", Tools.getHeures(decDCToConsult.getHeure_deces())+"");
   		parameter.put("sexe", decDCToConsult.getSexe_defunt());
   		parameter.put("lieuDeces", decDCToConsult.getLieu_deces());
   		parameter.put("mentions", decDCToConsult.getMention_marginale());
   		parameter.put("prenomAutorite", decDCToConsult.getValidateurDeces().getUserPrenom());
   		parameter.put("nomAutorite", decDCToConsult.getValidateurDeces().getUserNom());
   		parameter.put("heureCreation", Tools.getCurrentDateTime().substring(11, 13));
   		parameter.put("minuteCreation", Tools.getCurrentDateTime().substring(14, 16));
   		parameter.put("infosPere", decDCToConsult.getPrenom_pere()+" "+decDCToConsult.getNom_pere());
   		parameter.put("infosMere", decDCToConsult.getPrenom_mere()+" "+decDCToConsult.getNom_mere());
   		parameter.put("centre", decDCToConsult.getCreateurDeces().getCentre().getCenterType()+" de "+decDCToConsult.getCreateurDeces().getCentre().getCenterName());
   		parameter.put("declarant", decDCToConsult.getPrenom_declarant()+" "+decDCToConsult.getNom_declarant()+" "+decDCToConsult.getAdresse_declarant()+" "+decDCToConsult.getDegre_parente());
   		parameter.put("minutes", Tools.getMinutes(decDCToConsult.getHeure_deces())+"");
   		if(decDCToConsult.getSexe_defunt().equalsIgnoreCase("masculin"))
   			parameter.put("genre", "Fils de");
   		else
   			parameter.put("genre", "Fille de");
   		parameter.put("dateDecesEnLettre", NombreEnLettre.convert(Tools.getDayForDate(decDCToConsult.getDate_deces()))+" "
   				+Tools.getMoisLettre(decDCToConsult.getDate_deces())+" "
   				+NombreEnLettre.convert(Tools.getYearForDate(decDCToConsult.getDate_deces())));
   		parameter.put("dateCreationEnLettre", NombreEnLettre.convert(Tools.getDayForDate(Tools.getCurrentDateDDMMYYYY()))+" "
   				+Tools.getMoisLettre(Tools.getCurrentDateDDMMYYYY())+" "
   				+NombreEnLettre.convert(Tools.getYearForDate(Tools.getCurrentDateDDMMYYYY())));
   		
   		
   		FacesContext context = FacesContext.getCurrentInstance();
		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/copie/CopieLitteraleDeces.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
   		
   	}

	public DeclarationNaissance getFilterNaissance() {
		return filterNaissance;
	}

	public void setFilterNaissance(DeclarationNaissance filterNaissance) {
		this.filterNaissance = filterNaissance;
	}

	public DeclarationDeces getFilterDeces() {
		return filterDeces;
	}

	public void setFilterDeces(DeclarationDeces filterDeces) {
		this.filterDeces = filterDeces;
	}

	public DeclarationMariage getFilterMariage() {
		return filterMariage;
	}

	public void setFilterMariage(DeclarationMariage filterMariage) {
		this.filterMariage = filterMariage;
	}
    
  
	public String registre(){
		if(MyUtil.getProfil() != null){
		   registreYear = null;
		   if("Tous".equalsIgnoreCase(this.selectedAnnees)){
			   registreYear = acteService.getAllActe();
		   }
		   else{
			   registreYear = acteService.registresCurrentYear(this.selectedAnnees);
		   }
		   return "/views/" + MyUtil.getProfil() + "/registre/naissance-for-year?faces-redirect=true";
		}
		else
			return MyUtil.pathLogin();
	   }
	
	
	public String registreMariage(){
		if(MyUtil.getProfil() != null){
			registreMariageYear = null;
		   if("Tous".equalsIgnoreCase(this.selectedAnnees)){
			   registreMariageYear = marService.getRegistreMariage();
		   }
		   else{
			   registreMariageYear = marService.registreMariageCurrentYear(this.selectedAnnees);
		   }
		   return "/views/" + MyUtil.getProfil() + "/registre/mariage-for-year?faces-redirect=true";
		}
		else
			return MyUtil.pathLogin();
	   }
	
	
	public String registreDeces(){
		if(MyUtil.getProfil() != null){
		registreDecesYear = null;
	   if("Tous".equalsIgnoreCase(this.selectedAnnees)){
		   registreDecesYear = deceService.getRegistreDece();
	   }
	   else{
		   registreDecesYear = deceService.getRegistreDeceByNum(this.selectedAnnees);
	   }
	   return "/views/" + MyUtil.getProfil() + "/registre/dece-for-year?faces-redirect=true";
		}
		else
			return MyUtil.pathLogin();
   }
	   

		public List<String> getAnnees() {
			return annees;
		}



	public void setAnnees(List<String> annees) {
		this.annees = annees;
	}



		/**
	 * @return the selectedAnnees
	 */
	public String getSelectedAnnees() {
		return selectedAnnees;
	}



	/**
	 * @param selectedAnnees the selectedAnnees to set
	 */
	public void setSelectedAnnees(String selectedAnnees) {
		this.selectedAnnees = selectedAnnees;
	}

		


		public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}




		/**
	 * @return the registreYear
	 */
	public List<DeclarationNaissance> getRegistreYear() {
		return registreYear;
	}

	/**
	 * @param registreYear the registreYear to set
	 */
	public void setRegistreYear(List<DeclarationNaissance> registreYear) {
		this.registreYear = registreYear;
	}
	
	/**
	 * @return the registreCurrentYear
	 */
	public List<DeclarationNaissance> getRegistreCurrentYear() {
		String year = Tools.getCurrentDate().substring(6, 10);
		registreCurrentYear = acteService.registresCurrentYear(year);
		return registreCurrentYear;
	}

	/**
	 * @param registreCurrentYear the registreCurrentYear to set
	 */
	public void setRegistreCurrentYear(List<DeclarationNaissance> registreCurrentYear) {
		this.registreCurrentYear = registreCurrentYear;
	}


	/**
	 * @return the registreMariageCurrentYear
	 */
	public List<DeclarationMariage> getRegistreMariageCurrentYear() {
		String year = Tools.getCurrentDate().substring(6, 10);
		registreMariageCurrentYear = marService.registreMariageCurrentYear(year);
		return registreMariageCurrentYear;
	}



	/**
	 * @param registreMariageCurrentYear the registreMariageCurrentYear to set
	 */
	public void setRegistreMariageCurrentYear(
			List<DeclarationMariage> registreMariageCurrentYear) {
		this.registreMariageCurrentYear = registreMariageCurrentYear;
	}


	/**
	 * @return the registreMariageYear
	 */
	public List<DeclarationMariage> getRegistreMariageYear() {
		return registreMariageYear;
	}



	/**
	 * @param registreMariageYear the registreMariageYear to set
	 */
	public void setRegistreMariageYear(List<DeclarationMariage> registreMariageYear) {
		this.registreMariageYear = registreMariageYear;
	}


	/**
	 * @return the registreDecesCurrentYear
	 */
	public List<DeclarationDeces> getRegistreDecesCurrentYear() {
		String year = Tools.getCurrentDate().substring(6, 10);
		registreDecesCurrentYear = deceService.getRegistreDeceByNum(year);
		return registreDecesCurrentYear;
	}



	/**
	 * @param registreDecesCurrentYear the registreDecesCurrentYear to set
	 */
	public void setRegistreDecesCurrentYear(List<DeclarationDeces> registreDecesCurrentYear) {
		this.registreDecesCurrentYear = registreDecesCurrentYear;
	}


	/**
	 * @return the registreDecesYear
	 */
	public List<DeclarationDeces> getRegistreDecesYear() {
		return registreDecesYear;
	}



	/**
	 * @param registreDecesYear the registreDecesYear to set
	 */
	public void setRegistreDecesYear(List<DeclarationDeces> registreDecesYear) {
		this.registreDecesYear = registreDecesYear;
	}


	/**
	 * @return the decN
	 */
	public int getDecN() {
		decN = acteService.getAllDeclaration().size();
		return decN;
	}



	/**
	 * @param decN the decN to set
	 */
	public void setDecN(int decN) {
		this.decN = decN;
	}
	
	public String listeDecN(){
		String route = "";
		if(MyUtil.getProfil() != null){
			route = "/views/" + MyUtil.getProfil() + "/liste-declaration/naissance?faces-redirect=true";
		}
		else{
			route = "/views/login?faces-redirect=true";
		}
		return route;
	}
	
	
	public String listeDecM(){
		String route = "";
		if(MyUtil.getProfil() != null){
			route = "/views/" + MyUtil.getProfil() + "/liste-declaration/mariage?faces-redirect=true";
		}
		else{
			route = "/views/login?faces-redirect=true";
		}
		return route;
	}
	
	
	public String listeDecD(){
		String route = "";
		if(MyUtil.getProfil() != null){
			route = "/views/" + MyUtil.getProfil() + "/liste-declaration/deces?faces-redirect=true";
		}
		else{
			route = "/views/login?faces-redirect=true";
		}
		return route;
	}
	
	public String listePieceDel(){
		String route = "";
		if(MyUtil.getProfil() != null){
			route = "/views/" + MyUtil.getProfil() + "/piecesAnnexes/pieces_delivred_by_user?faces-redirect=true";
		}
		else{
			route = "/views/login?faces-redirect=true";
		}
		return route;
	}

	/**
	 * @return the decD
	 */
	public int getDecD() {
		decD = deceService.getAllDeclarationDece().size();
		return decD;
	}



	/**
	 * @param decD the decD to set
	 */
	public void setDecD(int decD) {
		this.decD = decD;
	}


	/**
	 * @return the decM
	 */
	public int getDecM() {
		decM = marService.getAllDecMariage().size();
		return decM;
	}



	/**
	 * @param decM the decM to set
	 */
	public void setDecM(int decM) {
		this.decM = decM;
	}


	/**
	 * @return the decNBU
	 */
	public int getDecNBU() {
		decNBU = acteService.getAllDeclarationByUser(MyUtil.getUserLogged()).size();
		return decNBU;
	}



	/**
	 * @param decNBU the decNBU to set
	 */
	public void setDecNBU(int decNBU) {
		this.decNBU = decNBU;
	}


	/**
	 * @return the decMBU
	 */
	public int getDecMBU() {
		decMBU = marService.getAllDecMaraigeByUser(MyUtil.getUserLogged()).size();
		return decMBU;
	}



	/**
	 * @param decMBU the decMBU to set
	 */
	public void setDecMBU(int decMBU) {
		this.decMBU = decMBU;
	}


	/**
	 * @return the decDBU
	 */
	public int getDecDBU() {
		decDBU = deceService.getAllDeclarationDeceByUser(MyUtil.getUserLogged()).size();
		return decDBU;
	}



	/**
	 * @param decDBU the decDBU to set
	 */
	public void setDecDBU(int decDBU) {
		this.decDBU = decDBU;
	}


	/**
	 * @return the piecDel
	 */
	public int getPiecDel() {
		piecDel = delService.getAllPiecesDel().size();
		return piecDel;
	}



	/**
	 * @param piecDel the piecDel to set
	 */
	public void setPiecDel(int piecDel) {
		this.piecDel = piecDel;
	}


	/**
	 * @return the pieceDelBU
	 */
	public int getPieceDelBU() {
		pieceDelBU = delService.getAllPieceBU(MyUtil.getUserLogged()).size();
		return pieceDelBU;
	}



	/**
	 * @param pieceDelBU the pieceDelBU to set
	 */
	public void setPieceDelBU(int pieceDelBU) {
		this.pieceDelBU = pieceDelBU;
	}


	/**
	 * @return the pdByUser
	 */
	public List<DelivredPieces> getPdByUser() {
		pdByUser = delService.getAllPiecesDel();
		return pdByUser;
	}



	/**
	 * @param pdByUser the pdByUser to set
	 */
	public void setPdByUser(List<DelivredPieces> pdByUser) {
		this.pdByUser = pdByUser;
	}


	/**
	 * @return the pieceDel
	 */
	public DelivredPieces getPieceDel() {
		return pieceDel;
	}



	/**
	 * @param pieceDel the pieceDel to set
	 */
	public void setPieceDel(DelivredPieces pieceDel) {
		this.pieceDel = pieceDel;
	}

	@SuppressWarnings("static-access")
	public void paiement(){
		this.pieceDel.setDate_paiement(Tools.getCurrentDateTime());
		this.pieceDel.setPaiement(true);
		this.pieceDel.setUsermodify(MyUtil.getUserLogged());
		if(delService.updatePiece(this.pieceDel)){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dépot de paiement effecttué avec succès.", null);
			FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Le dépot ne s'estt pas correctement effectué.", null);
			FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	private int anneeCourant = Integer.parseInt(Tools.getCurrentDate().substring(6, 10));


	
	@SuppressWarnings("static-access")
	public String updateRegistreNaissance(){
		String route = "";
		System.out.println(this.decToConsult.getDate_j());
		if(MyUtil.getProfil() != null){
		if(acteService.verifyNumeroJugement(this.decToConsult.getNumero_jugement(), Tools.formatDay(this.decToConsult.getDate_j()).substring(6, 10))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce numéro de jugement est déjà  attribué pour cette année.", null);
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else if((this.decToConsult.getDeclarant().equalsIgnoreCase("Autre")) && ("".equals(this.decToConsult.getDomicile_declarant()) || "".equals(this.decToConsult.getPrenom_declarant()) || "".equals(this.decToConsult.getNom_declarant()) || "".equals(this.decToConsult.getProfession_declarant()) || "".equals(this.decToConsult.getAdresse_declarant()) || "".equals(this.decToConsult.getNum_identification_declarant()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du déclarant.", null);
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else{
    		this.decToConsult.setDate_naissance_enfant(Tools.formatDay(this.decToConsult.getDate_naissanceE()));
            this.decToConsult.setDate_naissance_mere(Tools.formatDay(this.decToConsult.getDate_naissanceM()));
            this.decToConsult.setDate_naissance_pere(Tools.formatDay(this.decToConsult.getDate_naissanceP()));
            this.decToConsult.setDate_jugement(Tools.formatDay(this.decToConsult.getDate_j()));
            
            this.decToConsult.setHeure_naissance_enfant(this.decToConsult.getHeure_naissanceE().toString().substring(11, 16));
            this.decToConsult.setType_declaration("Jugement");
            this.decToConsult.setDate_modification(Tools.getCurrentDateTime());
            this.decToConsult.setModificateurNaissance(MyUtil.getUserLogged());
            
            if (acteService.updateActe(decToConsult)) {
            	
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La modification a été effectuée avec succès.", null);
            	FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            	route = "/views/" + MyUtil.getProfil() + "/consultation/acte/naissance?faces-redirect=true";
            }
            
            else{
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossible de modifier la déclaration. Vérifiez toutes les informations saisies.", null);
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
	
	@SuppressWarnings("static-access")
	public String updateRegistreMariage(){
		String route = "";
		if(MyUtil.getProfil() != null){
			
			if(marService.verifyNumeroJugement(this.decMarToConsult.getNumero_Jugement(), this.decMarToConsult.getDateJugement().toString().substring(6, 10))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce numéro de jugement est déjà  attribué pour cette année.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
                this.decMarToConsult.setDate_Mariage(Tools.formatDay(this.decMarToConsult.getDateMariage()));
                this.decMarToConsult.setDate_Jugement(Tools.formatDay(this.decMarToConsult.getDateJugement()));
                this.decMarToConsult.setDate_Naissance_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissanceEpouse()));
                this.decMarToConsult.setDate_Naissance_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpoux()));
                this.decMarToConsult.setDate_Naissance_Mere_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpouse()));
                this.decMarToConsult.setDate_Naissance_Mere_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissanceMereEpoux()));
                this.decMarToConsult.setDate_Naissance_Pere_Epoux(Tools.formatDay(this.decMarToConsult.getDateNaissancePereEpoux()));
                this.decMarToConsult.setDate_Naissance_Pere_Epouse(Tools.formatDay(this.decMarToConsult.getDateNaissancePereEpouse()));
                this.decMarToConsult.setHeure_Mariage(this.decMarToConsult.getHeureMariage().toString().substring(11, 16));
                this.decMarToConsult.setType_Declaration("Jugement");
                this.decMarToConsult.setDate_modification(Tools.getCurrentDateTime());
                this.decMarToConsult.setModificateurMariage(MyUtil.getUserLogged());
                
                if (marService.updateDeclarationMariage(decMarToConsult)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Les modifications ont été effectué avec succès.", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                    route="/views/" + MyUtil.getProfil() + "/consultation/acte/mariage?faces-redirect=true";

                } 
                else {

                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Les modifications n'ont pas été effecuté normalement.", null);
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
	
	
	@SuppressWarnings("static-access")
	public String updateRegistreDeces(){
		String route = "";
		if(MyUtil.getProfil() != null){
			
			if(deceService.verifyNumeroJugement(this.decDCToConsult.getNum_jugement(), Tools.formatDay(this.decDCToConsult.getDate_j()).substring(6, 10))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ce numéro de jugement est déjé attribué pour cette année.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Autre")) && ("".equals(this.decDCToConsult.getAdresse_declarant()) || "".equals(this.decDCToConsult.getPrenom_declarant()) || "".equals(this.decDCToConsult.getNom_declarant()) || "".equals(this.decDCToConsult.getProfession_declarant()) || "".equals(this.decDCToConsult.getNum_identification_declarant()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du déclarant.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Pére")) && ("".equals(this.decDCToConsult.getPrenom_pere()) || "".equals(this.decDCToConsult.getNom_pere()) || "".equals(this.decDCToConsult.getDomicile_pere()) || "".equals(this.decDCToConsult.getProfession_pere()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations du pére.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else if((this.decDCToConsult.getDegre_parente().equalsIgnoreCase("Mére")) && ("".equals(this.decDCToConsult.getPrenom_mere()) || "".equals(this.decDCToConsult.getNom_mere()) || "".equals(this.decDCToConsult.getDomicile_mere()) || "".equals(this.decDCToConsult.getProfession_mere()))){
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir les informations de la mére.", null);
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        	else{
        		this.decDCToConsult.setDate_deces(Tools.formatDay(this.decDCToConsult.getDate_d()));
                this.decDCToConsult.setDate_naissance_defunt(Tools.formatDay(this.decDCToConsult.getDate_naissanceDefunt()));
                this.decDCToConsult.setDate_jugement(Tools.formatDay(this.decDCToConsult.getDate_j()));
                this.decDCToConsult.setHeure_deces(this.decDCToConsult.getHeure_d().toString().substring(11, 16));
                this.decDCToConsult.setDate_modification(Tools.getCurrentDateTime());
                this.decDCToConsult.setType_declaration("Jugement");
                this.decDCToConsult.setModificateurDeces(MyUtil.getUserLogged());
                
                if (deceService.updateDeclarationDeces(decDCToConsult)) {
                	
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La modification a été effectué avec succés!", null);
                    FacesContext context = FacesContext.getCurrentInstance();
    	            context.getCurrentInstance().addMessage(null, message);
	            	
	            	context.getExternalContext().getFlash().setKeepMessages(true);
                    route="/views/" + MyUtil.getProfil() + "/consultation/acte/deces?faces-redirect=true";

                } else {
                	
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La modification n'a pas été effectué!", null);
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
	
	@SuppressWarnings("static-access")
	public String mAJMMN(){
		String route = "";
		if(MyUtil.getProfil() != null){
			this.decToConsult.setMention_marginale(this.decToConsult.getMention_marginale()+" \n "+this.getMentionN());
			if (acteService.updateActe(this.decToConsult)) {
            	
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour de la mention marginale effectuée avec succés!", null);
            	FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            	route = "/views/" + MyUtil.getProfil() + "/consultation/acte/naissance?faces-redirect=true";
            }
            
            else{
            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mise à jour de la mention marginale non effectuée!", null);
            	FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            }
		}
		else{
			
		}
		return route;
	}
	
	@SuppressWarnings("static-access")
	public String mAJMMM(){
		String route = "";
		if(MyUtil.getProfil() != null){
			this.decMarToConsult.setMentions_Marginales(this.decMarToConsult.getMentions_Marginales()+" \n "+this.getMention());
			
			if (marService.updateDeclarationMariage(decMarToConsult)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour de la mention marginale effectuée avec succés!", null);
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
                route="/views/" + MyUtil.getProfil() + "/consultation/acte/mariage?faces-redirect=true";

            } 
            else {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mise à jour de la mention marginale non effectuée!", null);
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            
            }
		}
		else{
			
		}
		return route;
	}
	
	@SuppressWarnings("static-access")
	public String mAJMMD(){
		String route = "";
		if(MyUtil.getProfil() != null){
			this.decDCToConsult.setMention_marginale(this.decDCToConsult.getMention_marginale()+" \n "+this.getMentionD());
			if (deceService.updateDeclarationDeces(decDCToConsult)) {
            	
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mise à jour de la mention marginale effectuée avec succés!", null);
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
                route="/views/" + MyUtil.getProfil() + "/consultation/acte/deces?faces-redirect=true";

            } else {
            	
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mise à jour de la mention marginale non effectuée!", null);
                FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
            }
		}
		else{
			
		}
		return route;
	}



	/**
	 * @return the mention
	 */
	public String getMention() {
		return mention;
	}



	/**
	 * @param mention the mention to set
	 */
	public void setMention(String mention) {
		this.mention = mention;
	}



	/**
	 * @return the mentionN
	 */
	public String getMentionN() {
		return mentionN;
	}



	/**
	 * @param mentionN the mentionN to set
	 */
	public void setMentionN(String mentionN) {
		this.mentionN = mentionN;
	}



	/**
	 * @return the mentionD
	 */
	public String getMentionD() {
		return mentionD;
	}



	/**
	 * @param mentionD the mentionD to set
	 */
	public void setMentionD(String mentionD) {
		this.mentionD = mentionD;
	}
	
	public String mentionN(){
		if(MyUtil.getProfil() != null)
			return MyUtil.pathMAJMM()+"naissance?faces-redirect=true";
		else
			return MyUtil.pathLogin();
	}
	
	public String mentionM(){
		if(MyUtil.getProfil() != null)
			return MyUtil.pathMAJMM()+"mariage?faces-redirect=true";
		else
			return MyUtil.pathLogin();
	}
	
	public String mentionD(){
		if(MyUtil.getProfil() != null)
			return MyUtil.pathMAJMM()+"deces?faces-redirect=true";
		else
			return MyUtil.pathLogin();
	}
}
