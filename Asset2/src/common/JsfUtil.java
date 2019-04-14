package common;

import java.util.Date;

import javax.el.ELContext;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public class JsfUtil {
	public static void redirect(String rule) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		NavigationHandler nh = ctx.getApplication().getNavigationHandler();
		nh.handleNavigation(ctx, null, rule);
		ctx.renderResponse();
	}
	public static void goTo(String rule) {
		FacesContext.getCurrentInstance().getViewRoot().setViewId(rule);
		   FacesContext.getCurrentInstance().renderResponse();
		   FacesContext.getCurrentInstance().responseComplete(); 
	}
	public static Date fixDate( Date date) {
		if(date==null)return null;
		date.setTime(date.getTime() - (date.getTimezoneOffset() * 60 * 1000));
		return date;
	}

	public static Object getBean(String beanName) {

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		return  FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext,
				null, beanName);
		
	}
}
