/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import models.DeclarationDeces;
import models.Search;
import models.Users;

/**
 *
 * @author sambasow
 */
public interface ActeDecesDAO {

    public abstract boolean SaveDeclaration(DeclarationDeces acte);

    public abstract boolean updateDeclaration(DeclarationDeces acte);

    public abstract boolean deleteActeDece(int idActe);

    public abstract DeclarationDeces findById(int idActe);

    public abstract List<DeclarationDeces> findByActeNum(int numActe);

    public abstract List<DeclarationDeces> getRegistre(String numRegistre);

    public abstract List<DeclarationDeces> getAllDeclaration();

    public abstract List<DeclarationDeces> getAllDeclarationByUser(Users user);

    public abstract List<DeclarationDeces> getAllDeclarationByDate(String date);

    public abstract List<DeclarationDeces> findDeclarationByIdUser(int idUser);

    public abstract List<DeclarationDeces> searchAD(Search s);

   // public abstract int IncrementeNumActe(String annee);

    public abstract List<DeclarationDeces> getAllDeclarationValider();

    //public abstract List<DeclarationDeces> getAllDeclaration();

    public abstract List<DeclarationDeces> getAllDeclarationRejeterByUser(Users u);

    public abstract List<DeclarationDeces> getCinqLastDeclaration();

    public boolean jugement(String num, String annee);

    public boolean jugementDeclaration(String num, String annee, int id);

    public String returnNumActe(int id);

    public String numActe(String annee);

    public boolean deleteActeDeces(int id);
    
    public boolean verifyNumeroJugement(String num, String annee);
    
    public boolean verifyNumeroJugement(String num, String annee, Integer id);
    
    public int findInstanceByDate(String date);
    
    public int findValidateByDate(String date);
}
