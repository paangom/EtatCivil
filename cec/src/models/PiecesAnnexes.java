package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Pieces_Annexes")
public class PiecesAnnexes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "Libelle", columnDefinition="varchar(100) default '' ", nullable=false)
    private String libelle;
	
	@Column(name = "Prix", columnDefinition="varchar(60) default '' ", nullable=true)
    private String prix;
	
	@Column(name = "Date_creation", columnDefinition="varchar(20) default '' ", nullable=false)
    private String date_creation;
	
	@Column(name = "Objectif", columnDefinition=" LONGTEXT")
    private String objectif;
	
	public PiecesAnnexes(){
		
	}

	public PiecesAnnexes(String libelle, String prix, String date_creation,
			String objectif) {
		this.libelle = libelle;
		this.prix = prix;
		this.date_creation = date_creation;
		this.objectif = objectif;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}
	
	

}
