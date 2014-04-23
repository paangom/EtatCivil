/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import models.Centres;
import models.Users;

import org.primefaces.context.RequestContext;

import services.CentreServices;
import services.UserServices;
import util.MyUtil;


public class UserBean implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users userConnect = null;
    private Users userToCreate = null;
    private List<Users> users = null;
    private Centres c = null;
    private CentreServices cService = new CentreServices();
    private Users selectedUser = null;
    private UserServices uService = new UserServices();
    /**
     * Creates a new instance of userBean
     */
    public UserBean() {
        if(userToCreate == null){
            userToCreate = new Users();
        }
    }

    public Users getUserConnect() {
        userConnect = MyUtil.getUserLogged();
        return userConnect;
    }

    public List<Users> getUsers() {
        users = uService.getAllUser();
        return users;
    }
    
    
    

    public Centres getC() {
    	c = cService.getCentre();
		return c;
	}

	public void setC(Centres c) {
		this.c = c;
	}

	public Users getSelectedUser() {
        return selectedUser;
    }

    public Users getUserToCreate() {
        return userToCreate;
    }
    

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    
    
    @SuppressWarnings("unused")
	public void updateProfil() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String route = MyUtil.basePathLogin() + "login.xhtml";
        FacesMessage msg;
        boolean loggedIn;

        if (uService.updateUser(userConnect)) {
            loggedIn = false;
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            session.invalidate();
            context.addCallbackParam("isLogin", false);
            //context.addCallbackParam("route", route);
            String url = MyUtil.basePathLogin() +"login.xhtml";
            doRedirect(url);
        }
        else{
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de de modification de votre profil",null);
             FacesContext.getCurrentInstance().addMessage(null, msg);
             String url = MyUtil.basePathAdmin()+"profil/modifier_profil.xhtml";
             doRedirect(url);
            
        }
    }
    
    private void doRedirect(String url) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void btnCreateUser(){
        //UserDAO userDao = new UserDAOImp();
        String msg;
        
        this.userToCreate.setUserUserName("passer");
        this.userToCreate.setUserPassword("passer");
        this.userToCreate.setModify(false);
        this.userToCreate.setCentre(MyUtil.getUserLogged().getCentre());
       // this.userToCreate.setCreateurUser(this.userConnect.getUserId());
        
        if(uService.addUser(userToCreate)){
            msg = "L'utilisateur a été bien créé!";
            this.userToCreate = new Users();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Erreur de création de l'utilisateur";
             this.userToCreate = new Users();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    
    public void btnUpdateUser(){
        //this.selectedUser = (User) this.users.getRowData();
        String msg;
        if(uService.updateUser(selectedUser)){
            msg = "La modification de l'utilisateur s'est correctement passée.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Erreur de modification de l'utilisateur";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        
    }
    
    public void btnDeleteUser(){
        //UserDAO userDao = new UserDAOImp();
        String msg;
        //this.selectedUser.setId(3);
        if(uService.deleteUser(selectedUser)){
            msg = "La suppression de l'utilisateur s'est bien passée.";
        }else{
            msg = "Erreur de suppression de l'utilisateur";
        }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
