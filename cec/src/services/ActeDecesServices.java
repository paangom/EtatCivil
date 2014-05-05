/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import dao.ActeDecesDAO;

import java.util.List;

import models.DeclarationDeces;
import models.Search;
import models.Users;
import util.Factory;

/**
 *
 * @author sambasow
 */
public class ActeDecesServices {
    
    private ActeDecesDAO deceDAO = Factory.getActeDecesDAO();
    
    public boolean saveDeclarationDC(DeclarationDeces dec){
        return deceDAO.SaveDeclaration(dec);
    }
    
    public boolean updateDeclarationDeces(DeclarationDeces dec){
    	return deceDAO.updateDeclaration(dec);
    }
    
    public DeclarationDeces findById(int id){
        return deceDAO.findById(id);
    }
    
    public List<DeclarationDeces> getRegistreDeceByNum(String numRegistre){
        return deceDAO.getRegistre(numRegistre);
    }
    
    public List<DeclarationDeces> getAllDeclarationDece(){
        return deceDAO.getAllDeclaration();
    }
    
    public List<DeclarationDeces> getAllDeclarationDeceByUser(Users user){
        return deceDAO.getAllDeclarationByUser(user);
    }
    
    public List<DeclarationDeces> getAllDeclarationDeceByUserReject(Users user){
        return deceDAO.getAllDeclarationRejeterByUser(user);
    }
    
    public List<DeclarationDeces> getRegistreDece(){
        return deceDAO.getAllDeclarationValider();
    }
    
    public List<DeclarationDeces> searchAD(Search s){
        return deceDAO.searchAD(s);
    }
    
    public String numeroActe(String annee){
    	return deceDAO.numActe(annee);
    }
    
    public boolean verifyNumeroJugement(String num ,String annee){
    	return deceDAO.verifyNumeroJugement(num, annee);
    }
    
    public boolean verifyNumeroJugement(String num ,String annee, Integer id){
    	return deceDAO.verifyNumeroJugement(num, annee, id);
    }
    
    public int findInstanceByDate(String date){
    	return deceDAO.findInstanceByDate(date);
    }
    
    public int findValidateByDate(String date){
    	return deceDAO.findValidateByDate(date);
    }
    
}
