package managed;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Intervento;
import common.Log;
import common.TimeUtil;
import database.dao.AssetDAO;
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

	public List<Intervento> getInterventiInData(String data) {
		System.out.println("getInterventiInData "+data);
		if (data == null || data.trim().equalsIgnoreCase(""))
			data = TimeUtil.getCurrentDate(new Date());
		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ll = dao.getInterventiInData(data);
		return ll;
	}
	public void onDateSelect(SelectEvent event) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    String dd = format.format(event.getObject());
	    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
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
		Log.getLogger().debug("select");
		System.out.println("row select id=" + selectedIntevento.getId());

	}

	public void insertManuale(Intervento in) {
		System.out.println("insert");

		InterventiDAO dao = new InterventiDAO();

		try {
			dao.insert(in);
		} catch (Throwable e) {
			System.out.println("lllllllllllllllllllllllll");
		}
	}

	private BasicDocumentViewController getDocController() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		BasicDocumentViewController assetBean = application.evaluateExpressionGet(context,
				"#{basicDocumentViewController}", BasicDocumentViewController.class);
		return assetBean;
	}

	public Intervento getSelectedIntevento() {

		return selectedIntevento;
	}

	public void setSelectedIntevento(Intervento s) {
		if (s == null)
			return;
		System.out.println("setSelectedIntevento idInter= " + s.getId());
		esito = s.getEsito();
		this.selectedIntevento = s;

		date_data_effettiva = TimeUtil.getCurrentStringDate(s.getData_effettiva());
	}

	public Date getDate_data_effettiva() {
		return date_data_effettiva;
	}

	public void nuovoIntervento() {
		System.out.println("niovooo");
		InterventiDAO dao = new InterventiDAO();
		dao.insert(selectedIntevento);
	}

	public void setDate_data_effettiva(Date d) {
		System.out.println("set data: " + d);
		if (d == null)
			d = new Date();
		this.date_data_effettiva = d;

		selectedIntevento.setData_effettiva(TimeUtil.getCurrentDate(d));
	}

	public Date getDataNuovoIntervento() {
		return dataNuovoIntervento;
	}

	public void setDataNuovoIntervento(Date d) {

		System.out.println("set data: " + d);
		if (d == null)
			d = new Date();
		this.dataNuovoIntervento = d;

		selectedIntevento.setData_pianificata(TimeUtil.getCurrentDate(d));
		selectedIntevento.setData_effettiva(null);
		selectedIntevento.setEsito(0);
	}

	public void setEsito(int esito) {
		this.esito = esito;
		System.out.println("esito :" + esito);
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
			assetBean.getSelectedAsset().setLastStatus(esito);
			AssetDAO assetDao = new AssetDAO();
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
		System.out.println(s);
		this.selectedDataForSituation = s;
	}

}
