/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.Serializable;
import java.util.List;

import models.DeclarationNaissance;
import models.Search;
import models.Users;
import util.Factory;
import dao.ActeNaissanceDAO;

/**
 *
 * @author sambasow 
 */
@SuppressWarnings("serial")
public class ActeNaissanceServices implements Serializable {

    private ActeNaissanceDAO acteDAO = Factory.getActeNaissanceDAO();

    // Ajout d'une nouvelle déclaration
    public boolean addDeclaration(DeclarationNaissance d) {
        return acteDAO.addDeclaration(d);
    }

    // Recupération d'une déclaration
    public DeclarationNaissance getDeclaration(int id) {
        return acteDAO.getDeclaration(id);
    }

    // Recupération d'un acte de naissance
    public DeclarationNaissance getActeNaissance(int numActe) {
        return acteDAO.getActeNaissance(numActe);
    }

//    // Valider une déclaration
//    public boolean validateDeclaration(DeclarationNaissance d) {
//        return acteDAO.validateDeclaration(d);
//    }
//
//    // Rejeter une déclaration
//    public boolean invalidateDeclaration(DeclarationNaissance d) {
//        return acteDAO.invalidateDeclaration(d);
//    }

    // Liste de toutes les déclarations
    public List<DeclarationNaissance> getAllDeclaration() {
        return acteDAO.getAllDeclaration();
    }

    // Liste de tous les actes de naissance
    public List<DeclarationNaissance> getAllActe() {
        return acteDAO.getAllActe();
    }

    // Liste de tous les acte de naissance d'un registre donné
    public List<DeclarationNaissance> getAllActeByRegistre(int registre) {
        return null;
    }

    // Modification d'un acte de naissance
    public boolean updateActe(DeclarationNaissance d) {
        return acteDAO.updateActe(d);
    }
    
    public boolean updateDeclarationNaissance(DeclarationNaissance dec){
    	return acteDAO.updateDeclaration(dec);
    }

    public List<DeclarationNaissance> getAllDeclarationInstance() {
        return acteDAO.getAllDeclarationInstance();
    }
    
    public List<DeclarationNaissance> getAllDeclarationByUser(Users u) {
        return acteDAO.getAllDecByUser(u);
    }
    
    public List<DeclarationNaissance> getAllDeclarationRejectByUser(Users u) {
        return acteDAO.getAllDecByUserReject(u);
    }
    
    public List<DeclarationNaissance> searchAN(Search s){
        return acteDAO.searchAN(s);
    }

    public String numeroActe(String annee) {
        return acteDAO.numeroActe(annee);
    }
    
    public boolean verifyNumeroJugement(String num, String annee){
    	return acteDAO.verifyNumeroJugement(num, annee);
    }
    
    public boolean verifyNumeroJugement(String num, String annee, Integer id){
    	return acteDAO.verifyNumeroJugement(num, annee, id);
    }
    
    public List<DeclarationNaissance> registresCurrentYear(String year){
    	
    	return acteDAO.registresCurrentYear(year);
    }
    
   
}
