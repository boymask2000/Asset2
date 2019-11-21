package managed;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beans.ManualeFamiglia;
import beans.TypeManuale;
import common.ApplicationConfig;
import common.JsfUtil;
import database.dao.ManualiFamigliaDAO;
import filter.AuthFilter;

public class ManualeFamigliaUpload extends ABaseBean {

	private UploadedFile file;
	private ManualeFamiglia currentManuale;

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

		String dir = ApplicationConfig.getDocumentdir()+File.separator+"ManualiFamiglia";
		File fDir = new File(dir);
		if( !fDir.exists())fDir.mkdirs();
		
		try {
			File tmpFile = createTempFile("man_" + assetId + "_", ext, new File(dir));

			load(is, tmpFile);

			currentManuale.setNomeFile(tmpFile.getName());
		
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

		

			long familyId = assetBean.getFamilyId();
			currentManuale.setFamilyId(familyId);
			
			ManualiFamigliaDAO manualiDAO = new ManualiFamigliaDAO();
			manualiDAO.insert(currentManuale);
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		if (!PhaseId.INVOKE_APPLICATION.equals(event.getPhaseId())) {
	        event.setPhaseId(PhaseId.INVOKE_APPLICATION);
	        event.queue();return;
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

		

		long familyId = assetBean.getFamilyId();
		currentManuale.setFamilyId(familyId);
		
		
		currentManuale.setTypeManuale( TypeManuale.getType((int) currentManuale.getType()));
	

		ManualiFamigliaDAO manualiDAO = new ManualiFamigliaDAO();
		manualiDAO.insert(currentManuale);

	}

	public ManualeFamiglia getCurrentManuale() {
		if (currentManuale == null)
			currentManuale = new ManualeFamiglia();
		return currentManuale;
	}

	public void setCurrentManuale(ManualeFamiglia currentManuale) {
		this.currentManuale = currentManuale;
	}
}
