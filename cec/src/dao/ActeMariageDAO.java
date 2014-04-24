/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import models.DeclarationMariage;
import models.Search;
import models.Users;

/**
 *
 * @author sambasow
 */
public interface ActeMariageDAO {

    public boolean saveDeclarationMariage(DeclarationMariage acte);

    public List<DeclarationMariage> findDeclarationByIdUser(Users user);

//    public boolean validateDeclarationMariage(DeclarationMariage dec);
//    
//    public boolean invalidateDeclarationMariage(DeclarationMariage dec);

    public boolean deleteDeclarationMariage(int id);

    public List<DeclarationMariage> getAllDeclarationMariage();
    
   // public List<DeclarationMariage> getAllDecByUserReject(Users u);

    public DeclarationMariage findById(int idActe);
    
    public abstract List<DeclarationMariage> searchAM(Search s);

    public String numActe(String annee);

    public List<DeclarationMariage> getRegistreMariage();

    public boolean updateRegistreMariage();
    
    public boolean updateDeclarationMariage(DeclarationMariage dec);

    public String returnNumActe(int id);

    public boolean deleteActeMariage(int id);

    public boolean jugement(String num, String annee);

    public boolean jugementDeclaration(String num, String annee, int id);
    
//    public int incremente(String numReg);
    
    public boolean verifyNumeroJugement(String num, String annee);
    
    public boolean verifyNumeroJugement(String num, String annee, Integer id);
    
    public List<DeclarationMariage> registreMariageCurrentYear(String year);
}
