package pieces;

import java.sql.Date;

import services.DelivredPieceService;
import services.PiecesAnnexesServices;

public class CertificatIndividualite {
	
	private PiecesAnnexesServices pService = new PiecesAnnexesServices();
	private DelivredPieceService dService = new DelivredPieceService();

	private String prenom_T1;
	private String nom_T1;
	private String cni_T1;
	private String adresse_T1;
	
	private String prenom_T2;
	private String nom_T2;
	private String cni_T2;
	private String adresse_T2;
	
	private String domicile_T;
	
	private String prenom;
	private String nom;
	private String date_N;
	private Date dateN;
	private String lieu_N;
	private String sexe;
	private String adresse;
	
	private String prenom_connu;
	private String nom_connu;
	private String profession;
	
	
	public String getPrenom_T1() {
		return prenom_T1;
	}
	public void setPrenom_T1(String prenom_T1) {
		this.prenom_T1 = prenom_T1;
	}
	public String getNom_T1() {
		return nom_T1;
	}
	public void setNom_T1(String nom_T1) {
		this.nom_T1 = nom_T1;
	}
	public String getCni_T1() {
		return cni_T1;
	}
	public void setCni_T1(String cni_T1) {
		this.cni_T1 = cni_T1;
	}
	public String getAdresse_T1() {
		return adresse_T1;
	}
	public void setAdresse_T1(String adresse_T1) {
		this.adresse_T1 = adresse_T1;
	}
	public String getPrenom_T2() {
		return prenom_T2;
	}
	public void setPrenom_T2(String prenom_T2) {
		this.prenom_T2 = prenom_T2;
	}
	public String getNom_T2() {
		return nom_T2;
	}
	public void setNom_T2(String nom_T2) {
		this.nom_T2 = nom_T2;
	}
	public String getCni_T2() {
		return cni_T2;
	}
	public void setCni_T2(String cni_T2) {
		this.cni_T2 = cni_T2;
	}
	public String getAdresse_T2() {
		return adresse_T2;
	}
	public void setAdresse_T2(String adresse_T2) {
		this.adresse_T2 = adresse_T2;
	}
	public String getDomicile_T() {
		return domicile_T;
	}
	public void setDomicile_T(String domicile_T) {
		this.domicile_T = domicile_T;
	}
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
	public String getDate_N() {
		return date_N;
	}
	public void setDate_N(String date_N) {
		this.date_N = date_N;
	}
	public Date getDateN() {
		return dateN;
	}
	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}
	public String getLieu_N() {
		return lieu_N;
	}
	public void setLieu_N(String lieu_N) {
		this.lieu_N = lieu_N;
	}
	public String getPrenom_connu() {
		return prenom_connu;
	}
	public void setPrenom_connu(String prenom_connu) {
		this.prenom_connu = prenom_connu;
	}
	public String getNom_connu() {
		return nom_connu;
	}
	public void setNom_connu(String nom_connu) {
		this.nom_connu = nom_connu;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public void save(){
		
	}
	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}
	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
}
