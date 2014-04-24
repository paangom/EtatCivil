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
import util.Tools;

public class Residence {
	
	@SuppressWarnings("rawtypes")
	private Map parameter = new HashMap();
	
	private String prenom;
	private String nom;
	private String date_naissance;
	private String lieu_naissance;
	private String sexe;
	private String date_residence;
	private String lieu_residence;
	private String motif;
	private String quartier;
	
	private Date dateNaissance;
	private Date dateResidence;
	
	
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
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
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
	public String getDate_residence() {
		return date_residence;
	}
	public void setDate_residence(String date_residence) {
		this.date_residence = date_residence;
	}
	public String getLieu_residence() {
		return lieu_residence;
	}
	public void setLieu_residence(String lieu_residence) {
		this.lieu_residence = lieu_residence;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Date getDateResidence() {
		return dateResidence;
	}
	public void setDateResidence(Date dateResidence) {
		this.dateResidence = dateResidence;
	}
	
	/**
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}
	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}
	/**
	 * @return the quartier
	 */
	public String getQuartier() {
		return quartier;
	}
	/**
	 * @param quartier the quartier to set
	 */
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	@SuppressWarnings("unchecked")
	public void save() throws IOException, JRException {
		parameter.put("region", MyUtil.getUserLogged().getCentre().getCenterRegion());
		parameter.put("depart", MyUtil.getUserLogged().getCentre().getCenterDepartement());
		parameter.put("arrond", MyUtil.getUserLogged().getCentre().getCenterArrondissement());
		parameter.put("centre", MyUtil.getUserLogged().getCentre().getCenterType());
		parameter.put("nomcentre", MyUtil.getUserLogged().getCentre().getCenterName());
		parameter.put("commune", MyUtil.getUserLogged().getCentre().getCenterCollectivite());
		parameter.put("motif", this.getMotif());
		parameter.put("personne", this.getPrenom()+" "+this.getNom());
		parameter.put("dateN", Tools.getformatDate(Tools.formatDay(this.getDateNaissance())));
		parameter.put("lieuN", this.getLieu_naissance());
		parameter.put("residence", this.getLieu_residence());
		parameter.put("quartier", this.getQuartier());
		parameter.put("dateR",  Tools.getformatDate(Tools.formatDay(this.getDateResidence())));
		parameter.put("dateC", Tools.getCurrentDateDDMMYYYY());
		parameter.put("officier", MyUtil.getUserLogged().getUserPrenom()+" "+MyUtil.getUserLogged().getUserNom());
		
		FacesContext context = FacesContext.getCurrentInstance();

		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/pieces/certificatResidence.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
	}

}
