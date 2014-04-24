/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import dao.ActeMariageDAO;
import java.util.List;
import models.DeclarationMariage;
import models.Search;
import models.Users;
import util.Factory;

/**
 *
 * @author sambasow
 */
public class ActeMariageServices {
    
    private ActeMariageDAO mariageDAO = Factory.getActeMariageDAO();
    
    public boolean saveDeclarationMariage(DeclarationMariage dec){
        return mariageDAO.saveDeclarationMariage(dec);
    }
    
    public List<DeclarationMariage> getAllDecMaraigeByUser(Users u){
        return mariageDAO.findDeclarationByIdUser(u);
    }
    
    public List<DeclarationMariage> getAllDecMariage(){
        return mariageDAO.getAllDeclarationMariage();
    }
    
    public DeclarationMariage getDeclarationMariage(int id){
        return mariageDAO.findById(id);
    }
    
    public List<DeclarationMariage> getRegistreMariage(){
        return mariageDAO.getRegistreMariage();
    }
    
    public List<DeclarationMariage> searchAM(Search s){
        return mariageDAO.searchAM(s);
    }
    
    public boolean updateDeclarationMariage(DeclarationMariage dec){
    	return mariageDAO.updateDeclarationMariage(dec);
    }
    
    public String numeroActe(String annee){
    	return mariageDAO.numActe(annee);
    }
    
    public boolean verifyNumeroJugement(String num, String annee){
    	return mariageDAO.verifyNumeroJugement(num, annee);
    }
    
    public boolean verifyNumeroJugement(String num, String annee, Integer id){
    	return mariageDAO.verifyNumeroJugement(num, annee, id);
    }
    
    public List<DeclarationMariage> registreMariageCurrentYear(String year){
    	return mariageDAO.registreMariageCurrentYear(year);
    }
    
}
