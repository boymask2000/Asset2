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

import beans.DocIntervento;
import common.ApplicationConfig;
import database.dao.DocInterventiDAO;

public class DocInterventoUpload {

	private UploadedFile file;
	private DocIntervento currentDoc;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
		String fileName = file.getFileName();

		System.out.println("fleName: " + file.getFileName());
		try (InputStream inputStream = file.getInputstream();) {

			loadFile(fileName, inputStream,0);

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

	private void loadFile(String fileName, InputStream is, long interventoId) {
		
		ManagedDocInterventiBean assetBean = getManagedDocInterventiBean();
		//long assetId = assetBean.getSelectedDocIntervento().getId();
		
		String ext = getExt(fileName);

		String dir = ApplicationConfig.getDocumentdir();
		try {
			File tmpFile = File.createTempFile("doc_" + interventoId + "_", ext, new File(dir));

			try (FileOutputStream os = new FileOutputStream(tmpFile);) {
				byte buffer[] = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) != -1) {
					os.write(buffer, 0, count);
				}
				os.flush();

				currentDoc.setFilename(tmpFile.getName());
				currentDoc.setExt(ext);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void upload() {
		System.out.println("upload file=" + file);
//		if (file != null) {
//			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//
//			ManagedAssetBean assetBean = getManagedAssetBean();
//
//			System.out.println(currentDoc.getDescrizione());
//			System.out.println(currentDoc.getFilename());
//
//			long assetId = assetBean.getSelectedAsset().getId();
//			currentDoc.setAssetId(assetId);
//			System.out.println(assetId);
//
//			ManualiDAO manualiDAO = new ManualiDAO();
//			manualiDAO.insert(currentDoc);
//		}
	}

	private ManagedDocInterventiBean getManagedDocInterventiBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ManagedDocInterventiBean assetBean = application.evaluateExpressionGet(context, "#{managedDocInterventiBean}",
				ManagedDocInterventiBean.class);
		return assetBean;
	}

	public void handleFileUpload(FileUploadEvent event) {
		long interventoId = (long) event.getComponent().getAttributes().get("intId");
	
		String fileName = event.getFile().getFileName();
	//	fileName = getFullPath(fileName);

		System.out.println("fleName: " + fileName);
		try (InputStream inputStream = event.getFile().getInputstream();) {

			loadFile(fileName, inputStream, interventoId);

		} catch (IOException e) {
			e.printStackTrace();
		}

		FacesMessage message = new FacesMessage("Succesful", fileName + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

		ManagedDocInterventiBean assetBean = getManagedDocInterventiBean();

		System.out.println(currentDoc.getDescrizione());
		System.out.println(currentDoc.getFilename());

	//	long assetId = assetBean.getSelectedDocIntervento().getId();
		currentDoc.setInterventoId(interventoId);
	
	

		DocInterventiDAO dao = new DocInterventiDAO();
		dao.insert(currentDoc);

	}

//	private String getFullPath(String nome) {
//		String dir = ApplicationConfig.getDocumentdir();
//		if (!dir.endsWith(File.separator))
//			dir += File.separator;
//		return dir + nome;
//	}



	public DocIntervento getCurrentDoc() {
		if (currentDoc == null)
			currentDoc = new DocIntervento();
		return currentDoc;
	}

	public void setCurrentDoc(DocIntervento currentDoc) {
		this.currentDoc = currentDoc;
	}
}
