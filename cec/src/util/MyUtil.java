/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.context.FacesContext;
import models.Users;
import services.UserServices;

/**
 *
 * @author sambasow
 */
public class MyUtil {

    public static Users getUserLogged() {
        UserServices uServices = new UserServices();
        //login is current name defined in our managed bean.
        String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        Users user = uServices.findByUsername(userName);
        return user;
    }

    public static String getUserName() {
        Users u = getUserLogged();
        return u.getUserUserName();
    }
    
    public static String getProfil(){
    	if(getUserLogged() != null)
    		return getUserLogged().getUserProfil();
    	else
    		return null;
    }
    
    public static int getUserId() {
        Users u = getUserLogged();
        return u.getUserId();
    }

    public static String baseUrl() {
        return "http://localhost:8080/cec/";
    }

    public static String basePathLogin() {
        return "/cec/";
    }

    public static String basePath() {
        String profil = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profil");
        String baseUrl = "/faces/views/"+profil+"/";
        return baseUrl;
    }
    
    public static String basePathAdmin() {
        
        String baseUrl = "/faces/views/officier/configuration/";
        return baseUrl;
    }
    
    public static String pathLogin(){
    	return "/login?faces-redirect=true";
    }
    
    public static String pathDeclaration() {
        return basePath()+"declaration/";
    }
    
    public static String pathConsultationDeclaration() {
        return basePath()+"consultation/declaration/";
    }
    
    public static String pathConsultationActe() {
        return basePath()+"consultation/acte/";
    }
    
    public static String pathModificationDeclaration() {
        return basePath()+"modification/declaration/";
    }
    
    public static String pathModificationActe() {
        return basePath()+"modification/acte/";
    }
    
    public static String pathMAJMM() {
        return basePath()+"modification/mentions/";
    }
    
    public static String pathRegistre() {
        return "/faces/views/registre/";
    }
}
