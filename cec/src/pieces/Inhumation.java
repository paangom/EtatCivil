package pieces;

import java.sql.Date;

public class Inhumation {
	
	private String nom;
	private String prenom;
	private String sexe;
	private String date_deces;
	private String formationSanitaire;
	private String prenom_gardien;
	private String nom_gardien;
	
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
	
	public void save(){
		
	}
	public String getPrenom_gardien() {
		return prenom_gardien;
	}
	public void setPrenom_gardien(String prenom_gardien) {
		this.prenom_gardien = prenom_gardien;
	}
	public String getNom_gardien() {
		return nom_gardien;
	}
	public void setNom_gardien(String nom_gardien) {
		this.nom_gardien = nom_gardien;
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

}
