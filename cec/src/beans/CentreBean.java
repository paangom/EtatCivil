package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionListener;

import models.Centres;
import services.CentreServices;
import util.MyUtil;
import util.Tools;

public class CentreBean {

	private CentreServices cenServ = new CentreServices();
    private Centres centreToAdd = null;
    private Centres centreToUpdate = null;
    private List<String> selectedAnnees;
    private List<String> annees;
    /**
     * Creates a new instance of centreBean
     */
    public CentreBean() {
        if(centreToAdd == null){
            centreToAdd = new Centres();
        }
        annees= new ArrayList<String>(); 
        int a = Integer.parseInt(cenServ.getCentre().getAnneeRegistre());
		
		for(int i = anneeCourant ; i >= a; i--){
			annees.add(i+""); 
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
   
   
   

	public List<String> getAnnees() {
	return annees;
}



public void setAnnees(List<String> annees) {
	this.annees = annees;
}



	/**
 * @return the selectedAnnees
 */
public List<String> getSelectedAnnees() {
	return selectedAnnees;
}



/**
 * @param selectedAnnees the selectedAnnees to set
 */
public void setSelectedAnnees(List<String> selectedAnnees) {
	this.selectedAnnees = selectedAnnees;
}




	private int anneeCourant = Integer.parseInt(Tools.getCurrentDate().substring(6, 10));

}
