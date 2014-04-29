package pieces;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.DelivredPieceService;
import services.PiecesAnnexesServices;
import util.MyUtil;
import util.Tools;

public class VieIndividuel {
	
	private PiecesAnnexesServices pService = new PiecesAnnexesServices();
	private DelivredPieceService dService = new DelivredPieceService();
	
	@SuppressWarnings("rawtypes")
	private Map parameter = new HashMap();
	
	private String prenom;
	private String nom;
	private String lieu_naissance;
	private String sexe;
	private String domicile;
	
	private Date dateNaissance;
	
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getLieu_naissance() {
		return lieu_naissance;
	}
	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	@SuppressWarnings("unchecked")
	public void save()  {
		parameter.put("region", MyUtil.getUserLogged().getCentre().getCenterRegion());
		parameter.put("depart", MyUtil.getUserLogged().getCentre().getCenterDepartement());
		parameter.put("arrond", MyUtil.getUserLogged().getCentre().getCenterArrondissement());
		parameter.put("typecentre", MyUtil.getUserLogged().getCentre().getCenterType());
		parameter.put("nomcentre", MyUtil.getUserLogged().getCentre().getCenterName());
		parameter.put("domicile", this.getDomicile());
		parameter.put("personne", this.getPrenom()+" "+this.getNom());
		parameter.put("dateN", Tools.getformatDate(Tools.formatDay(this.getDateNaissance())));
		parameter.put("lieuN", this.getLieu_naissance());
		parameter.put("acte", "");
		parameter.put("annee", "");
		parameter.put("dateC", Tools.getCurrentDateDDMMYYYY());
		parameter.put("officier", MyUtil.getUserLogged().getUserPrenom()+" "+MyUtil.getUserLogged().getUserNom());
		
		FacesContext context = FacesContext.getCurrentInstance();

		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/pieces/certificatVieIndividuel.jrxml");

		JasperDesign jasperDesign;
		try {
			jasperDesign = JRXmlLoader.load(reportSource);
			JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
			if(dService.addPiece(pService.findByCode(230))){
				JasperViewer.viewReport(jasperPrint,false);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Chargement effectué avec succès!", null);
		        FacesContext.getCurrentInstance().addMessage(null, message);
			}
			else{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'ajout du document délivré!", null);
		        FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (JRException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de chargement!", null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		
		
		
	}


}
