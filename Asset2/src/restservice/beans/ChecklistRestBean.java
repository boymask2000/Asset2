package restservice.beans;

import java.io.Serializable;
import java.util.List;

import beans.Asset;
import beans.Checklist;

public class ChecklistRestBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Asset asset;
	private List<Checklist> lista;
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public List<Checklist> getLista() {
		return lista;
	}
	public void setLista(List<Checklist> lista) {
		this.lista = lista;
	}
}
