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

public class AttestationNonExistance {
	
	@SuppressWarnings("rawtypes")
	private Map parameter = new HashMap();
	
	
	private String prenom;
	private String nom;
	private String numero;
	private String annee;
	private String lieu_N;
	private String prenom_P;
	private String nom_P;
	private String prenom_M;
	private String nom_M;
	private String officier;
	private String centre;
	
	private Date dateN;
	
	
	
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
	
	public String getLieu_N() {
		return lieu_N;
	}
	public void setLieu_N(String lieu_N) {
		this.lieu_N = lieu_N;
	}
	
	public String getPrenom_P() {
		return prenom_P;
	}
	public void setPrenom_P(String prenom_P) {
		this.prenom_P = prenom_P;
	}
	public String getNom_P() {
		return nom_P;
	}
	public void setNom_P(String nom_P) {
		this.nom_P = nom_P;
	}
	
	public String getPrenom_M() {
		return prenom_M;
	}
	public void setPrenom_M(String prenom_M) {
		this.prenom_M = prenom_M;
	}
	public String getNom_M() {
		return nom_M;
	}
	public void setNom_M(String nom_M) {
		this.nom_M = nom_M;
	}
	
	public String getOfficier() {
		return officier;
	}
	public void setOfficier(String officier) {
		this.officier = officier;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}

	public Date getDateN() {
		return dateN;
	}
	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}
	
	
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}
	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	@SuppressWarnings("unchecked")
	public void save() throws IOException, JRException {
		parameter.put("region", MyUtil.getUserLogged().getCentre().getCenterRegion());
		parameter.put("depart", MyUtil.getUserLogged().getCentre().getCenterDepartement());
		parameter.put("arrond", MyUtil.getUserLogged().getCentre().getCenterArrondissement());
		parameter.put("centre", MyUtil.getUserLogged().getCentre().getCenterType());
		parameter.put("nomcentre", MyUtil.getUserLogged().getCentre().getCenterName());
		parameter.put("numero", this.getNumero());
		parameter.put("pere", this.getPrenom_P()+" "+this.getNom_P());
		parameter.put("personne", this.getPrenom()+" "+this.getNom());
		parameter.put("dateN", Tools.getformatDate(Tools.formatDay(this.getDateN())));
		parameter.put("lieuN", this.getLieu_N());
		parameter.put("mere", this.getPrenom_M()+" "+this.getNom_M());
		parameter.put("annee", this.getAnnee());
		parameter.put("dateC", Tools.getCurrentDateDDMMYYYY());
		parameter.put("officier", MyUtil.getUserLogged().getUserPrenom()+" "+MyUtil.getUserLogged().getUserNom());
		
		FacesContext context = FacesContext.getCurrentInstance();

		String reportSource = context.getExternalContext().getRealPath("ActeNPDF/pieces/certificatNonExistance.jrxml");

		JasperDesign jasperDesign=JRXmlLoader.load(reportSource);
		JasperReport jasperReport =JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
		JasperViewer.viewReport(jasperPrint,false);
	}


}
