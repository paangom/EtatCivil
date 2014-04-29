package models;
// Generated 24 mars 2014 10:24:07 by Hibernate Tools 3.6.0


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
 * Connected generated by hbm2java
 */
@Entity
@Table(name="connected")
public class Connected  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "Adresse_Ip", columnDefinition="varchar(40) default '' ", nullable=false, unique = true)
    private String adresseIp;
	
	@Column(name = "Temps_Connexion", columnDefinition="varchar(80) default '' ", nullable=false)
    private Long temps_connexion;
	
	@ManyToOne
	(
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinColumn(name="connected", nullable = false, unique = true)
	private Users connected;

    public Connected() {
    }

    public Connected(String connectedIp, Long connectedTime, Users connected) {
       this.connected = connected;
       this.adresseIp = connectedIp;
       this.temps_connexion = connectedTime;
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
	 * @return the adresseIp
	 */
	public String getAdresseIp() {
		return adresseIp;
	}

	/**
	 * @param adresseIp the adresseIp to set
	 */
	public void setAdresseIp(String adresseIp) {
		this.adresseIp = adresseIp;
	}

	/**
	 * @return the temps_connexion
	 */
	public Long getTemps_connexion() {
		return temps_connexion;
	}

	/**
	 * @param temps_connexion the temps_connexion to set
	 */
	public void setTemps_connexion(Long temps_connexion) {
		this.temps_connexion = temps_connexion;
	}

	/**
	 * @return the connected
	 */
	public Users getConnected() {
		return connected;
	}

	/**
	 * @param connected the connected to set
	 */
	public void setConnected(Users connected) {
		this.connected = connected;
	}
    
    

}