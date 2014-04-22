package pieces;

import java.sql.Date;

public class AttestationDeCoutume {

	private String officier;
	private String centre;
	
	private String nom;
	private String prenom;
	private String date_N;
	private String lieu_N;
	private String sexe;
	private String prenom_P;
	private String nom_P;
	private String prenom_M;
	private String nom_M;
	private String coutume;
	private String date_C;
	
	private Date dateN;
	private Date dateC;
	
	
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
	public String getDate_N() {
		return date_N;
	}
	public void setDate_N(String date_N) {
		this.date_N = date_N;
	}
	public String getLieu_N() {
		return lieu_N;
	}
	public void setLieu_N(String lieu_N) {
		this.lieu_N = lieu_N;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
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
	public String getCoutume() {
		return coutume;
	}
	public void setCoutume(String coutume) {
		this.coutume = coutume;
	}
	public String getDate_C() {
		return date_C;
	}
	public void setDate_C(String date_C) {
		this.date_C = date_C;
	}
	public Date getDateN() {
		return dateN;
	}
	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}
	public Date getDateC() {
		return dateC;
	}
	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}
	
	public void save(){
		
	}
}
