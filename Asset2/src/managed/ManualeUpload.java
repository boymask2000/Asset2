package managed;

import java.io.File;
import java.io.FileInputStream;
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

		System.out.println("fleName: " + file.getFileName());
		try (InputStream inputStream = file.getInputstream();) {

			loadFile(fileName, inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getExt(String fileName) {
		String ext = "";
		int pos = fileName.lastIndexOf(".");
		if (pos != -1)
			ext = fileName.substring(pos);
		return ext;
	}

	private void loadFile(String fileName, InputStream is) {
		byte buffer[] = new byte[1024];
		ManagedAssetBean assetBean = getManagedAssetBean();
		long assetId = assetBean.getSelectedAsset().getId();
		String ext = getExt(fileName);

		String dir = ApplicationConfig.getDocumentdir();
		try {
			File tmpFile = File.createTempFile("man_" + assetId + "_", ext, new File(dir));

			try (FileOutputStream os = new FileOutputStream(tmpFile);) {
				int count = 0;
				while ((count = is.read(buffer)) != -1) {
					os.write(buffer, 0, count);
				}
				os.flush();

				currentManuale.setNomefile(tmpFile.getName());
				currentManuale.setExt(ext);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void upload() {
		System.out.println("upload file=" + file);
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			ManagedAssetBean assetBean = getManagedAssetBean();

			System.out.println(currentManuale.getDescrizione());
			System.out.println(currentManuale.getNomefile());

			long assetId = assetBean.getSelectedAsset().getId();
			currentManuale.setAssetId(assetId);
			System.out.println(assetId);

			ManualiDAO manualiDAO = new ManualiDAO();
			manualiDAO.insert(currentManuale);
		}
	}

	private ManagedAssetBean getManagedAssetBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ManagedAssetBean assetBean = application.evaluateExpressionGet(context, "#{managedAssetBean}",
				ManagedAssetBean.class);
		return assetBean;
	}

	public void handleFileUpload(FileUploadEvent event) {
		String fileName = event.getFile().getFileName();
	//	fileName = getFullPath(fileName);

		System.out.println("fleName: " + fileName);
		try (InputStream inputStream = event.getFile().getInputstream();) {

			loadFile(fileName, inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}

		FacesMessage message = new FacesMessage("Succesful", fileName + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

		ManagedAssetBean assetBean = getManagedAssetBean();

		System.out.println(currentManuale.getDescrizione());
		System.out.println(currentManuale.getNomefile());

		long assetId = assetBean.getSelectedAsset().getId();
		currentManuale.setAssetId(assetId);
		System.out.println(assetId);

		ManualiDAO manualiDAO = new ManualiDAO();
		manualiDAO.insert(currentManuale);

	}

	private String getFullPath(String nome) {
		String dir = ApplicationConfig.getDocumentdir();
		if (!dir.endsWith(File.separator))
			dir += File.separator;
		return dir + nome;
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
