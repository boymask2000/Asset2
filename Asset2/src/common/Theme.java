package common;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Theme implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private String displayName;
    private String image;
    
    public static final String DEFAULT_THEME = "aristo";
    public static final List<Theme> THEMES;
    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Bundle");
        THEMES = new ArrayList<>();
        THEMES.add(new Theme("aristo", resourceBundle.getString("aristo"), "aristo.png"));
        THEMES.add(new Theme("black-tie", resourceBundle.getString("black-tie"), "black-tie.png"));
        THEMES.add(new Theme("blitzer", resourceBundle.getString("blitzer"), "blitzer.png"));
        THEMES.add(new Theme("bluesky", resourceBundle.getString("bluesky"), "bluesky.png"));
        THEMES.add(new Theme("casablanca", resourceBundle.getString("casablanca"), "casablanca.png"));
        THEMES.add(new Theme("cupertino", resourceBundle.getString("cupertino"), "cupertino.png"));
        THEMES.add(new Theme("dark-hive", resourceBundle.getString("dark-hive"), "dark-hive.png"));
        THEMES.add(new Theme("dot-luv", resourceBundle.getString("dot-luv"), "dot-luv.png"));
        THEMES.add(new Theme("eggplant", resourceBundle.getString("eggplant"), "eggplant.png"));
        THEMES.add(new Theme("excite-bike", resourceBundle.getString("excite-bike"), "excite-bike.png"));
        THEMES.add(new Theme("flick", resourceBundle.getString("flick"), "flick.png"));
        THEMES.add(new Theme("glass-x", resourceBundle.getString("glass-x"), "glass-x.png"));
        THEMES.add(new Theme("hot-sneaks", resourceBundle.getString("hot-sneaks"), "hot-sneaks.png"));
        THEMES.add(new Theme("humanity", resourceBundle.getString("humanity"), "humanity.png"));
        THEMES.add(new Theme("le-frog", resourceBundle.getString("le-frog"), "le-frog.png"));
        THEMES.add(new Theme("midnight", resourceBundle.getString("midnight"), "midnight.png"));
        THEMES.add(new Theme("mint-choc", resourceBundle.getString("mint-choc"), "mint-choc.png"));
        THEMES.add(new Theme("overcast", resourceBundle.getString("overcast"), "overcast.png"));
        THEMES.add(new Theme("pepper-grinder", resourceBundle.getString("pepper-grinder"), "pepper-grinder.png"));
        THEMES.add(new Theme("redmond", resourceBundle.getString("redmond"), "redmond.png"));
        THEMES.add(new Theme("rocket", resourceBundle.getString("rocket"), "rocket.png"));
        THEMES.add(new Theme("smoothness", resourceBundle.getString("smoothness"), "smoothness.png"));
        THEMES.add(new Theme("south-street", resourceBundle.getString("south-street"), "south-street.png"));
        THEMES.add(new Theme("start", resourceBundle.getString("start"), "start.png"));
        THEMES.add(new Theme("sunny", resourceBundle.getString("sunny"), "sunny.png"));
        THEMES.add(new Theme("swanky-purse", resourceBundle.getString("swanky-purse"), "swanky-purse.png"));
        THEMES.add(new Theme("trontastic", resourceBundle.getString("trontastic"), "trontastic.png"));
        THEMES.add(new Theme("ui-darkness", resourceBundle.getString("ui-darkness"), "ui-darkness.png"));
        THEMES.add(new Theme("ui-lightness", resourceBundle.getString("ui-lightness"), "ui-lightness.png"));
        THEMES.add(new Theme("vader", resourceBundle.getString("vader"), "vader.png"));
        THEMES.add(new Theme("afterdark", resourceBundle.getString("afterdark"), "vader.png"));
    }

   
    public Theme() {
    }

    public Theme(String name, String displayName, String image) {
        this.name = name;
        this.displayName = displayName;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }

	public List<Theme> getThemes() {

		return THEMES;
	}
}
