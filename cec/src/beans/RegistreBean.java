/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import models.DeclarationDeces;
import models.DeclarationMariage;
import models.DeclarationNaissance;

/**
 *
 * @author sambasow
 */
public class RegistreBean {
	
	private DeclarationNaissance decToConsult;
    private DeclarationDeces decDCToConsult;
    private DeclarationMariage decMarToConsult;
    /**
     * Creates a new instance of registreBean
     */
    public RegistreBean() {
    }

	/**
	 * @return the decToConsult
	 */
	public DeclarationNaissance getDecToConsult() {
		return decToConsult;
	}

	/**
	 * @param decToConsult the decToConsult to set
	 */
	public void setDecToConsult(DeclarationNaissance decToConsult) {
		this.decToConsult = decToConsult;
	}

	/**
	 * @return the decDCToConsult
	 */
	public DeclarationDeces getDecDCToConsult() {
		return decDCToConsult;
	}

	/**
	 * @param decDCToConsult the decDCToConsult to set
	 */
	public void setDecDCToConsult(DeclarationDeces decDCToConsult) {
		this.decDCToConsult = decDCToConsult;
	}

	/**
	 * @return the decMarToConsult
	 */
	public DeclarationMariage getDecMarToConsult() {
		return decMarToConsult;
	}

	/**
	 * @param decMarToConsult the decMarToConsult to set
	 */
	public void setDecMarToConsult(DeclarationMariage decMarToConsult) {
		this.decMarToConsult = decMarToConsult;
	}
    
	
}
