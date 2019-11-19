package excel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.JsfUtil;
import managed.FileUploadView;

public class ExcelColumnManagedBean {

	private List<ColumnsItem> colItems = new ArrayList<ColumnsItem>();
	private String error;

	public void setColonne(List<String> colonne) {
		colItems.clear();
		int ncol = 1;

		for (String t : colonne) {
			ColumnsItem item = new ColumnsItem();
			item.setTitle(t);
			item.setNum(ncol++);
			colItems.add(item);
		}
	}

	public List<ColumnsItem> getColItems() {
		return colItems;
	}

	public void setColItems(List<ColumnsItem> colItems) {
		this.colItems = colItems;
	}

	public String check() {
		error = null;
		boolean keyIsPresent = false;
		boolean familyIsPresent = false;

		Set<Integer> nums = new HashSet<Integer>();
		for (ColumnsItem item : colItems) {
		//	System.out.println(item.getTitle() + "   " + item.getNum());
			if (nums.contains(item.getNum())) {
				error = "No duplicate items";
				break;
			}
			if (item.getNum() != 0)
				nums.add(item.getNum());
			if (item.getNum() == 11)
				keyIsPresent = true;
			if (item.getNum() == 3)
				familyIsPresent = true;
		}
		if (error == null) {
			if (!keyIsPresent)
				error = "Field RpieIdIndividual must be matched";
			if (!familyIsPresent)
				error = "Field FacSubSystem must be matched";
		}
		if (error == null) {
			FileUploadView fileUploadView = (FileUploadView) JsfUtil.getBean("fileUploadView");
			fileUploadView.readFile2(colItems);
			JsfUtil.showMessage("File in caricamento");
		}
		return "adminHome";
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
