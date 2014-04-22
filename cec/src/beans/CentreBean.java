package beans;

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
        System.out.println(centreToUpdate.getCenterName());
        return centreToUpdate;
    }

   public void configureCentre(ActionListener event){
       
   }
   
   public String updateConfigurationCentre(){
       
        if (cenServ.modifyCenter(centreToUpdate)) {
            return MyUtil.basePathAdmin()+"centre/informations?faces-redirect=true";
        }
        else
            return MyUtil.basePathAdmin()+"centre/modifier_information?faces-redirect=true";
        
        
   }

}
