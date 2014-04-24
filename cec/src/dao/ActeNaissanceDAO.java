/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import models.DeclarationNaissance;
import models.Search;
import models.Users;

/**
 *
 * @author sambasow
 */
public interface ActeNaissanceDAO {
    // Ajout d'une nouvelle déclaration
    public boolean addDeclaration(DeclarationNaissance d);
    // Recupération d'une déclaration
    public DeclarationNaissance getDeclaration(int id);
    // Recupération d'un acte de naissance
    public DeclarationNaissance getActeNaissance(int numActe);
//    // Valider une déclaration
//    public boolean validateDeclaration(DeclarationNaissance d);
//    // Rejeter une déclaration
//    public boolean invalidateDeclaration(DeclarationNaissance d);
    // Liste de toutes les déclarations en instance
    public List<DeclarationNaissance> getAllDeclarationInstance();
    // Liste de toutes les déclarations
    public List<DeclarationNaissance> getAllDeclaration();
    // Liste de toutes les déclaration par utilisateur
    public List<DeclarationNaissance>  getAllDecByUser(Users u);
    // Liste de toutes les déclaration par utilisateur
    public List<DeclarationNaissance>  getAllDecByUserReject(Users u);
    // Liste de tous les actes de naissance
    public List<DeclarationNaissance> getAllActe();
    // Liste de tous les acte de naissance d'un registre donné
    public List<DeclarationNaissance> getAllActeByRegistre(int registre);
    // Modification d'un acte de naissance
    public boolean updateActe(DeclarationNaissance d);
    // Modification d'un acte de naissance
    public boolean updateDeclaration(DeclarationNaissance d);
    
    public abstract List<DeclarationNaissance> searchAN(Search s);
    
    public String numeroActe(String annee);
    
    public boolean verifyNumeroJugement(String num, String annee);
    
    public boolean verifyNumeroJugement(String num, String annee, Integer id);
    
    public List<DeclarationNaissance> registresCurrentYear(String year);
    
}
