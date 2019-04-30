package managed;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(eager=true)
@ApplicationScoped
public class Faces {
	public static String HOMEDIR;

    @PostConstruct
    public void init() {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HOMEDIR = externalContext.getRealPath("/");

    }

    @PreDestroy
    public void destroy() {
        // ...
    }

}