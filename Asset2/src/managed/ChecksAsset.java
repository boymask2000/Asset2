package managed;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import beans.Check;
import beans.CheckAsset;
import common.JsfUtil;
import database.dao.ChecksAssetDAO;

public class ChecksAsset {
	private List<CheckAsset> allChecksForAsset;
	private CheckAsset selectedCheck;
	private CheckAsset toDelete;

	@PostConstruct
	private void init() {
		selectedCheck = new CheckAsset();
	}

	public List<CheckAsset> getAllChecksForAsset() {

		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");

		ChecksAssetDAO dao = new ChecksAssetDAO();
		return dao.getChecksByAssetId(bean.getSelectedAsset().getId());
	}

	public void setAllChecksForAsset(List<CheckAsset> allChecksForAsset) {
		this.allChecksForAsset = allChecksForAsset;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", "" + ((Check) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		CheckAsset saf = (CheckAsset) event.getObject();

		ChecksAssetDAO dao = new ChecksAssetDAO();
		dao.update(selectedCheck);
	}

	public void split() {
		List<String> descrIt = new ArrayList<String>();
		List<String> descrUs = new ArrayList<String>();
		System.out.println(selectedCheck.getDescription());
	String[] jj = selectedCheck.getDescription().split("\n");
	System.out.println(jj.length);
	for(int i=0; i<jj.length; i++)System.out.println(jj[i]);

		InputStream is = new ByteArrayInputStream(selectedCheck.getDescription().getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while ((line = br.readLine()) != null) {
				if (line.trim().length() > 0)
					descrIt.add(line);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		InputStream is1 = new ByteArrayInputStream(selectedCheck.getDescriptionUS().getBytes());
		BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));

		try {
			while ((line = br1.readLine()) != null) {
				if (line.trim().length() > 0)
					descrUs.add(line);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (descrIt.size() != descrUs.size())
			return;

		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		ChecksAssetDAO dao = new ChecksAssetDAO();
		selectedCheck.setAssetId(bean.getSelectedAsset().getId());

		for (int i = 0; i < descrIt.size(); i++) {
			selectedCheck.setDescription(descrIt.get(i));
			selectedCheck.setDescriptionUS(descrUs.get(i));
			dao.insert(selectedCheck);
			JsfUtil.showMessage("Inserimento eseguito");
		}
	}

	public void insertCheck() {
		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		ChecksAssetDAO dao = new ChecksAssetDAO();
		selectedCheck.setAssetId(bean.getSelectedAsset().getId());
		dao.insert(selectedCheck);
		JsfUtil.showMessage("Inserimento eseguito");
	}

	public String delete() {
		ChecksAssetDAO dao = new ChecksAssetDAO();
		dao.delete(toDelete);

		return null;
	}

	public CheckAsset getSelectedCheck() {
		return selectedCheck;
	}

	public void setSelectedCheck(CheckAsset selectedCheck) {
		this.selectedCheck = selectedCheck;
	}

	public CheckAsset getToDelete() {
		return toDelete;
	}

	public void setToDelete(CheckAsset toDelete) {
		this.toDelete = toDelete;
	}
}
