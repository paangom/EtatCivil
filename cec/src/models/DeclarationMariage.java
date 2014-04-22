package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author Papa Ngom
 *
 */
@Entity
@Table(name="declaration_mariage")
public class DeclarationMariage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="date_Mariage", columnDefinition="varchar(40) default ''", nullable=false)
	private String date_Mariage;
	
	@Column(name="heure_Mariage", columnDefinition="varchar(40) default ''", nullable=false)
	private String heure_Mariage;
	
	@Column(name="lieu_Mariage", columnDefinition="varchar(80) default ''", nullable=false)
	private String lieu_Mariage;
	
	@Column(name="genre_Mariage", columnDefinition="varchar(30) default ''", nullable=false)
	private String genre_Mariage;
	
	@Column(name="dot_Mariage", columnDefinition="varchar(30) default ''", nullable=false)
	private String dot_Mariage;
	
	@Column(name="regime_Mariage", columnDefinition="varchar(60) default ''", nullable=false)
	private String regime_Mariage;
	
	@Column(name="type_Mariage", columnDefinition="varchar(30) default ''", nullable=false)
	private String type_Mariage;
	
	@Column(name="prenom_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String prenom_Epoux;
	
	@Column(name="nom_Epoux", columnDefinition="varchar(40) default ''", nullable=false)
	private String nom_Epoux;
	
	@Column(name="date_Naissance_Epoux", columnDefinition="varchar(40) default ''", nullable=false)
	private String date_Naissance_Epoux;
	
	@Column(name="lieu_Naissance_Epoux", columnDefinition="varchar(100) default ''", nullable=false)
	private String lieu_Naissance_Epoux;
	
	@Column(name="profession_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String profession_Epoux;
	
	@Column(name="domicile_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String domicile_Epoux;
	
	@Column(name="residence_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String residence_Epoux;
	
	@Column(name="precedent_Conjoint", columnDefinition="varchar(120) default ''", nullable=true)
	private String precedent_Conjoint;
	
	
	@Column(name="prenom_Mere_Epoux", columnDefinition="varchar(80) default ''", nullable=true)
	private String prenom_Mere_Epoux;
	
	@Column(name="nom_Mere_Epoux", columnDefinition="varchar(40) default ''", nullable=true)
	private String nom_Mere_Epoux;
	
	@Column(name="date_Naissance_Mere_Epoux", columnDefinition="varchar(40) default ''", nullable=true)
	private String date_Naissance_Mere_Epoux;
	
	@Column(name="lieu_Naissance_Mere_Epoux", columnDefinition="varchar(80) default ''", nullable=true)
	private String lieu_Naissance_Mere_Epoux;
	
	@Column(name="profession_Mere_Epoux", columnDefinition="varchar(40) default ''", nullable=true)
	private String profession_Mere_Epoux;
	
	@Column(name="domicile_Mere_Epoux", columnDefinition="varchar(80) default ''", nullable=true)
	private String domicile_Mere_Epoux;
	
	@Column(name="prenom_Pere_Epoux", columnDefinition="varchar(80) default ''", nullable=true)
	private String prenom_Pere_Epoux;
	
	@Column(name="nom_Pere_Epoux", columnDefinition="varchar(40) default ''", nullable=true)
	private String nom_Pere_Epoux;
	
	@Column(name="date_Naissance_Pere_Epoux", columnDefinition="varchar(40) default ''", nullable=true)
	private String date_Naissance_Pere_Epoux;
	
	@Column(name="lieu_Naissance_Pere_Epoux", columnDefinition="varchar(80) default ''", nullable=true)
	private String lieu_Naissance_Pere_Epoux;
	
	@Column(name="profession_Pere_Epoux", columnDefinition="varchar(40) default ''", nullable=true)
	private String profession_Pere_Epoux;
	
	@Column(name="domicile_Pere_Epoux", columnDefinition="varchar(80) default ''", nullable=true)
	private String domicile_Pere_Epoux;
	
	@Column(name="prenom_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String prenom_Epouse;
	
	@Column(name="nom_Epouse", columnDefinition="varchar(40) default ''", nullable=false)
	private String nom_Epouse;
	
	@Column(name="date_Naissance_Epouse", columnDefinition="varchar(40) default ''", nullable=false)
	private String date_Naissance_Epouse;
	
	@Column(name="lieu_Naissance_Epouse", columnDefinition="varchar(100) default ''", nullable=false)
	private String lieu_Naissance_Epouse;
	
	@Column(name="profession_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String profession_Epouse;
	
	@Column(name="domicile_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String domicile_Epouse;
	
	@Column(name="residence_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String residence_Epouse;
	
	@Column(name="prenom_Mere_Epouse", columnDefinition="varchar(80) default ''", nullable=true)
	private String prenom_Mere_Epouse;
	
	@Column(name="nom_Mere_Epouse", columnDefinition="varchar(40) default ''", nullable=true)
	private String nom_Mere_Epouse;
	
	@Column(name="date_Naissance_Mere_Epouse", columnDefinition="varchar(40) default ''", nullable=true)
	private String date_Naissance_Mere_Epouse;
	
	@Column(name="lieu_Naissance_Mere_Epouse", columnDefinition="varchar(80) default ''", nullable=true)
	private String lieu_Naissance_Mere_Epouse;
	
	@Column(name="profession_Mere_Epouse", columnDefinition="varchar(40) default ''", nullable=true)
	private String profession_Mere_Epouse;
	
	@Column(name="domicile_Mere_Epouse", columnDefinition="varchar(80) default ''", nullable=true)
	private String domicile_Mere_Epouse;
	
	@Column(name="prenom_Pere_Epouse", columnDefinition="varchar(80) default ''", nullable=true)
	private String prenom_Pere_Epouse;
	
	@Column(name="nom_Pere_Epouse", columnDefinition="varchar(40) default ''", nullable=true)
	private String nom_Pere_Epouse;
	
	@Column(name="date_Naissance_Pere_Epouse", columnDefinition="varchar(40) default ''", nullable=true)
	private String date_Naissance_Pere_Epouse;
	
	@Column(name="lieu_Naissance_Pere_Epouse", columnDefinition="varchar(80) default ''", nullable=true)
	private String lieu_Naissance_Pere_Epouse;
	
	@Column(name="profession_Pere_Epouse", columnDefinition="varchar(40) default ''", nullable=true)
	private String profession_Pere_Epouse;
	
	@Column(name="domicile_Pere_Epouse", columnDefinition="varchar(80) default ''", nullable=true)
	private String domicile_Pere_Epouse;
	
	@Column(name="prenom_Temoin1_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String prenom_Temoin1_Epoux;
	
	@Column(name="nom_Temoin1_Epoux", columnDefinition="varchar(40) default ''", nullable=false)
	private String nom_Temoin1_Epoux;
	
	@Column(name="profession_Temoin1_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String profession_Temoin1_Epoux;
	
	@Column(name="domicile_Temoin1_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String domicile_Temoin1_Epoux;
	
	@Column(name="cni_Temoin1_Epoux", columnDefinition="varchar(20) default ''", nullable=false)
	private String cni_Temoin1_Epoux;
	
	@Column(name="prenom_Temoin2_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String prenom_Temoin2_Epoux;
	
	@Column(name="nom_Temoin2_Epoux", columnDefinition="varchar(40) default ''", nullable=false)
	private String nom_Temoin2_Epoux;
	
	@Column(name="profession_Temoin2_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String profession_Temoin2_Epoux;
	
	@Column(name="domicile_Temoin2_Epoux", columnDefinition="varchar(80) default ''", nullable=false)
	private String domicile_Temoin2_Epoux;
	
	@Column(name="cni_Temoin2_Epoux", columnDefinition="varchar(20) default ''", nullable=false)
	private String cni_Temoin2_Epoux;
	
	@Column(name="prenom_Temoin1_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String prenom_Temoin1_Epouse;
	
	@Column(name="nom_Temoin1_Epouse", columnDefinition="varchar(40) default ''", nullable=false)
	private String nom_Temoin1_Epouse;
	
	@Column(name="profession_Temoin1_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String profession_Temoin1_Epouse;
	
	@Column(name="domicile_Temoin1_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String domicile_Temoin1_Epouse;
	
	@Column(name="cni_Temoin1_Epouse", columnDefinition="varchar(20) default ''", nullable=false)
	private String cni_Temoin1_Epouse;
	
	@Column(name="prenom_Temoin2_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String prenom_Temoin2_Epouse;
	
	@Column(name="nom_Temoin2_Epouse", columnDefinition="varchar(40) default ''", nullable=false)
	private String nom_Temoin2_Epouse;
	
	@Column(name="profession_Temoin2_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String profession_Temoin2_Epouse;
	
	@Column(name="domicile_Temoin2_Epouse", columnDefinition="varchar(80) default ''", nullable=false)
	private String domicile_Temoin2_Epouse;
	
	@Column(name="cni_Temoin2_Epouse", columnDefinition="varchar(20) default ''", nullable=false)
	private String cni_Temoin2_Epouse;
	
	@Column(name="date_Jugement", columnDefinition="varchar(40) default ''", nullable=true)
	private String date_Jugement;
	
	@Column(name="numero_Jugement", columnDefinition="varchar(20) default ''", nullable=true)
	private String numero_Jugement;
	
	@Column(name="tribunal", columnDefinition="varchar(80) default ''", nullable=true)
	private String tribunal;
	
	@Column(name="mentions_Marginales", columnDefinition="LONGTEXT", nullable=true)
	private String mentions_Marginales;
	
	@Column(name="etat", columnDefinition="varchar(20) default ''", nullable=false)
	private String etat;
	
	@Column(name="numero_Acte", columnDefinition="varchar(11) default ''", nullable=true)
	private String numero_Acte;
	
	@Column(name="date_creation", columnDefinition="varchar(40) default ''", nullable=false)
	private String date_creation;
	
	@Column(name = "date_modification", columnDefinition="varchar(40) default ''", nullable=true)
	private String date_modification;
	
	@Column(name = "date_validation", columnDefinition="varchar(40) default ''", nullable=true)
	private String date_validation;
	
	@Column(name="type_Declaration", columnDefinition="varchar(40) default ''", nullable=false)
	private String type_Declaration;

	@ManyToOne
	(
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinColumn(name="createurMariage")
	private Users createurMariage;
	
	@ManyToOne
	(
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinColumn(name="modificateurMariage")
	private Users modificateurMariage;
	
	@ManyToOne
	(
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinColumn(name="validateurMariage")
	private Users validateurMariage;
	
	private Date dateMariage, dateNaissanceEpoux, dateNaissanceEpouse, heureMariage,dateNaissancePereEpoux, dateNaissanceMereEpoux, dateNaissancePereEpouse,dateNaissanceMereEpouse, dateJugement;
	
	
	public DeclarationMariage () {

	}


	public DeclarationMariage(Integer id, String date_Mariage,
			String heure_Mariage, String lieu_Mariage, String genre_Mariage,
			String dot_Mariage, String regime_Mariage, String type_Mariage,
			String prenom_Epoux, String nom_Epoux, String date_Naissance_Epoux,
			String lieu_Naissance_Epoux, String profession_Epoux,
			String domicile_Epoux, String residence_Epoux,
			String precedent_Conjoint,
			String prenom_Mere_Epoux, String nom_Mere_Epoux,
			String date_Naissance_Mere_Epoux, String lieu_Naissance_Mere_Epoux,
			String profession_Mere_Epoux, String domicile_Mere_Epoux,
			String prenom_Pere_Epoux, String nom_Pere_Epoux,
			String date_Naissance_Pere_Epoux, String lieu_Naissance_Pere_Epoux,
			String profession_Pere_Epoux, String domicile_Pere_Epoux,
			String prenom_Epouse, String nom_Epouse,
			String date_Naissance_Epouse, String lieu_Naissance_Epouse,
			String profession_Epouse, String domicile_Epouse,
			String residence_Epouse, String prenom_Mere_Epouse,
			String nom_Mere_Epouse, String date_Naissance_Mere_Epouse,
			String lieu_Naissance_Mere_Epouse, String profession_Mere_Epouse,
			String domicile_Mere_Epouse, String prenom_Pere_Epouse,
			String nom_Pere_Epouse, String date_Naissance_Pere_Epouse,
			String lieu_Naissance_Pere_Epouse, String profession_Pere_Epouse,
			String domicile_Pere_Epouse, String prenom_Temoin1_Epoux,
			String nom_Temoin1_Epoux, String profession_Temoin1_Epoux,
			String domicile_Temoin1_Epoux, String cni_Temoin1_Epoux,
			String prenom_Temoin2_Epoux, String nom_Temoin2_Epoux,
			String profession_Temoin2_Epoux, String domicile_Temoin2_Epoux,
			String cni_Temoin2_Epoux, String prenom_Temoin1_Epouse,
			String nom_Temoin1_Epouse, String profession_Temoin1_Epouse,
			String domicile_Temoin1_Epouse, String cni_Temoin1_Epouse,
			String prenom_Temoin2_Epouse, String nom_Temoin2_Epouse,
			String profession_Temoin2_Epouse, String domicile_Temoin2_Epouse,
			String cni_Temoin2_Epouse, String date_Jugement,
			String numero_Jugement, String tribunal,
			String mentions_Marginales, String etat, String numero_Acte,
			String date_creation, String date_modification,
			String date_validation, String type_Declaration, Users id_Createur,
			Users id_Modificateur, Users id_Validateur, Date heureMariage) {
		super();
		this.id = id;
		this.date_Mariage = date_Mariage;
		this.heure_Mariage = heure_Mariage;
		this.lieu_Mariage = lieu_Mariage;
		this.genre_Mariage = genre_Mariage;
		this.dot_Mariage = dot_Mariage;
		this.regime_Mariage = regime_Mariage;
		this.type_Mariage = type_Mariage;
		this.prenom_Epoux = prenom_Epoux;
		this.nom_Epoux = nom_Epoux;
		this.date_Naissance_Epoux = date_Naissance_Epoux;
		this.lieu_Naissance_Epoux = lieu_Naissance_Epoux;
		this.profession_Epoux = profession_Epoux;
		this.domicile_Epoux = domicile_Epoux;
		this.residence_Epoux = residence_Epoux;
		this.precedent_Conjoint = precedent_Conjoint;
		this.prenom_Mere_Epoux = prenom_Mere_Epoux;
		this.nom_Mere_Epoux = nom_Mere_Epoux;
		this.date_Naissance_Mere_Epoux = date_Naissance_Mere_Epoux;
		this.lieu_Naissance_Mere_Epoux = lieu_Naissance_Mere_Epoux;
		this.profession_Mere_Epoux = profession_Mere_Epoux;
		this.domicile_Mere_Epoux = domicile_Mere_Epoux;
		this.prenom_Pere_Epoux = prenom_Pere_Epoux;
		this.nom_Pere_Epoux = nom_Pere_Epoux;
		this.date_Naissance_Pere_Epoux = date_Naissance_Pere_Epoux;
		this.lieu_Naissance_Pere_Epoux = lieu_Naissance_Pere_Epoux;
		this.profession_Pere_Epoux = profession_Pere_Epoux;
		this.domicile_Pere_Epoux = domicile_Pere_Epoux;
		this.prenom_Epouse = prenom_Epouse;
		this.nom_Epouse = nom_Epouse;
		this.date_Naissance_Epouse = date_Naissance_Epouse;
		this.lieu_Naissance_Epouse = lieu_Naissance_Epouse;
		this.profession_Epouse = profession_Epouse;
		this.domicile_Epouse = domicile_Epouse;
		this.residence_Epouse = residence_Epouse;
		this.prenom_Mere_Epouse = prenom_Mere_Epouse;
		this.nom_Mere_Epouse = nom_Mere_Epouse;
		this.date_Naissance_Mere_Epouse = date_Naissance_Mere_Epouse;
		this.lieu_Naissance_Mere_Epouse = lieu_Naissance_Mere_Epouse;
		this.profession_Mere_Epouse = profession_Mere_Epouse;
		this.domicile_Mere_Epouse = domicile_Mere_Epouse;
		this.prenom_Pere_Epouse = prenom_Pere_Epouse;
		this.nom_Pere_Epouse = nom_Pere_Epouse;
		this.date_Naissance_Pere_Epouse = date_Naissance_Pere_Epouse;
		this.lieu_Naissance_Pere_Epouse = lieu_Naissance_Pere_Epouse;
		this.profession_Pere_Epouse = profession_Pere_Epouse;
		this.domicile_Pere_Epouse = domicile_Pere_Epouse;
		this.prenom_Temoin1_Epoux = prenom_Temoin1_Epoux;
		this.nom_Temoin1_Epoux = nom_Temoin1_Epoux;
		this.profession_Temoin1_Epoux = profession_Temoin1_Epoux;
		this.domicile_Temoin1_Epoux = domicile_Temoin1_Epoux;
		this.cni_Temoin1_Epoux = cni_Temoin1_Epoux;
		this.prenom_Temoin2_Epoux = prenom_Temoin2_Epoux;
		this.nom_Temoin2_Epoux = nom_Temoin2_Epoux;
		this.profession_Temoin2_Epoux = profession_Temoin2_Epoux;
		this.domicile_Temoin2_Epoux = domicile_Temoin2_Epoux;
		this.cni_Temoin2_Epoux = cni_Temoin2_Epoux;
		this.prenom_Temoin1_Epouse = prenom_Temoin1_Epouse;
		this.nom_Temoin1_Epouse = nom_Temoin1_Epouse;
		this.profession_Temoin1_Epouse = profession_Temoin1_Epouse;
		this.domicile_Temoin1_Epouse = domicile_Temoin1_Epouse;
		this.cni_Temoin1_Epouse = cni_Temoin1_Epouse;
		this.prenom_Temoin2_Epouse = prenom_Temoin2_Epouse;
		this.nom_Temoin2_Epouse = nom_Temoin2_Epouse;
		this.profession_Temoin2_Epouse = profession_Temoin2_Epouse;
		this.domicile_Temoin2_Epouse = domicile_Temoin2_Epouse;
		this.cni_Temoin2_Epouse = cni_Temoin2_Epouse;
		this.date_Jugement = date_Jugement;
		this.numero_Jugement = numero_Jugement;
		this.tribunal = tribunal;
		this.mentions_Marginales = mentions_Marginales;
		this.etat = etat;
		this.numero_Acte = numero_Acte;
		this.date_creation = date_creation;
		this.date_modification = date_modification;
		this.date_validation = date_validation;
		this.type_Declaration = type_Declaration;
		this.createurMariage = id_Createur;
		this.modificateurMariage = id_Modificateur;
		this.validateurMariage = id_Validateur;
		this.heureMariage = heureMariage;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDate_Mariage() {
		return date_Mariage;
	}


	public void setDate_Mariage(String date_Mariage) {
		this.date_Mariage = date_Mariage;
	}


	public String getHeure_Mariage() {
		return heure_Mariage;
	}


	public void setHeure_Mariage(String heure_Mariage) {
		this.heure_Mariage = heure_Mariage;
	}


	public String getLieu_Mariage() {
		return lieu_Mariage;
	}


	public void setLieu_Mariage(String lieu_Mariage) {
		this.lieu_Mariage = lieu_Mariage;
	}


	public String getGenre_Mariage() {
		return genre_Mariage;
	}


	public void setGenre_Mariage(String genre_Mariage) {
		this.genre_Mariage = genre_Mariage;
	}


	public String getDot_Mariage() {
		return dot_Mariage;
	}


	public void setDot_Mariage(String dot_Mariage) {
		this.dot_Mariage = dot_Mariage;
	}


	public String getRegime_Mariage() {
		return regime_Mariage;
	}


	public void setRegime_Mariage(String regime_Mariage) {
		this.regime_Mariage = regime_Mariage;
	}


	public String getType_Mariage() {
		return type_Mariage;
	}


	public void setType_Mariage(String type_Mariage) {
		this.type_Mariage = type_Mariage;
	}


	public String getPrenom_Epoux() {
		return prenom_Epoux;
	}


	public void setPrenom_Epoux(String prenom_Epoux) {
		this.prenom_Epoux = prenom_Epoux;
	}


	public String getNom_Epoux() {
		return nom_Epoux;
	}


	public void setNom_Epoux(String nom_Epoux) {
		this.nom_Epoux = nom_Epoux;
	}


	public String getDate_Naissance_Epoux() {
		return date_Naissance_Epoux;
	}


	public void setDate_Naissance_Epoux(String date_Naissance_Epoux) {
		this.date_Naissance_Epoux = date_Naissance_Epoux;
	}


	public String getLieu_Naissance_Epoux() {
		return lieu_Naissance_Epoux;
	}


	public void setLieu_Naissance_Epoux(String lieu_Naissance_Epoux) {
		this.lieu_Naissance_Epoux = lieu_Naissance_Epoux;
	}


	public String getProfession_Epoux() {
		return profession_Epoux;
	}


	public void setProfession_Epoux(String profession_Epoux) {
		this.profession_Epoux = profession_Epoux;
	}


	public String getDomicile_Epoux() {
		return domicile_Epoux;
	}


	public void setDomicile_Epoux(String domicile_Epoux) {
		this.domicile_Epoux = domicile_Epoux;
	}


	public String getResidence_Epoux() {
		return residence_Epoux;
	}


	public void setResidence_Epoux(String residence_Epoux) {
		this.residence_Epoux = residence_Epoux;
	}


	public String getPrecedent_Conjoint() {
		return precedent_Conjoint;
	}


	public void setPrecedent_Conjoint(String precedent_Conjoint) {
		this.precedent_Conjoint = precedent_Conjoint;
	}


	public String getPrenom_Mere_Epoux() {
		return prenom_Mere_Epoux;
	}


	public void setPrenom_Mere_Epoux(String prenom_Mere_Epoux) {
		this.prenom_Mere_Epoux = prenom_Mere_Epoux;
	}


	public String getNom_Mere_Epoux() {
		return nom_Mere_Epoux;
	}


	public void setNom_Mere_Epoux(String nom_Mere_Epoux) {
		this.nom_Mere_Epoux = nom_Mere_Epoux;
	}


	public String getDate_Naissance_Mere_Epoux() {
		return date_Naissance_Mere_Epoux;
	}


	public void setDate_Naissance_Mere_Epoux(String date_Naissance_Mere_Epoux) {
		this.date_Naissance_Mere_Epoux = date_Naissance_Mere_Epoux;
	}


	public String getLieu_Naissance_Mere_Epoux() {
		return lieu_Naissance_Mere_Epoux;
	}


	public void setLieu_Naissance_Mere_Epoux(String lieu_Naissance_Mere_Epoux) {
		this.lieu_Naissance_Mere_Epoux = lieu_Naissance_Mere_Epoux;
	}


	public String getProfession_Mere_Epoux() {
		return profession_Mere_Epoux;
	}


	public void setProfession_Mere_Epoux(String profession_Mere_Epoux) {
		this.profession_Mere_Epoux = profession_Mere_Epoux;
	}


	public String getDomicile_Mere_Epoux() {
		return domicile_Mere_Epoux;
	}


	public void setDomicile_Mere_Epoux(String domicile_Mere_Epoux) {
		this.domicile_Mere_Epoux = domicile_Mere_Epoux;
	}


	public String getPrenom_Pere_Epoux() {
		return prenom_Pere_Epoux;
	}


	public void setPrenom_Pere_Epoux(String prenom_Pere_Epoux) {
		this.prenom_Pere_Epoux = prenom_Pere_Epoux;
	}


	public String getNom_Pere_Epoux() {
		return nom_Pere_Epoux;
	}


	public void setNom_Pere_Epoux(String nom_Pere_Epoux) {
		this.nom_Pere_Epoux = nom_Pere_Epoux;
	}


	public String getDate_Naissance_Pere_Epoux() {
		return date_Naissance_Pere_Epoux;
	}


	public void setDate_Naissance_Pere_Epoux(String date_Naissance_Pere_Epoux) {
		this.date_Naissance_Pere_Epoux = date_Naissance_Pere_Epoux;
	}


	public String getLieu_Naissance_Pere_Epoux() {
		return lieu_Naissance_Pere_Epoux;
	}


	public void setLieu_Naissance_Pere_Epoux(String lieu_Naissance_Pere_Epoux) {
		this.lieu_Naissance_Pere_Epoux = lieu_Naissance_Pere_Epoux;
	}


	public String getProfession_Pere_Epoux() {
		return profession_Pere_Epoux;
	}


	public void setProfession_Pere_Epoux(String profession_Pere_Epoux) {
		this.profession_Pere_Epoux = profession_Pere_Epoux;
	}


	public String getDomicile_Pere_Epoux() {
		return domicile_Pere_Epoux;
	}


	public void setDomicile_Pere_Epoux(String domicile_Pere_Epoux) {
		this.domicile_Pere_Epoux = domicile_Pere_Epoux;
	}


	public String getPrenom_Epouse() {
		return prenom_Epouse;
	}


	public void setPrenom_Epouse(String prenom_Epouse) {
		this.prenom_Epouse = prenom_Epouse;
	}


	public String getNom_Epouse() {
		return nom_Epouse;
	}


	public void setNom_Epouse(String nom_Epouse) {
		this.nom_Epouse = nom_Epouse;
	}


	public String getDate_Naissance_Epouse() {
		return date_Naissance_Epouse;
	}


	public void setDate_Naissance_Epouse(String date_Naissance_Epouse) {
		this.date_Naissance_Epouse = date_Naissance_Epouse;
	}


	public String getLieu_Naissance_Epouse() {
		return lieu_Naissance_Epouse;
	}


	public void setLieu_Naissance_Epouse(String lieu_Naissance_Epouse) {
		this.lieu_Naissance_Epouse = lieu_Naissance_Epouse;
	}


	public String getProfession_Epouse() {
		return profession_Epouse;
	}


	public void setProfession_Epouse(String profession_Epouse) {
		this.profession_Epouse = profession_Epouse;
	}


	public String getDomicile_Epouse() {
		return domicile_Epouse;
	}


	public void setDomicile_Epouse(String domicile_Epouse) {
		this.domicile_Epouse = domicile_Epouse;
	}


	public String getResidence_Epouse() {
		return residence_Epouse;
	}


	public void setResidence_Epouse(String residence_Epouse) {
		this.residence_Epouse = residence_Epouse;
	}


	public String getPrenom_Mere_Epouse() {
		return prenom_Mere_Epouse;
	}


	public void setPrenom_Mere_Epouse(String prenom_Mere_Epouse) {
		this.prenom_Mere_Epouse = prenom_Mere_Epouse;
	}


	public String getNom_Mere_Epouse() {
		return nom_Mere_Epouse;
	}


	public void setNom_Mere_Epouse(String nom_Mere_Epouse) {
		this.nom_Mere_Epouse = nom_Mere_Epouse;
	}


	public String getDate_Naissance_Mere_Epouse() {
		return date_Naissance_Mere_Epouse;
	}


	public void setDate_Naissance_Mere_Epouse(String date_Naissance_Mere_Epouse) {
		this.date_Naissance_Mere_Epouse = date_Naissance_Mere_Epouse;
	}


	public String getLieu_Naissance_Mere_Epouse() {
		return lieu_Naissance_Mere_Epouse;
	}


	public void setLieu_Naissance_Mere_Epouse(String lieu_Naissance_Mere_Epouse) {
		this.lieu_Naissance_Mere_Epouse = lieu_Naissance_Mere_Epouse;
	}


	public String getProfession_Mere_Epouse() {
		return profession_Mere_Epouse;
	}


	public void setProfession_Mere_Epouse(String profession_Mere_Epouse) {
		this.profession_Mere_Epouse = profession_Mere_Epouse;
	}


	public String getDomicile_Mere_Epouse() {
		return domicile_Mere_Epouse;
	}


	public void setDomicile_Mere_Epouse(String domicile_Mere_Epouse) {
		this.domicile_Mere_Epouse = domicile_Mere_Epouse;
	}


	public String getPrenom_Pere_Epouse() {
		return prenom_Pere_Epouse;
	}


	public void setPrenom_Pere_Epouse(String prenom_Pere_Epouse) {
		this.prenom_Pere_Epouse = prenom_Pere_Epouse;
	}


	public String getNom_Pere_Epouse() {
		return nom_Pere_Epouse;
	}


	public void setNom_Pere_Epouse(String nom_Pere_Epouse) {
		this.nom_Pere_Epouse = nom_Pere_Epouse;
	}


	public String getDate_Naissance_Pere_Epouse() {
		return date_Naissance_Pere_Epouse;
	}


	public void setDate_Naissance_Pere_Epouse(String date_Naissance_Pere_Epouse) {
		this.date_Naissance_Pere_Epouse = date_Naissance_Pere_Epouse;
	}


	public String getLieu_Naissance_Pere_Epouse() {
		return lieu_Naissance_Pere_Epouse;
	}


	public void setLieu_Naissance_Pere_Epouse(String lieu_Naissance_Pere_Epouse) {
		this.lieu_Naissance_Pere_Epouse = lieu_Naissance_Pere_Epouse;
	}


	public String getProfession_Pere_Epouse() {
		return profession_Pere_Epouse;
	}


	public void setProfession_Pere_Epouse(String profession_Pere_Epouse) {
		this.profession_Pere_Epouse = profession_Pere_Epouse;
	}


	public String getDomicile_Pere_Epouse() {
		return domicile_Pere_Epouse;
	}


	public void setDomicile_Pere_Epouse(String domicile_Pere_Epouse) {
		this.domicile_Pere_Epouse = domicile_Pere_Epouse;
	}


	public String getPrenom_Temoin1_Epoux() {
		return prenom_Temoin1_Epoux;
	}


	public void setPrenom_Temoin1_Epoux(String prenom_Temoin1_Epoux) {
		this.prenom_Temoin1_Epoux = prenom_Temoin1_Epoux;
	}


	public String getNom_Temoin1_Epoux() {
		return nom_Temoin1_Epoux;
	}


	public void setNom_Temoin1_Epoux(String nom_Temoin1_Epoux) {
		this.nom_Temoin1_Epoux = nom_Temoin1_Epoux;
	}


	public String getProfession_Temoin1_Epoux() {
		return profession_Temoin1_Epoux;
	}


	public void setProfession_Temoin1_Epoux(String profession_Temoin1_Epoux) {
		this.profession_Temoin1_Epoux = profession_Temoin1_Epoux;
	}


	public String getDomicile_Temoin1_Epoux() {
		return domicile_Temoin1_Epoux;
	}


	public void setDomicile_Temoin1_Epoux(String domicile_Temoin1_Epoux) {
		this.domicile_Temoin1_Epoux = domicile_Temoin1_Epoux;
	}


	public String getCni_Temoin1_Epoux() {
		return cni_Temoin1_Epoux;
	}


	public void setCni_Temoin1_Epoux(String cni_Temoin1_Epoux) {
		this.cni_Temoin1_Epoux = cni_Temoin1_Epoux;
	}


	public String getPrenom_Temoin2_Epoux() {
		return prenom_Temoin2_Epoux;
	}


	public void setPrenom_Temoin2_Epoux(String prenom_Temoin2_Epoux) {
		this.prenom_Temoin2_Epoux = prenom_Temoin2_Epoux;
	}


	public String getNom_Temoin2_Epoux() {
		return nom_Temoin2_Epoux;
	}


	public void setNom_Temoin2_Epoux(String nom_Temoin2_Epoux) {
		this.nom_Temoin2_Epoux = nom_Temoin2_Epoux;
	}


	public String getProfession_Temoin2_Epoux() {
		return profession_Temoin2_Epoux;
	}


	public void setProfession_Temoin2_Epoux(String profession_Temoin2_Epoux) {
		this.profession_Temoin2_Epoux = profession_Temoin2_Epoux;
	}


	public String getDomicile_Temoin2_Epoux() {
		return domicile_Temoin2_Epoux;
	}


	public void setDomicile_Temoin2_Epoux(String domicile_Temoin2_Epoux) {
		this.domicile_Temoin2_Epoux = domicile_Temoin2_Epoux;
	}


	public String getCni_Temoin2_Epoux() {
		return cni_Temoin2_Epoux;
	}


	public void setCni_Temoin2_Epoux(String cni_Temoin2_Epoux) {
		this.cni_Temoin2_Epoux = cni_Temoin2_Epoux;
	}


	public String getPrenom_Temoin1_Epouse() {
		return prenom_Temoin1_Epouse;
	}


	public void setPrenom_Temoin1_Epouse(String prenom_Temoin1_Epouse) {
		this.prenom_Temoin1_Epouse = prenom_Temoin1_Epouse;
	}


	public String getNom_Temoin1_Epouse() {
		return nom_Temoin1_Epouse;
	}


	public void setNom_Temoin1_Epouse(String nom_Temoin1_Epouse) {
		this.nom_Temoin1_Epouse = nom_Temoin1_Epouse;
	}


	public String getProfession_Temoin1_Epouse() {
		return profession_Temoin1_Epouse;
	}


	public void setProfession_Temoin1_Epouse(String profession_Temoin1_Epouse) {
		this.profession_Temoin1_Epouse = profession_Temoin1_Epouse;
	}


	public String getDomicile_Temoin1_Epouse() {
		return domicile_Temoin1_Epouse;
	}


	public void setDomicile_Temoin1_Epouse(String domicile_Temoin1_Epouse) {
		this.domicile_Temoin1_Epouse = domicile_Temoin1_Epouse;
	}


	public String getCni_Temoin1_Epouse() {
		return cni_Temoin1_Epouse;
	}


	public void setCni_Temoin1_Epouse(String cni_Temoin1_Epouse) {
		this.cni_Temoin1_Epouse = cni_Temoin1_Epouse;
	}


	public String getPrenom_Temoin2_Epouse() {
		return prenom_Temoin2_Epouse;
	}


	public void setPrenom_Temoin2_Epouse(String prenom_Temoin2_Epouse) {
		this.prenom_Temoin2_Epouse = prenom_Temoin2_Epouse;
	}


	public String getNom_Temoin2_Epouse() {
		return nom_Temoin2_Epouse;
	}


	public void setNom_Temoin2_Epouse(String nom_Temoin2_Epouse) {
		this.nom_Temoin2_Epouse = nom_Temoin2_Epouse;
	}


	public String getProfession_Temoin2_Epouse() {
		return profession_Temoin2_Epouse;
	}


	public void setProfession_Temoin2_Epouse(String profession_Temoin2_Epouse) {
		this.profession_Temoin2_Epouse = profession_Temoin2_Epouse;
	}


	public String getDomicile_Temoin2_Epouse() {
		return domicile_Temoin2_Epouse;
	}


	public void setDomicile_Temoin2_Epouse(String domicile_Temoin2_Epouse) {
		this.domicile_Temoin2_Epouse = domicile_Temoin2_Epouse;
	}


	public String getCni_Temoin2_Epouse() {
		return cni_Temoin2_Epouse;
	}


	public void setCni_Temoin2_Epouse(String cni_Temoin2_Epouse) {
		this.cni_Temoin2_Epouse = cni_Temoin2_Epouse;
	}


	public String getDate_Jugement() {
		return date_Jugement;
	}


	public void setDate_Jugement(String date_Jugement) {
		this.date_Jugement = date_Jugement;
	}


	public String getNumero_Jugement() {
		return numero_Jugement;
	}


	public void setNumero_Jugement(String numero_Jugement) {
		this.numero_Jugement = numero_Jugement;
	}


	public String getTribunal() {
		return tribunal;
	}


	public void setTribunal(String tribunal) {
		this.tribunal = tribunal;
	}


	public String getMentions_Marginales() {
		return mentions_Marginales;
	}


	public void setMentions_Marginales(String mentions_Marginales) {
		this.mentions_Marginales = mentions_Marginales;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public String getNumero_Acte() {
		return numero_Acte;
	}


	public void setNumero_Acte(String numero_Acte) {
		this.numero_Acte = numero_Acte;
	}


	public String getDate_creation() {
		return date_creation;
	}


	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}


	public String getDate_modification() {
		return date_modification;
	}


	public void setDate_modification(String date_modification) {
		this.date_modification = date_modification;
	}


	public String getDate_validation() {
		return date_validation;
	}


	public void setDate_validation(String date_validation) {
		this.date_validation = date_validation;
	}


	public String getType_Declaration() {
		return type_Declaration;
	}


	public void setType_Declaration(String type_Declaration) {
		this.type_Declaration = type_Declaration;
	}


	public Users getId_Createur() {
		return createurMariage;
	}


	public void setId_Createur(Users id_Createur) {
		this.createurMariage = id_Createur;
	}


	public Users getId_Modificateur() {
		return modificateurMariage;
	}


	public void setId_Modificateur(Users id_Modificateur) {
		this.modificateurMariage = id_Modificateur;
	}


	public Users getValidateurMariage() {
		return validateurMariage;
	}


	public void setId_Validateur(Users id_Validateur) {
		this.validateurMariage = id_Validateur;
	}


	public Date getDateMariage() {
		return dateMariage;
	}


	public void setDateMariage(Date dateMariage) {
		this.dateMariage = dateMariage;
	}


	public Date getDateNaissanceEpoux() {
		return dateNaissanceEpoux;
	}


	public void setDateNaissanceEpoux(Date dateNaissanceEpoux) {
		this.dateNaissanceEpoux = dateNaissanceEpoux;
	}


	public Date getDateNaissanceEpouse() {
		return dateNaissanceEpouse;
	}


	public void setDateNaissanceEpouse(Date dateNaissanceEpouse) {
		this.dateNaissanceEpouse = dateNaissanceEpouse;
	}


	public Date getHeureMariage() {
		return heureMariage;
	}


	public void setHeureMariage(Date heureMariage) {
		this.heureMariage = heureMariage;
	}


	public Date getDateNaissancePereEpoux() {
		return dateNaissancePereEpoux;
	}


	public void setDateNaissancePereEpoux(Date dateNaissancePereEpoux) {
		this.dateNaissancePereEpoux = dateNaissancePereEpoux;
	}


	public Date getDateNaissanceMereEpoux() {
		return dateNaissanceMereEpoux;
	}


	public void setDateNaissanceMereEpoux(Date dateNaissanceMereEpoux) {
		this.dateNaissanceMereEpoux = dateNaissanceMereEpoux;
	}


	public Date getDateNaissancePereEpouse() {
		return dateNaissancePereEpouse;
	}


	public void setDateNaissancePereEpouse(Date dateNaissancePereEpouse) {
		this.dateNaissancePereEpouse = dateNaissancePereEpouse;
	}


	public Date getDateNaissanceMereEpouse() {
		return dateNaissanceMereEpouse;
	}


	public void setDateNaissanceMereEpouse(Date dateNaissanceMereEpouse) {
		this.dateNaissanceMereEpouse = dateNaissanceMereEpouse;
	}


	public Date getDateJugement() {
		return dateJugement;
	}


	public void setDateJugement(Date dateJugement) {
		this.dateJugement = dateJugement;
	}


	public Users getCreateurMariage() {
		return createurMariage;
	}


	public void setCreateurMariage(Users createurMariage) {
		this.createurMariage = createurMariage;
	}


	public Users getModificateurMariage() {
		return modificateurMariage;
	}


	public void setModificateurMariage(Users modificateurMariage) {
		this.modificateurMariage = modificateurMariage;
	}


	public void setValidateurMariage(Users validateurMariage) {
		this.validateurMariage = validateurMariage;
	}
	
	
	
}
