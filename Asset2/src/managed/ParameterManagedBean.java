package managed;

import java.io.File;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import beans.Parameter;
import common.JsfUtil;
import database.dao.ParameterDAO;

public class ParameterManagedBean {

	private Parameter frequenzaControlloFisico;
	private Parameter directoryPDFInterventiMensili;

	public void update() {
		ParameterDAO dao = new ParameterDAO();
		dao.update(frequenzaControlloFisico);
		dao.update(directoryPDFInterventiMensili);
		JsfUtil.showMessage("Updated!");
	}

	public Parameter getFrequenzaControlloFisico() {
		ParameterDAO dao = new ParameterDAO();
		frequenzaControlloFisico = dao.selectParameter(ParameterDAO.FREQUENZA_CONTROLLO_FISICO);

		return frequenzaControlloFisico;
	}
	public Parameter getDirectoryPDFInterventiMensili() {
		ParameterDAO dao = new ParameterDAO();
		directoryPDFInterventiMensili = dao.selectParameter(ParameterDAO.DIRECTORY_PDF_INTERVENTI_MENSILI);

		return directoryPDFInterventiMensili;
	}
	
	public void setDirectoryPDFInterventiMensili(Parameter directoryPDFInterventiMensili) {
		this.directoryPDFInterventiMensili = directoryPDFInterventiMensili;
	}
	
	public void setFrequenzaControlloFisico(Parameter frequenzaControlloFisico) {
		this.frequenzaControlloFisico = frequenzaControlloFisico;
	}

	public void isPositiveNumber(FacesContext context, UIComponent comp, Object value) {
		String sVal = (String) value;
		int val;
		try {
			val = Integer.parseInt(sVal);
		} catch (NumberFormatException e) {
			FacesMessage msg = new FacesMessage(" Must be numeric");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
	
		}
		if( val<=0) {
			FacesMessage msg = new FacesMessage(" Must be positive number");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}

	}
	public void	existDirectory(FacesContext context, UIComponent comp, Object value) {
		String sVal = (String) value;
		
		File fDir = new File(sVal);
		
		if( !fDir.exists()) {
			FacesMessage msg = new FacesMessage("Directory does not exist");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}
		if( !fDir.canWrite()) {
			FacesMessage msg = new FacesMessage("Directory is not writable");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(msg);
		}

	}
	
}
