package pieces;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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
import util.MyUtil;
import util.NombreEnLettre;
import util.Tools;

public class Inhumation {
	
	@SuppressWarnings("rawtypes")
	private Map parameter = new HashMap();
	private String nom;
	private String prenom;
	private String sexe;
	private String date_deces;
	private String formationSanitaire;
	private String cimetiere;
	
	private Date dateDeces;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDate_deces() {
		return date_deces;
	}
	public void setDate_deces(String date_deces) {
		this.date_deces = date_deces;
	}
	public String getFormationSanitaire() {
		return formationSanitaire;
	}
	public void setFormationSanitaire(String formationSanitaire) {
		this.formationSanitaire = formationSanitaire;
	}
	
	

	public String getCimetiere() {
		return cimetiere;
	}
	public void setCimetiere(String cimetiere) {
		this.cimetiere = cimetiere;
	}
	public Date getDateDeces() {
		return dateDeces;
	}
	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	@SuppressWarnings("unchecked")
	public void saveI() throws IOException, JRException {
		System.out.println("jhvlkjbkmlvbkjlbkgj");
//		parameter.put("region", MyUtil.getUserLogged().getCentre().getCenterRegion());
//		parameter.put("depart", MyUtil.getUserLogged().getCentre().getCenterDepartement());
//		parameter.put("arrond", MyUtil.getUserLogged().getCentre().getCenterArrondissement());
//		parameter.put("centre", MyUtil.getUserLogged().getCentre().getCenterType());
//		parameter.put("nomCentre", MyUtil.getUserLogged().getCentre().getCenterName());
//		parameter.put("cimetiere", this.getCimetiere());
//		parameter.put("defunt", this.getPrenom()+" "+this.getNom());
//		parameter.put("deces", NombreEnLettre.convert(Tools.getDayForDate(Tools.formatDay(this.getDateDeces())))+" "
//				+Tools.getMoisLettre(Tools.formatDay(this.getDateDeces()))+" "
//				+NombreEnLettre.convert(Tools.getYearForDate(Tools.formatDay(this.getDateDeces()))));
//		parameter.put("annee", "");
//		parameter.put("numActe", "");
//		parameter.put("dateC", Tools.getformatDate(Tools.getCurrentDateDDMMYYYY()));
//		parameter.put("officier", MyUtil.getUserLogged().getUserPrenom()+" "+MyUtil.getUserLogged().getUserNom());
//		
//		System.out.println(this.getPrenom()+" "+this.getNom()+" dgmkfhk");
//		FacesContext context = FacesContext.getCurrentInstance();
//
//		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/pieces/permisinhumation.jrxml");
//
//		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
//		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
//		JasperViewer.viewReport(jasperPrint,false);
	}
	
	public void dddd(){
		System.out.println("kjglhlkhlh");
	}

}
