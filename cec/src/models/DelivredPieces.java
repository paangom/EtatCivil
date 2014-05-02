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


@Entity
@Table(name="DelivredPieces")
public class DelivredPieces implements Serializable{

	private static final long serialVersionUID = -731468984861123772L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Integer id;
	
	@Column(name = "Paiement", columnDefinition="boolean default false ")
    private boolean paiement;
	
	@Column(name = "date_paiement", columnDefinition="varchar(20) default '' ")
    private String date_paiement;
	
	@Column(name = "date_delivre", columnDefinition="varchar(20) default '' ")
    private String date_delivre;
	
	@ManyToOne
	(
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinColumn(name="userdelivred")
	private Users userdelivred;
	
	@ManyToOne
	(
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinColumn(name="usermodify")
	private Users usermodify;
	
	
	@ManyToOne
	(
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinColumn(name="piecedelivred")
    private PiecesAnnexes piecedelivred;

	public DelivredPieces(boolean paiement, String date_paiement,
			String date_delivre, Users userdelivred, PiecesAnnexes piecedelivred) {
		super();
		this.paiement = paiement;
		this.date_paiement = date_paiement;
		this.date_delivre = date_delivre;
		this.userdelivred = userdelivred;
		this.piecedelivred = piecedelivred;
	}

	public DelivredPieces() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the paiement
	 */
	public boolean isPaiement() {
		return paiement;
	}

	/**
	 * @param paiement the paiement to set
	 */
	public void setPaiement(boolean paiement) {
		this.paiement = paiement;
	}

	/**
	 * @return the date_paiement
	 */
	public String getDate_paiement() {
		return date_paiement;
	}

	/**
	 * @param date_paiement the date_paiement to set
	 */
	public void setDate_paiement(String date_paiement) {
		this.date_paiement = date_paiement;
	}

	/**
	 * @return the date_delivre
	 */
	public String getDate_delivre() {
		return date_delivre;
	}

	/**
	 * @param date_delivre the date_delivre to set
	 */
	public void setDate_delivre(String date_delivre) {
		this.date_delivre = date_delivre;
	}

	/**
	 * @return the userdelivred
	 */
	public Users getUserdelivred() {
		return userdelivred;
	}

	/**
	 * @param userdelivred the userdelivred to set
	 */
	public void setUserdelivred(Users userdelivred) {
		this.userdelivred = userdelivred;
	}

	/**
	 * @return the piecedelivred
	 */
	public PiecesAnnexes getPiecedelivred() {
		return piecedelivred;
	}

	/**
	 * @param piecedelivred the piecedelivred to set
	 */
	public void setPiecedelivred(PiecesAnnexes piecedelivred) {
		this.piecedelivred = piecedelivred;
	}
	
	/**
	 * @return the dateD
	 */
	public Date getDateD() {
		return dateD;
	}

	/**
	 * @param dateD the dateD to set
	 */
	public void setDateD(Date dateD) {
		this.dateD = dateD;
	}

	/**
	 * @return the dateP
	 */
	public Date getDateP() {
		return dateP;
	}

	/**
	 * @param dateP the dateP to set
	 */
	public void setDateP(Date dateP) {
		this.dateP = dateP;
	}
	
	

	/**
	 * @return the usermodify
	 */
	public Users getUsermodify() {
		return usermodify;
	}

	/**
	 * @param usermodify the usermodify to set
	 */
	public void setUsermodify(Users usermodify) {
		this.usermodify = usermodify;
	}



	private Date dateD;
	private Date dateP;
}
