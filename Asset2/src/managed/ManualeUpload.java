package managed;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beans.Manuale;
import common.ApplicationConfig;
import common.JsfUtil;
import database.dao.ManualiDAO;
import filter.AuthFilter;

public class ManualeUpload extends ABaseBean {

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

		ManagedAssetBean assetBean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		long assetId = assetBean.getSelectedAsset().getId();
		String ext = getExt(fileName);

		String dir = ApplicationConfig.getDocumentdir()+File.separator+"Manuali";
		try {
			File tmpFile = createTempFile("man_" + assetId + "_", ext, new File(dir));

			load(is, tmpFile);

			currentManuale.setNomeFile(tmpFile.getName());
			currentManuale.setExt(ext);
			
			AuthFilter.copyDocsInt();

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
		if (!PhaseId.INVOKE_APPLICATION.equals(event.getPhaseId())) {
	        event.setPhaseId(PhaseId.INVOKE_APPLICATION);
	        event.queue();
	        return;
	    } 
		String fileName = event.getFile().getFileName();

		
		try (InputStream inputStream = event.getFile().getInputstream();) {

			loadFile(fileName, inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}

		FacesMessage message = new FacesMessage("Succesful", fileName + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

		ManagedAssetBean assetBean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");

		

		long assetId = assetBean.getSelectedAsset().getId();
		currentManuale.setAssetId(assetId);
	

		ManualiDAO manualiDAO = new ManualiDAO();
		manualiDAO.insert(currentManuale);

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
