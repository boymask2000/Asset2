package managed;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.AssetAlca;
import beans.Intervento;
import common.JsfUtil;
import common.TimeUtil;
import database.dao.AssetAlcaDAO;
import database.dao.InterventiDAO;

public class ManagedInterventiBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Intervento> myList;
	//
	private Date date_data_effettiva;

	private Date dataNuovoIntervento;

	private Intervento selectedIntevento = new Intervento();
	private int esito;

	private String selectedDataForSituation;
	private AssetAlca asset=null;
	
	public List<Intervento> getInterventiFromTo(String dateFrom, String dateTo){

		if (dateFrom == null || dateFrom.trim().equalsIgnoreCase(""))
			dateFrom = TimeUtil.getCurrentDate(new Date());
		if (dateTo == null || dateTo.trim().equalsIgnoreCase(""))
			dateTo = TimeUtil.getCurrentDate(new Date());
		
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ll = dao.getInterventiFromTo(dateFrom,dateTo);
		return ll;
	}
	
	
	public List<Intervento> getInterventiInData(String data) {

		if (data == null || data.trim().equalsIgnoreCase(""))
			data = TimeUtil.getCurrentDate(new Date());
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ll = dao.getInterventiInData(data);
		return ll;
	}

	public List<Intervento> getInterventiPerData() {

		if (selectedDataForSituation == null || selectedDataForSituation.trim().equalsIgnoreCase(""))
			selectedDataForSituation = TimeUtil.getCurrentDate(new Date());
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ll = dao.getInterventiInData(selectedDataForSituation);
		return ll;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dd = format.format(event.getObject());
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		setSelectedDataForSituation(dd);
	}

	public List<Intervento> getAllInterventi() {
		InterventiDAO dao = new InterventiDAO();
		myList = dao.selectAll();
		return myList;
	}

	public void update(Intervento u) {
	
		InterventiDAO dao = new InterventiDAO();
		
		dao.update(u);
	}

	public List<Intervento> getInterventiForAsset(int assetId, boolean done) {
		InterventiDAO dao = new InterventiDAO();
		myList = dao.getInterventiForAsset(assetId, done);
		return myList;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((Intervento) event.getObject()).getId());
		selectedIntevento = (Intervento) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void insertManuale(Intervento in) {

		InterventiDAO dao = new InterventiDAO();

		try {
			dao.insert(in);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Intervento getSelectedIntevento() {

		return selectedIntevento;
	}

	public void setSelectedIntevento(Intervento s) {
		if (s == null)
			return;

		esito = s.getEsito();
		this.selectedIntevento = s;

		date_data_effettiva = TimeUtil.getCurrentStringDate(s.getData_effettiva());
	}

	public Date getDate_data_effettiva() {
		return date_data_effettiva;
	}

	public void nuovoIntervento() {
		ManagedAssetBean managedAssetBean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		long id = managedAssetBean.getSelectedAsset().getId();

		selectedIntevento.setAssetId(id);
		InterventiDAO dao = new InterventiDAO();
		dao.insert(selectedIntevento);
	}

	public void setDate_data_effettiva(Date d) {

		if (d == null)
			d = new Date();
		this.date_data_effettiva = d;

		selectedIntevento.setData_effettiva(TimeUtil.getCurrentDate(d));
	}

	public Date getDataNuovoIntervento() {
		return dataNuovoIntervento;
	}

	public void setDataNuovoIntervento(Date d) {

		if (d == null)
			d = new Date();
		this.dataNuovoIntervento = d;

		selectedIntevento.setData_pianificata(TimeUtil.getCurrentDate(d));
		selectedIntevento.setData_effettiva(null);
		selectedIntevento.setEsito(0);
	}

	public void setEsito(int esito) {
		this.esito = esito;

		selectedIntevento.setEsito(esito);
		InterventiDAO dao = new InterventiDAO();

		try {
			dao.update(selectedIntevento);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (dao.isLastIntervento(selectedIntevento.getData_effettiva())) {
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			ManagedAssetBean assetBean = application.evaluateExpressionGet(context, "#{managedAssetBean}",
					ManagedAssetBean.class);
			assetBean.getSelectedAsset().setLastStatus("" + esito);
			AssetAlcaDAO assetDao = new AssetAlcaDAO();
			assetDao.update(assetBean.getSelectedAsset());
		}
	}

	public int getEsito() {
		return esito;
	}

	public String getSelectedDataForSituation() {
		return selectedDataForSituation;
	}

	public void setSelectedDataForSituation(String s) {

		this.selectedDataForSituation = s;
	}

	public AssetAlca getAsset() {
		ManagedAssetBean managedAssetBean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		return managedAssetBean.getSelectedAsset();
	}

	public void setAsset(AssetAlca asset) {
		this.asset = asset;
	}

}
