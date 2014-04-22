/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author sambasow
 */
public class Search {
    
    private String numActe;
    private String numReg;
    private String type;

    public Search() {
    }

    public String getNumActe() {
        return numActe;
    }

    public String getNumReg() {
        return numReg;
    }

    public String getType() {
        return type;
    }

    public void setNumActe(String numActe) {
        this.numActe = numActe;
    }

    public void setNumReg(String numReg) {
        this.numReg = numReg;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
