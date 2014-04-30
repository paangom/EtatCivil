/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import models.DeclarationDeces;
import models.DeclarationMariage;
import models.DeclarationNaissance;
import models.Search;
import services.ActeDecesServices;
import services.ActeMariageServices;
import services.ActeNaissanceServices;
import util.MyUtil;


public class SearchBean implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Search search = null;
    private List<DeclarationNaissance> resultN;
    private List<DeclarationDeces> resultDc = null;
    private List<DeclarationMariage> resultMar = null;
    private ActeDecesServices dcServ = new ActeDecesServices();
    private ActeMariageServices marServ = new ActeMariageServices();
    private ActeNaissanceServices naissServ = new ActeNaissanceServices();

    /**
     * Creates a new instance of searchBean
     */
    public SearchBean() {
        if(search == null){
            search = new Search();
        }
    }

    public Search getSearch() {
        return search;
    }
    
    public List<DeclarationNaissance> getResultN() {
        //resultN = naissServ.searchAN(search);
        return resultN;
    }

    public List<DeclarationDeces> getResultDc() {
        return resultDc;
    }

    public List<DeclarationMariage> getResultMar() {
        return resultMar;
    }

    public void setResultN(List<DeclarationNaissance> resultN) {
        this.resultN = resultN;
    }

    public void setResultDc(List<DeclarationDeces> resultDc) {
        this.resultDc = resultDc;
    }

    public void setResultMar(List<DeclarationMariage> resultMar) {
        this.resultMar = resultMar;
    }
    
    
    
    @SuppressWarnings("static-access")
	public String recherche(){
        String router = "";
        if(MyUtil.getProfil() != null){
        if("Naissance".equals(search.getType())){
        	
            this.setResultN(naissServ.searchAN(search));
            router = MyUtil.basePath()+"rechercheAN?faces-redirect=true";
        }
        else if("Mariage".equals(search.getType())){
            resultMar = marServ.searchAM(search);
            router = MyUtil.basePath()+"rechercheAM?faces-redirect=true";
        }
        else if("Décès".equals(search.getType())){
            resultDc = dcServ.searchAD(search);
            router = MyUtil.basePath()+"rechercheAD?faces-redirect=true";
        }
        else{
        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Veuillez choisir le type d'acte à rechercher.", null);
        	FacesContext context = FacesContext.getCurrentInstance();
            context.getCurrentInstance().addMessage(null, message);
        	
        	context.getExternalContext().getFlash().setKeepMessages(true);
        }
        return router;
        }
        else return MyUtil.pathLogin();
    }

    
}
