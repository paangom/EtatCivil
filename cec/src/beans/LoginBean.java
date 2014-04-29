package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import models.Connected;
import models.Users;
import services.ConnectedService;
import services.UserServices;
import util.MyUtil;

public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = null;
    private Users userConnect = null;
    private UserServices uService = new UserServices();
    private boolean isLogin;  
    private ConnectedService cService = new ConnectedService();
    private List<Connected> listc;
    
    private boolean conneted = false;

    /**
     * Creates a new instance of loginBean
     */
    public LoginBean() {
        if (this.user == null) {
            this.user = new Users();
        }
        
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUserConnect() {
        userConnect = MyUtil.getUserLogged();
        return userConnect;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
    
    

    public String login() {
        String route = "";

        Users u = this.uService.connectUser(this.user);
        if (u != null) {
        	if(this.cService.findUser(u) == null){
        		if(cService.addConnected(u)){
        			conneted = true;
        			 isLogin = true;
        	            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", u.getUserUserName());
        	            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("profil", u.getUserProfil());
        	            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue", u.getUserUserName());
        	            FacesContext.getCurrentInstance().addMessage(null, message);
        	            if(u.isModify())
        	            	route = "/views/" + u.getUserProfil() + "/home?faces-redirect=true";
        	            else
        	            	route = "/views/" + u.getUserProfil() + "/update?faces-redirect=true";
        	        } else {
        	        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'authentification",  "Vous vous êtes déjà connecté. Veuillez vous déconnecter!");
        	            FacesContext.getCurrentInstance().addMessage(null, message);
        	        }

        	        
        		}
        	else{
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'authentification", "Vous vous êtes déjà connecté. Veuillez vous déconnecter!");
	            FacesContext.getCurrentInstance().addMessage(null, message);
        	}
        
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur d'authentification","Invalide nom d'utilisateur et/ou mot de passe");
            FacesContext.getCurrentInstance().addMessage(null, message);
	            if (this.user == null) {
	                this.user = new Users();
	            }
        	
        }
        return route;
    }


    /**
     *
     * @return
     */
    public String logout() {
    	 Users u = this.uService.connectUser(MyUtil.getUserLogged());
        String route = "";
        if(cService.deleteConnected(cService.findUser(u))){
        	isLogin = false;
        	conneted = false;
        	route = "/login?faces-redirect=true";
        }
        return route;
    }

    /**
     * An event listener for redirecting the user to login page if he/she is not
     * currently logged in
     *
     * @param event
     */

    public String verifyLoginOfficier(){
    	String route = "";
    	this.user.setUserId(this.userConnect.getUserId());
    	this.user.setCni(this.userConnect.getCni());
    	this.user.setUserAddress(this.userConnect.getUserAddress());
    	this.user.setUserNom(this.userConnect.getUserNom());
    	this.user.setUserNumMatricule(this.userConnect.getUserNumMatricule());
    	this.user.setUserNumTel(this.userConnect.getUserNumTel());
    	this.user.setUserPrenom(this.userConnect.getUserPrenom());
    	this.user.setUserProfil(this.userConnect.getUserProfil());
    	this.user.setCentre(this.userConnect.getCentre());
    	this.user.setSexe(this.userConnect.getSexe());
    	this.user.setModify(true);
    	
    	if(uService.findByUsername(this.user.getUserUserName()) != null){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Votre nouveau login est: "+user.getUserUserName()+" et votre mot de passe est: "+user.getUserPassword(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    	}
    	else{
    		
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Les paramètres de connexions ont été modifié avec succès!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    		uService.updateUser(user);
    		route = "/views/officier/home?faces-redirect=true";
    	}
    	return route;
    }
    
    
    public String verifyLoginAgent(){
    	String route = "";
    	this.user.setUserId(this.userConnect.getUserId());
    	this.user.setCni(this.userConnect.getCni());
    	this.user.setUserAddress(this.userConnect.getUserAddress());
    	this.user.setUserNom(this.userConnect.getUserNom());
    	this.user.setUserNumMatricule(this.userConnect.getUserNumMatricule());
    	this.user.setUserNumTel(this.userConnect.getUserNumTel());
    	this.user.setUserPrenom(this.userConnect.getUserPrenom());
    	this.user.setUserProfil(this.userConnect.getUserProfil());
    	this.user.setCentre(this.userConnect.getCentre());
    	this.user.setSexe(this.userConnect.getSexe());
    	this.user.setModify(true);
    	
    	if(uService.findByUsername(this.user.getUserUserName()) != null){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cet utilisateur existe déjà!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    	}
    	else{
    		
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre nouveau login est: "+user.getUserUserName()+" et votre mot de passe est: "+user.getUserPassword(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    		uService.updateUser(user);
    		route = "/views/agent/home?faces-redirect=true";
    	}
    	return route;
    }
    
    
    public void verifyUseLogin(ComponentSystemEvent event){
  	  if(!isLogin){
  		String url = MyUtil.basePathLogin();
  	   doRedirect(url);
  	  }
  	 }

  	 /**
  	 * Method for redirecting a request
  	 * @param url
  	 */
  	 private void doRedirect(String url){
  	  try {
  	   FacesContext context=FacesContext.getCurrentInstance();
  	   context.getExternalContext().redirect(url);
  	  } catch (IOException e) {
  	   e.printStackTrace();
  	  }
  	 }

	/**
	 * @return the listc
	 */
	public List<Connected> getListc() {
		listc = cService.findAllUsers();
		return listc;
	}

	/**
	 * @param listc the listc to set
	 */
	public void setListc(List<Connected> listc) {
		this.listc = listc;
	}

	/**
	 * @return the conneted
	 */
	public boolean isConneted() {
		return conneted;
	}

	/**
	 * @param conneted the conneted to set
	 */
	public void setConneted(boolean conneted) {
		this.conneted = conneted;
	}


}
