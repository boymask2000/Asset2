package managed;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import common.Theme;


public class ThemeSwitcherView {
	 
    private List<Theme> themes;
     
    @Inject
    private Theme service;
 
    @PostConstruct
    public void init() {
    	service=new Theme();
        themes = service.getThemes();
    }
     
    public List<Theme> getThemes() {
        return themes;
    } 
 
    public void setService(Theme service) {
        this.service = service;
    }

	public Theme getService() {
		return service;
	}
}