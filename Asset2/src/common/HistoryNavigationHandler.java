package common;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class HistoryNavigationHandler extends ConfigurableNavigationHandler {

	private NavigationHandler wrapped;
	
	 private final Stack<String> outcomes=new Stack<String>();

	public HistoryNavigationHandler(NavigationHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void handleNavigation(FacesContext context, String from, String outcome) {

		// TODO: Do your job here.

		System.out.println(from +" --> "+outcome);
		
		if (outcome != null)
        {
            if (outcome.equals("back"))
            {
                final String lastViewId = this.outcomes.pop();
//System.out.println("back "+lastViewId);
                final ViewHandler viewHandler = context.getApplication().getViewHandler();
                final UIViewRoot viewRoot = viewHandler.createView(context, lastViewId);
                context.setViewRoot(viewRoot);
                context.renderResponse();

                return;
            }
			if( outcome.equals("admin")||outcome.equals("adminHome"))outcomes.clear();
			
			this.outcomes.push(context.getViewRoot().getViewId());
        }
		
		
		
		wrapped.handleNavigation(context, from, outcome);
	}

	@Override
	public NavigationCase getNavigationCase(FacesContext context, String fromAction, String outcome) {
		return (wrapped instanceof ConfigurableNavigationHandler)
				? ((ConfigurableNavigationHandler) wrapped).getNavigationCase(context, fromAction, outcome)
				: null;
	}

	@Override
	public Map<String, Set<NavigationCase>> getNavigationCases() {
		return (wrapped instanceof ConfigurableNavigationHandler)
				? ((ConfigurableNavigationHandler) wrapped).getNavigationCases()
				: null;
	}

}