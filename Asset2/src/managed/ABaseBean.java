package managed;

import java.io.File;

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
}
