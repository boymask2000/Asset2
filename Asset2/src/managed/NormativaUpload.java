package managed;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beans.Manuale;
import beans.Normativa;
import common.ApplicationConfig;
import common.JsfUtil;
import database.dao.ManualiDAO;
import database.dao.NormativeDAO;

public class NormativaUpload extends ABaseBean {

	private UploadedFile file;
	private Manuale currentManuale;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
		String fileName = file.getFileName();

		
		try (InputStream inputStream = file.getInputstream();) {

			loadFile(fileName, inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadFile(String fileName, InputStream is) {

		String ext = getExt(fileName);

		String dir = ApplicationConfig.getDocumentdir() + File.separator + "Normative";
		try {
			File tmpFile = createTempFile("norm_", ext, new File(dir));

			load(is, tmpFile);

			ManagedNormativeBean mnb = (ManagedNormativeBean) JsfUtil.getBean("managedNormativaBean");
			Normativa norm = mnb.getSelectedNormativa();
			norm.setFilename(tmpFile.getName());
			NormativeDAO dao = new NormativeDAO();
			dao.insert(norm);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void upload() {
	
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			ManagedAssetBean assetBean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");

			long assetId = assetBean.getSelectedAsset().getId();
			currentManuale.setAssetId(assetId);
			

			ManualiDAO manualiDAO = new ManualiDAO();
			manualiDAO.insert(currentManuale);
		}
	}


	public void handleFileUpload(FileUploadEvent event) {
		String fileName = event.getFile().getFileName();
		// fileName = getFullPath(fileName);

		
		try (InputStream inputStream = event.getFile().getInputstream();) {

			loadFile(fileName, inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}

		FacesMessage message = new FacesMessage("Succesful", fileName + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

//		ManagedAssetBean assetBean = getManagedAssetBean();
//
//		System.out.println(currentManuale.getDescrizione());
//		System.out.println(currentManuale.getNomefile());
//
//		long assetId = assetBean.getSelectedAsset().getId();
//		currentManuale.setAssetId(assetId);
//		System.out.println(assetId);
//
//		ManualiDAO manualiDAO = new ManualiDAO();
//		manualiDAO.insert(currentManuale);

	}

	public Manuale getCurrentManuale() {
		if (currentManuale == null)
			currentManuale = new Manuale();
		return currentManuale;
	}

	public void setCurrentManuale(Manuale currentManuale) {
		this.currentManuale = currentManuale;
	}
}
