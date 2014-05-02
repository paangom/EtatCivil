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
    
    

    @SuppressWarnings("static-access")
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
        	            
        	            if(u.isModify()){
        	            	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenue", u.getUserPrenom()+" "+u.getUserNom());
        	            	FacesContext context = FacesContext.getCurrentInstance();
            	            context.getCurrentInstance().addMessage(null, message);
        	            	
        	            	context.getExternalContext().getFlash().setKeepMessages(true);
        	            	route = "/views/" + u.getUserProfil() + "/home?faces-redirect=true";
        	            }
        	            else{
        	            	route = "/views/" + u.getUserProfil() + "/update?faces-redirect=true";
        	            }
        	        } else {
        	        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'authentification",  "Vous vous êtes déjà connecté. Veuillez vous déconnecter!");
        	        	FacesContext context = FacesContext.getCurrentInstance();
        	            context.getCurrentInstance().addMessage(null, message);
    	            	
    	            	context.getExternalContext().getFlash().setKeepMessages(true);
        	        }

        	        
        	}
        	else{
        		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur d'authentification", "Vous vous êtes déjà connecté. Veuillez vous déconnecter!");
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.getCurrentInstance().addMessage(null, message);
            	
            	context.getExternalContext().getFlash().setKeepMessages(true);
        	}
        
        }else if(this.user.getUserUserName().equals("admin") && this.user.getUserPassword().equals("admin")){
        	
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreur d'authentification","Invalide nom d'utilisateur et/ou mot de passe");
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
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
        String route = "";
        if(cService.deleteConnected(cService.findUser(MyUtil.getUserLogged()))){
        	isLogin = false;
        	conneted = false;
        	route = MyUtil.pathLogin();
        }
        return route;
    }

    /**
     * An event listener for redirecting the user to login page if he/she is not
     * currently logged in
     *
     * @param event
     */

    @SuppressWarnings("static-access")
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
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else{
    		
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Les paramètres de connexions ont été modifié avec succès!", null);
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    		uService.updateUser(user);
    		route = "/views/officier/home?faces-redirect=true";
    	}
    	return route;
    }
    
    
    @SuppressWarnings("static-access")
	public String verifyLoginAgent(){
    	String route = "";
    	if(MyUtil.getProfil() != null){
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
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Cet utilisateur existe déjà!");
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    	}
    	else{
    		
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Votre nouveau login est: "+user.getUserUserName()+" et votre mot de passe est: "+user.getUserPassword());
    		FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
    		uService.updateUser(user);
    		route = "/views/"+MyUtil.getProfil()+"/home?faces-redirect=true";
    	}
    	}
    	else
    		route = "/login?faces-redirect=true";
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
