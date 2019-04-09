package managed;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import beans.Manuale;
import common.ApplicationConfig;
import database.dao.ManualiDAO;

public class ManualeUpload {

	private UploadedFile file;
	private Manuale currentManuale;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
		String fileName = file.getFileName();
		String ext = "";
		int pos = fileName.lastIndexOf(".");
		if (pos != -1)
			ext = fileName.substring(pos);

		System.out.println("fleName: " + file.getFileName());
		try (InputStream inputStream = file.getInputstream();) {

			String dir = ApplicationConfig.getDocumentdir();
			File tmpFile = File.createTempFile("man_", ext, new File(dir));
			loadFile(inputStream, tmpFile);
			
			currentManuale.setNomefile(tmpFile.getName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadFile(InputStream is, File tmpFile) {
		byte buffer[] = new byte[1024];
		try (FileOutputStream os = new FileOutputStream(tmpFile);) {
			int count = 0;
			while ((count = is.read(buffer)) != -1) {
				os.write(buffer, 0, count);
			}
			os.flush();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void upload() {
		System.out.println("upload file=" + file);
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			
			FacesContext context = FacesContext.getCurrentInstance();
			Application application = context.getApplication();
			ManagedAssetBean assetBean = application.evaluateExpressionGet(context, "#{managedAssetBean}", ManagedAssetBean.class);
		
			System.out.println(currentManuale.getDescrizione());
			System.out.println(currentManuale.getNomefile());
			
			long assetId = assetBean.getSelectedAsset().getId();
			currentManuale.setAssetId(assetId);
			System.out.println(assetId);
			
			ManualiDAO manualiDAO =new ManualiDAO();
			manualiDAO.insert(currentManuale);
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println("handleFileUpload");
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		String foo = (String) event.getComponent().getAttributes().get("assetId");
		System.out.println("foo:"+foo);
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
