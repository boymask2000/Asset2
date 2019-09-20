package managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import beans.FamigliaAsset;
import beans.Safety;
import common.JsfUtil;
import database.dao.SafetyDAO;

public class ManagedSafetyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Safety selectedSafety = new Safety();

	private FamigliaAsset famiglia;
	private int selectedImageNum = 0;

	private boolean selected1;
	private boolean selected2;
	private boolean selected3;
	private boolean selected4;
	private boolean selected5;
	private boolean selected6;
	private boolean selected7;
	private boolean selected8;
	private boolean selected9;
	private boolean selected10;
	private boolean selected11;
	private boolean selected12;

	public List<Safety> getAllSafety() {

		SafetyDAO dao = new SafetyDAO();
		List<Safety> myList = dao.selectAll();
		return myList;
	}

	private List<Integer> images = new ArrayList<Integer>();

	public void setImg(int id) {
		System.out.println(id);
	}

	List<Safety> myList = null;

	public List<Safety> getAllSafetyForFamily() {
		SafetyDAO dao = new SafetyDAO();

		if (myList == null)
			if (famiglia != null)
				myList = dao.selectByFamily(famiglia.getId());
			else
				myList = getAllSafety();
		return myList;
	}

	public void resetSelezione() {
		selectedSafety = null;
	}

//
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage(" Selected", "" + ((Safety) event.getObject()).getId());
		selectedSafety = (Safety) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void saveSafety() {
		ManagedFamiglieAssetBean mfab = (ManagedFamiglieAssetBean) JsfUtil.getBean("managedFamiglieAssetBean");
		getSelectedSafety().setFamilyid(mfab.getSelectedFamiglia().getId());
		SafetyDAO dao = new SafetyDAO();
		dao.save(selectedSafety);
	}

	public void insertSafety() {

		SafetyDAO dao = new SafetyDAO();

		try {
			selectedSafety.setFamilyid(famiglia.getId());
			dao.insert(selectedSafety);
			JsfUtil.showMessage("Safety inserita");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public void onCellEdit(CellEditEvent event) {
		String oldValue = (String) event.getOldValue();
		Object newValue = event.getNewValue();

		DataTable table = (DataTable) event.getSource();
		Safety saf = (Safety) table.getRowData();

		SafetyDAO dao = new SafetyDAO();
		dao.update(saf);
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
//				"Old: " + oldValue + ", New:" + newValue);
//		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public Safety getSelectedSafety() {

		return selectedSafety;

	}

	public void setSelectedSafety(Safety s) {
		if (s == null)
			return;

		this.selectedSafety = s;
	}

	public FamigliaAsset getFamiglia() {
		return famiglia;
	}

	public void setFamiglia(FamigliaAsset famiglia) {
		this.famiglia = famiglia;
		myList = null;
	}

	public List<Integer> getImages() {
		images.clear();
		for (int i = 1; i < 13; i++)
			images.add(i);
		return images;
	}

	public void setImages(List<Integer> images) {
		this.images = images;
	}

	public int getSelectedImageNum() {
		return selectedImageNum;
	}

	public void setSelectedImageNum(int selectedImageNum) {
		this.selectedImageNum = selectedImageNum;
		System.out.println(selectedImageNum);
	}

	public boolean isSelected(int v) {
		System.out.println(v + " " + (v == selectedImageNum));
		return v == selectedImageNum;
	}

	public boolean isSelected1() {
		return selected1;
	}

	public void setSelected1(boolean selected1) {
		if (!selected1)
			return;
		cleanSelectedNum();
		this.selected1 = selected1;
		selectedSafety.setImgId(1);
	}

	public boolean isSelected2() {
		return selected2;
	}

	public void setSelected2(boolean selected2) {
		if (!selected2)
			return;
		cleanSelectedNum();
		this.selected2 = selected2;
		selectedSafety.setImgId(2);
	}

	public String getRiskLabel(int n) {
		switch (n) {
		case 1:
			return "FALL DOWN";
		case 2:
			return "FOOT IMPACTS OR CRUSHING";
		case 3:
			return "HANDS IMPACTS OR CRUSHING";
		case 4:
			return "EYES INJURY";
		case 5:
			return "FACE INJURY";
		case 6:
			return "HEAD INJURY";
		case 7:
			return "ELECTRICAL SHOCK";
		case 8:
			return "CHEMICAL RISK";
		case 9:
			return "CHEMICAL RISK";
		case 10:
			return "CHEMICAL RISK";
		case 11:
			return "CHEMICAL RISK";
		case 12:
			return "CHEMICAL RISK";
		default:
			break;
		}
		return "";
	}

	public String getPPELabel(int n) {
		switch (n) {
		case 1:
			return "SAFETY BELT";
		case 2:
			return "SAFETY SHOES";
		case 3:
			return "SAFETY SHOVELS";
		case 4:
			return "SAFETY GLASSES";
		case 5:
			return "SAFETY SHIELD";
		case 6:
			return "SAFETY HELMET";
		case 7:
			return "ELECTRIC GENERAL SWITCH";
		case 8:
			return "SAFETY GLOVES";
		case 9:
			return "SAFETY CLOTHING";
		case 10:
			return "SAFETY GLASSES";
		case 11:
			return "SAFETY SHIELD";
		case 12:
			return "SAFETY MASK";
		default:
			break;
		}
		return "";
	}

	private void cleanSelectedNum() {

		selected1 = false;
		selected2 = false;
		selected3 = false;
		selected4 = false;
		selected5 = false;
		selected6 = false;
		selected7 = false;
		selected8 = false;
		selected9 = false;
		selected10 = false;
		selected11 = false;
		selected12 = false;

	}

	public boolean isSelected3() {
		return selected3;
	}

	public void setSelected3(boolean selected3) {
		if (!selected3)
			return;
		cleanSelectedNum();
		this.selected3 = selected3;
		selectedSafety.setImgId(3);
	}

	public boolean isSelected4() {
		return selected4;
	}

	public void setSelected4(boolean selected4) {
		if (!selected4)
			return;
		cleanSelectedNum();
		this.selected4 = selected4;
		selectedSafety.setImgId(4);
	}

	public boolean isSelected5() {
		return selected5;
	}

	public void setSelected5(boolean selected5) {
		if (!selected5)
			return;
		cleanSelectedNum();
		this.selected5 = selected5;
		selectedSafety.setImgId(5);
	}

	public boolean isSelected6() {
		return selected6;
	}

	public void setSelected6(boolean selected6) {
		if (!selected6)
			return;
		cleanSelectedNum();
		this.selected6 = selected6;
		selectedSafety.setImgId(6);
	}

	public boolean isSelected7() {
		return selected7;
	}

	public void setSelected7(boolean selected7) {
		if (!selected7)
			return;
		cleanSelectedNum();
		this.selected7 = selected7;
		selectedSafety.setImgId(7);
	}

	public boolean isSelected8() {
		return selected8;
	}

	public void setSelected8(boolean selected8) {
		if (!selected8)
			return;
		cleanSelectedNum();
		this.selected8 = selected8;
		selectedSafety.setImgId(8);
	}

	public boolean isSelected9() {
		return selected9;
	}

	public void setSelected9(boolean selected9) {
		if (!selected9)
			return;
		cleanSelectedNum();
		this.selected9 = selected9;
		selectedSafety.setImgId(9);
	}

	public boolean isSelected10() {
		return selected10;
	}

	public void setSelected10(boolean selected10) {
		if (!selected10)
			return;
		cleanSelectedNum();
		this.selected10 = selected10;
		selectedSafety.setImgId(10);
	}

	public boolean isSelected11() {
		return selected11;
	}

	public void setSelected11(boolean selected11) {
		if (!selected11)
			return;
		cleanSelectedNum();
		this.selected11 = selected11;
		selectedSafety.setImgId(11);
	}

	public boolean isSelected12() {
		return selected12;
	}

	public void setSelected12(boolean selected12) {
		if (!selected12)
			return;
		cleanSelectedNum();
		this.selected12 = selected12;
		selectedSafety.setImgId(12);
	}

}
