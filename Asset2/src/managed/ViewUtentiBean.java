package managed;

import java.io.Serializable;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import beans.Utente;
import common.Log;


public class ViewUtentiBean implements Serializable{
	
	private static final long serialVersionUID = -1060598718658473231L;
	
	private Utente selectedUser =new Utente();

	public Utente getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Utente selectedUser) {
		this.selectedUser = selectedUser;
		Log.getLogger().debug("setSelectedUser");
		
	}
	
	 public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        Log.getLogger().debug("select");
	       
	    
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	        FacesMessage msg = new FacesMessage(" Unselected", ((Utente) event.getObject()).getUsername());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        Log.getLogger().debug("unselect");
	    }
}
