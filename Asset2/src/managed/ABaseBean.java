package managed;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import common.ApplicationConfig;

public class ABaseBean {
	protected String getFullPath(String nome) {
		String dir = ApplicationConfig.getDocumentdir();
		if( !dir.endsWith(File.separator))dir+=File.separator;
		return dir+nome;
	}

	protected BasicDocumentViewController getDocController() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		BasicDocumentViewController assetBean = application.evaluateExpressionGet(context,
				"#{basicDocumentViewController}", BasicDocumentViewController.class);
		return assetBean;
	}
	protected String getExt(String fileName) {
		String ext = "";
		int pos = fileName.lastIndexOf(".");
		if (pos != -1)
			ext = fileName.substring(pos);
		return ext;
	}
	protected void load( InputStream is, File outFile) throws Exception {
		byte buffer[] = new byte[1024];
		try (FileOutputStream os = new FileOutputStream(outFile);) {
			int count = 0;
			while ((count = is.read(buffer)) != -1) {
				os.write(buffer, 0, count);
			}
			os.flush();
		}
	}
	protected File createTempFile(String name, String ext, File dir) throws IOException {
		if( !dir.exists())dir.mkdirs();
		return File.createTempFile(name, ext, dir);
		
	}

}
