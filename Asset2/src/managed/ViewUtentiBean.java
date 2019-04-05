package managed;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import beans.Utente;
import common.Log;

@ManagedBean(name="dtSelectionView")
@SessionScoped
public class ViewUtentiBean implements Serializable{
	
	private static final long serialVersionUID = -1060598718658473231L;
	
	private Utente selectedUser ;

	public Utente getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Utente selectedUser) {
		this.selectedUser = selectedUser;
		Log.getLogger().debug("setSelectedUser");
		System.out.println("setSelectedUser");
	}
	
	 public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage(" Selected", ((Utente) event.getObject()).getUsername());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        Log.getLogger().debug("select");
	        System.out.println("onRowSelect");
	    
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	        FacesMessage msg = new FacesMessage(" Unselected", ((Utente) event.getObject()).getUsername());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        Log.getLogger().debug("unselect");
	    }
}
