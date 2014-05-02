package beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;

import models.Centres;
import services.CentreServices;
import util.MyUtil;

public class CentreBean {

	private CentreServices cenServ = new CentreServices();
    private Centres centreToAdd = null;
    private Centres centreToUpdate = null;
    
    /**
     * Creates a new instance of centreBean
     */
    public CentreBean() {
        if(centreToAdd == null){
            centreToAdd = new Centres();
        }
       
    }
    
    

    public Centres getCentreToAdd() {
        return centreToAdd;
    }

    public Centres getCentreToUpdate() {
        centreToUpdate = cenServ.getCentre();
        return centreToUpdate;
    }

   public void configureCentre(ActionListener event){
       
   }
   
   public String updateConfigurationCentre(){
       	if(MyUtil.getProfil() != null){
        if (cenServ.modifyCenter(centreToUpdate)) {
            return MyUtil.basePathAdmin()+"centre/informations?faces-redirect=true";
        }
        else
            return MyUtil.basePathAdmin()+"centre/modifier_information?faces-redirect=true";
        
       	}
       	else
       		return "/views/login?faces-redirect=true";
   }
   
   @SuppressWarnings("static-access")
public String saveCentre(){
	   String route = "";
	   if(this.cenServ.viderCentre()){
		   if(cenServ.createCenter(this.centreToAdd)){
			   
			   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Initialisation du centre effectué avec succès");
               FacesContext context = FacesContext.getCurrentInstance();
               context.getCurrentInstance().addMessage(null, message);
               route = "/views/install/root?faces-redirect=true";
		   }
		   else{
			   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Impossible de créer le centre. Veuillez vérifier les informations.");
               FacesContext context = FacesContext.getCurrentInstance();
               context.getCurrentInstance().addMessage(null, message);
           	
           	context.getExternalContext().getFlash().setKeepMessages(true);
		   }
	   }
	   else{
		   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Impossible d'initialiser le centre.");
           FacesContext context = FacesContext.getCurrentInstance();
           context.getCurrentInstance().addMessage(null, message);
       	
       	context.getExternalContext().getFlash().setKeepMessages(true);
	   }
	   
	   return route;
	   
   }

}
