/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import models.PiecesAnnexes;

import org.primefaces.model.chart.PieChartModel;

import services.ActeDecesServices;
import services.ActeMariageServices;
import services.ActeNaissanceServices;
import services.CentreServices;
import services.DelivredPieceService;
import services.PiecesAnnexesServices;
import util.Tools;



/**
 *
 * @author sambasow
 */
public class Statistiques {
	
	private PieChartModel pieModel;
	private PieChartModel pieModelInstance;
	
	private ActeNaissanceServices acteService = new ActeNaissanceServices();
    private ActeDecesServices deceService = new ActeDecesServices();
    private ActeMariageServices marService = new ActeMariageServices();
    private DelivredPieceService delService = new DelivredPieceService(); 
    private CentreServices cenServ = new CentreServices();
    private PiecesAnnexesServices pService = new PiecesAnnexesServices();
    
    private int decN;
    private int decM;
    private int decD;
    
    private int decNV;
    private int decMV;
    private int decDV;
    private int pDel;
    
    private int coutume;
    private int nonExistence;
    private int nonInscription;
    private int vieIndiv;
    private int vieCollect;
    private int nonInscripM;
    private int residence;
    private int individualite;
    private int nonDNM;
    private int nonDNRM;
    private int celibat;
    private int nonDNS;
    private int vieIndivFam;
    private int vieCollectFam;
    private int permis;
    
    private String coutumeL;
    private String nonExistenceL;
    private String nonInscriptionL;
    private String vieIndivL;
    private String vieCollectL;
    private String nonInscripML;
    private String residenceL;
    private String individualiteL;
    private String nonDNML;
    private String nonDNRML;
    private String celibatL;
    private String nonDNSL;
    private String vieIndivFamL;
    private String vieCollectFamL;
    private String permisL;
    
    private String libele;
   	
	
	public Statistiques() {
		super();
		decN = acteService.findInstanceByDate(Tools.getCurrentDateDDMMYYYY().substring(3));
		decM = marService.findInstanceByDate(Tools.getCurrentDateDDMMYYYY().substring(3));
		decD = deceService.findInstanceByDate(Tools.getCurrentDateDDMMYYYY().substring(3));
		decNV = acteService.findValidateByDate(Tools.getCurrentDateDDMMYYYY().substring(3));
		decMV = marService.findValidateByDate(Tools.getCurrentDateDDMMYYYY().substring(3));
		decDV = deceService.findValidateByDate(Tools.getCurrentDateDDMMYYYY().substring(3));
		
		nonDNRML = pService.findByCode(300).getLibelle();
		nonDNML = pService.findByCode(290).getLibelle();
		individualiteL = pService.findByCode(270).getLibelle();
		residenceL = pService.findByCode(250).getLibelle();
		nonInscripML = pService.findByCode(310).getLibelle();
		vieCollectL = pService.findByCode(320).getLibelle();
		vieIndivL = pService.findByCode(230).getLibelle();
		nonInscriptionL = pService.findByCode(220).getLibelle();
		nonExistenceL = pService.findByCode(240).getLibelle();
		coutumeL = pService.findByCode(200).getLibelle();
		setCelibatL(pService.findByCode(260).getLibelle());
		setNonDNSL(pService.findByCode(280).getLibelle());
		setVieIndivFamL(pService.findByCode(340).getLibelle());
		setVieCollectFamL(pService.findByCode(330).getLibelle());
		setPermisL(pService.findByCode(210).getLibelle());
		
		nonDNRM = delService.nombreDePieces(pService.findByCode(300), Tools.getCurrentDateDDMMYYYY().substring(3));
		individualite = delService.nombreDePieces(pService.findByCode(270), Tools.getCurrentDateDDMMYYYY().substring(3));
		residence = delService.nombreDePieces(pService.findByCode(250), Tools.getCurrentDateDDMMYYYY().substring(3));
		nonInscripM = delService.nombreDePieces(pService.findByCode(310), Tools.getCurrentDateDDMMYYYY().substring(3));
		vieCollect = delService.nombreDePieces(pService.findByCode(320), Tools.getCurrentDateDDMMYYYY().substring(3));
		vieIndiv = delService.nombreDePieces(pService.findByCode(230), Tools.getCurrentDateDDMMYYYY().substring(3));
		nonInscription = delService.nombreDePieces(pService.findByCode(220), Tools.getCurrentDateDDMMYYYY().substring(3));
		nonExistence = delService.nombreDePieces(pService.findByCode(240), Tools.getCurrentDateDDMMYYYY().substring(3));
		coutume = delService.nombreDePieces(pService.findByCode(200), Tools.getCurrentDateDDMMYYYY().substring(3));
		nonDNM = delService.nombreDePieces(pService.findByCode(290), Tools.getCurrentDateDDMMYYYY().substring(3));
		celibat = delService.nombreDePieces(pService.findByCode(260), Tools.getCurrentDateDDMMYYYY().substring(3));
		nonDNS = delService.nombreDePieces(pService.findByCode(280), Tools.getCurrentDateDDMMYYYY().substring(3));
		vieIndivFam = delService.nombreDePieces(pService.findByCode(340), Tools.getCurrentDateDDMMYYYY().substring(3));
		vieCollectFam = delService.nombreDePieces(pService.findByCode(330), Tools.getCurrentDateDDMMYYYY().substring(3));
		permis = delService.nombreDePieces(pService.findByCode(210), Tools.getCurrentDateDDMMYYYY().substring(3));
		
		// TODO Auto-generated constructor stub
		createPieModel();
	}
	
	 private void createPieModel() {  
	        pieModel = new PieChartModel();  
	        pieModelInstance = new PieChartModel();
	  
	        pieModelInstance.set("Naissances", decNV);  
	        pieModelInstance.set("Mariages", decMV);  
	        pieModelInstance.set("Décès", decDV);
	        
	        pieModel.set("Naissances", decN);
	        pieModel.set("Mariages", decM);
	        pieModel.set("Décès", decD);
	    }  

	/**
	 * @return the categoryModel
	 */
	public PieChartModel getPieModel() {
		return pieModel;
	}

	/**
	 * @return the decN
	 */
	public int getDecN() {
		
		return decN;
	}


	/**
	 * @return the decM
	 */
	public int getDecM() {

		return decM;
	}


	/**
	 * @return the decD
	 */
	public int getDecD() {
		
		return decD;
	}


	/**
	 * @return the pDel
	 */
	public int getpDel(int x) {
		pDel = delService.nombreDePieces(pService.findByCode(x), Tools.getCurrentDateDDMMYYYY().substring(3));
		return pDel;
	}

	/**
	 * @return the pieModelInstance
	 */
	public PieChartModel getPieModelInstance() {
		return pieModelInstance;
	}

	
	/**
	 * @return the decNV
	 */
	public int getDecNV() {
		
		return decNV;
	}

	

	/**
	 * @return the decMV
	 */
	public int getDecMV() {
		
		return decMV;
	}


	/**
	 * @return the decDV
	 */
	public int getDecDV() {
		
		return decDV;
	}

	/**
	 * @return the libele
	 */
	public String getLibele(int code) {
		libele = pService.findByCode(code).getLibelle();
		return libele;
	}

	/**
	 * @param libele the libele to set
	 */
	public void setLibele(String libele) {
		this.libele = libele;
	}

	/**
	 * @return the nonDNM
	 */
	public int getNonDNM() {
		
		return nonDNM;
	}


	/**
	 * @return the coutume
	 */
	public int getCoutume() {
		
		return coutume;
	}

	/**
	 * @return the nonExistence
	 */
	public int getNonExistence() {
		
		return nonExistence;
	}

	/**
	 * @return the nonInscription
	 */
	public int getNonInscription() {
		
		return nonInscription;
	}

	/**
	 * @return the vieIndiv
	 */
	public int getVieIndiv() {
		
		return vieIndiv;
	}

	/**
	 * @return the vieCollect
	 */
	public int getVieCollect() {
		
		return vieCollect;
	}

	/**
	 * @return the nonInscripM
	 */
	public int getNonInscripM() {
		
		return nonInscripM;
	}

	/**
	 * @return the residence
	 */
	public int getResidence() {
		
		return residence;
	}

	/**
	 * @return the individualite
	 */
	public int getIndividualite() {
		
		return individualite;
	}

	/**
	 * @return the nonDNRM
	 */
	public int getNonDNRM() {
		
		return nonDNRM;
	}

	/**
	 * @return the coutumeL
	 */
	public String getCoutumeL() {
		
		return coutumeL;
	}

	/**
	 * @return the nonExistenceL
	 */
	public String getNonExistenceL() {
		
		return nonExistenceL;
	}

	/**
	 * @return the nonInscriptionL
	 */
	public String getNonInscriptionL() {
		
		return nonInscriptionL;
	}

	/**
	 * @return the vieIndivL
	 */
	public String getVieIndivL() {
		
		return vieIndivL;
	}

	/**
	 * @return the vieCollectL
	 */
	public String getVieCollectL() {
		
		return vieCollectL;
	}

	/**
	 * @return the nonInscripML
	 */
	public String getNonInscripML() {
		
		return nonInscripML;
	}

	/**
	 * @return the residenceL
	 */
	public String getResidenceL() {
		
		return residenceL;
	}

	/**
	 * @return the individualiteL
	 */
	public String getIndividualiteL() {
		
		return individualiteL;
	}

	/**
	 * @return the nonDNML
	 */
	public String getNonDNML() {
		
		return nonDNML;
	}

	/**
	 * @return the nonDNRML
	 */
	public String getNonDNRML() {
		
		return nonDNRML;
	}

	/**
	 * @return the celibat
	 */
	public int getCelibat() {
		return celibat;
	}

	/**
	 * @param celibat the celibat to set
	 */
	public void setCelibat(int celibat) {
		this.celibat = celibat;
	}

	/**
	 * @return the nonDNS
	 */
	public int getNonDNS() {
		return nonDNS;
	}

	/**
	 * @param nonDNS the nonDNS to set
	 */
	public void setNonDNS(int nonDNS) {
		this.nonDNS = nonDNS;
	}

	/**
	 * @return the vieIndivFam
	 */
	public int getVieIndivFam() {
		return vieIndivFam;
	}

	/**
	 * @param vieIndivFam the vieIndivFam to set
	 */
	public void setVieIndivFam(int vieIndivFam) {
		this.vieIndivFam = vieIndivFam;
	}

	/**
	 * @return the vieCollectFam
	 */
	public int getVieCollectFam() {
		return vieCollectFam;
	}

	/**
	 * @param vieCollectFam the vieCollectFam to set
	 */
	public void setVieCollectFam(int vieCollectFam) {
		this.vieCollectFam = vieCollectFam;
	}

	/**
	 * @return the permis
	 */
	public int getPermis() {
		return permis;
	}

	/**
	 * @param permis the permis to set
	 */
	public void setPermis(int permis) {
		this.permis = permis;
	}

	/**
	 * @return the celibatL
	 */
	public String getCelibatL() {
		return celibatL;
	}

	/**
	 * @param celibatL the celibatL to set
	 */
	public void setCelibatL(String celibatL) {
		this.celibatL = celibatL;
	}

	/**
	 * @return the nonDNSL
	 */
	public String getNonDNSL() {
		return nonDNSL;
	}

	/**
	 * @param nonDNSL the nonDNSL to set
	 */
	public void setNonDNSL(String nonDNSL) {
		this.nonDNSL = nonDNSL;
	}

	/**
	 * @return the vieIndivFamL
	 */
	public String getVieIndivFamL() {
		return vieIndivFamL;
	}

	/**
	 * @param vieIndivFamL the vieIndivFamL to set
	 */
	public void setVieIndivFamL(String vieIndivFamL) {
		this.vieIndivFamL = vieIndivFamL;
	}

	/**
	 * @return the vieCollectFamL
	 */
	public String getVieCollectFamL() {
		return vieCollectFamL;
	}

	/**
	 * @param vieCollectFamL the vieCollectFamL to set
	 */
	public void setVieCollectFamL(String vieCollectFamL) {
		this.vieCollectFamL = vieCollectFamL;
	}

	/**
	 * @return the permisL
	 */
	public String getPermisL() {
		return permisL;
	}

	/**
	 * @param permisL the permisL to set
	 */
	public void setPermisL(String permisL) {
		this.permisL = permisL;
	}
	
	
	
}
