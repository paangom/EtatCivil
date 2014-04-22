package pieces;

import java.sql.Date;

public class Residence {
	
	private String prenom;
	private String nom;
	private String date_naissance;
	private String lieu_naissance;
	private String prenom_pere;
	private String nom_pere;
	private String prenom_mere;
	private String nom_mere;
	private String sexe;
	private String date_residence;
	private String lieu_residence;
	
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
	public String getPrenom_pere() {
		return prenom_pere;
	}
	public void setPrenom_pere(String prenom_pere) {
		this.prenom_pere = prenom_pere;
	}
	public String getNom_pere() {
		return nom_pere;
	}
	public void setNom_pere(String nom_pere) {
		this.nom_pere = nom_pere;
	}
	public String getPrenom_mere() {
		return prenom_mere;
	}
	public void setPrenom_mere(String prenom_mere) {
		this.prenom_mere = prenom_mere;
	}
	public String getNom_mere() {
		return nom_mere;
	}
	public void setNom_mere(String nom_mere) {
		this.nom_mere = nom_mere;
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
	
	public void save(){
		System.out.println(this.getNom());
	}

}
