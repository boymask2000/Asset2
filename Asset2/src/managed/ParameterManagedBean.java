package managed;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import beans.Parameter;
import common.JsfUtil;
import database.dao.ParameterDAO;

public class ParameterManagedBean {
	private String FREQUENZA_CONTROLLO_FISICO = "FREC_CTR_FIS";

	private Parameter frequenzaControlloFisico;

	public void update() {
		ParameterDAO dao = new ParameterDAO();
		dao.update(frequenzaControlloFisico);
		JsfUtil.showMessage("Updated!");
	}

	public Parameter getFrequenzaControlloFisico() {
		ParameterDAO dao = new ParameterDAO();
		frequenzaControlloFisico = dao.selectParameter(FREQUENZA_CONTROLLO_FISICO);

		return frequenzaControlloFisico;
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
}
