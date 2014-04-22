/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import models.Centres;
import services.CentreServices;


public class CentreRBean {
    private CentreServices cenServ = new CentreServices();
    private Centres centreToConsult = null;

    /**
     * Creates a new instance of centreRBean
     */
    public CentreRBean() {
    }

    public Centres getCentreToConsult() {
        centreToConsult = cenServ.getCentre();
        return centreToConsult;
    }
    
    
    
}
