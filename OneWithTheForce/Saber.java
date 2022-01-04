import java.net.URISyntaxException;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Saber implements Cloneable {
	// Data fields
	boolean isDefault;
	String name = "Unnamed Saber";
	String color = "Blue";
	Image coloredEmitter = new Image("/Colored Emitters/AnakinEmitter/AnakinBlue.png");
	Image emitter = new Image("/Saber Parts/AnakinEmitter.png");
	Image guard = new Image("/Saber Parts/AnakinGuard.png");
	Image bladeSwitch = new Image("/Saber Parts/AnakinSwitch.png");
	Image pommel = new Image("/Saber Parts/AnakinPommel.png");
	Media clash1;
	Media clash2;
	Media clash3;
	Media deactivate;
	Media deflect;
	Media hum;
	Media ignite;
	Media swoosh1;
	Media swoosh2;
	Media swoosh3;
	
	// Construct default saber
	public Saber() {
	}
	
	// Construct saber with specific properties
	public Saber(boolean isDefault, String name, String color, Image coloredEmitter, 
			Image emitter, Image guard, Image bladeSwitch, Image pommel) {
		this.isDefault = isDefault;
		this.name = name;
		this.color = color;
		this.coloredEmitter = coloredEmitter;
		this.emitter = emitter;
		this.guard = guard;
		this.bladeSwitch = bladeSwitch;
		this.pommel = pommel;
	}
	
	// Return isDefault
	public boolean getIsDefault() {
		return isDefault;
	}
	
	// Set a new isDefault
	public void isIsDefault(boolean newIsDefault) {
		isDefault = newIsDefault;
	}
	
	// Return name
	public String getName() {
		return name;
	}
	
	// Set a new name
	public void setName(String newName) {
		name = newName;
	}
	
	// Return color
	public String getColor() {
		return color;
	}
	
	// Set a new color
	public void setColor(String newColor) {
		color = newColor;
	}
	
	// Return coloredEmitter
	public Image getColoredEmitter() {
		return coloredEmitter;
	}
	
	// Set a new coloredEmitter
	public void setColoredEmitter(Image newColoredEmitter) {
		coloredEmitter = newColoredEmitter;
	}
	
	// Return emitter
	public Image getEmitter() {
		return emitter;
	}
	
	// Set a new emitter
	public void setEmitter(Image newEmitter) {
		emitter = newEmitter;
	}
	
	// Return guard
	public Image getGuard() {
		return guard;
	}
	
	// Set a new guard
	public void setGuard(Image newGuard) {
		guard = newGuard;
	}
	
	// Return bladeSwitch
	public Image getBladeSwitch() {
		return bladeSwitch;
	}
	
	// Set a new bladeSwitch
	public void setBladeSwitch(Image newBladeSwitch) {
		bladeSwitch = newBladeSwitch;
	}
	// Return pommel
	public Image getPommel() {
		return pommel;
	}
	
	// Set a new pommel
	public void setPommel(Image newPommel) {
		pommel = newPommel;
	}
	
	// Clone an object
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	// Return clash1 based on color
	public Media getClash1() throws URISyntaxException {
		switch (color) {
			case "Black":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Black/Clash1Black.mp3").toURI().toString());
			case "Blue":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Blue/Clash1Blue.mp3").toURI().toString());
			case "Green":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Blue/Clash1Blue.mp3").toURI().toString());
			case "Orange":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Orange/Clash1Orange.mp3").toURI().toString());
			case "Purple":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Blue/Clash1Blue.mp3").toURI().toString());
			case "Red":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Blue/Clash1Blue.mp3").toURI().toString());
			case "Silver":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Silver/Clash1Silver.mp3").toURI().toString());
			case "Yellow":
				return clash1 = new Media(getClass().getResource("/SaberSounds/Yellow/Clash1Yellow.mp3").toURI().toString());
		}
		return clash1;
	}
	
	// Return clash2 based on color
		public Media getClash2() throws URISyntaxException {
			switch (color) {
				case "Black":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Black/Clash2Black.mp3").toURI().toString());
				case "Blue":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Blue/Clash2Blue.mp3").toURI().toString());
				case "Green":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Blue/Clash2Blue.mp3").toURI().toString());
				case "Orange":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Orange/Clash2Orange.mp3").toURI().toString());
				case "Purple":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Blue/Clash2Blue.mp3").toURI().toString());
				case "Red":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Blue/Clash2Blue.mp3").toURI().toString());
				case "Silver":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Silver/Clash2Silver.mp3").toURI().toString());
				case "Yellow":
					return clash2 = new Media(getClass().getResource("/SaberSounds/Yellow/Clash2Yellow.mp3").toURI().toString());
			}
			return clash2;
		}
		
		// Return clash1 based on color
		public Media getClash3() throws URISyntaxException {
			switch (color) {
				case "Black":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Black/Clash3Black.mp3").toURI().toString());
				case "Blue":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Blue/Clash3Blue.mp3").toURI().toString());
				case "Green":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Blue/Clash3Blue.mp3").toURI().toString());
				case "Orange":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Orange/Clash3Orange.mp3").toURI().toString());
				case "Purple":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Blue/Clash3Blue.mp3").toURI().toString());
				case "Red":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Blue/Clash3Blue.mp3").toURI().toString());
				case "Silver":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Silver/Clash3Silver.mp3").toURI().toString());
				case "Yellow":
					return clash3 = new Media(getClass().getResource("/SaberSounds/Yellow/Clash3Yellow.mp3").toURI().toString());
			}
			return clash3;
		}
		
		// Return deactivate based on color
		public Media getDeactivate() throws URISyntaxException {
			switch (color) {
				case "Black":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Black/DeactivateBlack.mp3").toURI().toString());
				case "Blue":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Blue/DeactivateBlue.mp3").toURI().toString());
				case "Green":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Blue/DeactivateBlue.mp3").toURI().toString());
				case "Orange":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Orange/DeactivateOrange.mp3").toURI().toString());
				case "Purple":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Blue/DeactivateBlue.mp3").toURI().toString());
				case "Red":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Blue/DeactivateBlue.mp3").toURI().toString());
				case "Silver":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Silver/DeactivateSilver.mp3").toURI().toString());
				case "Yellow":
					return deactivate = new Media(getClass().getResource("/SaberSounds/Yellow/DeactivateYellow.mp3").toURI().toString());
			}
			return deactivate;
		}
		
		// Return deflect based on color
		public Media getDeflect() throws URISyntaxException {
			switch (color) {
				case "Black":
					return deflect = new Media(getClass().getResource("/SaberSounds/Black/DeflectBlack.mp3").toURI().toString());
				case "Blue":
					return deflect = new Media(getClass().getResource("/SaberSounds/Blue/DeflectBlue.mp3").toURI().toString());
				case "Green":
					return deflect = new Media(getClass().getResource("/SaberSounds/Blue/DeflectBlue.mp3").toURI().toString());
				case "Orange":
					return deflect = new Media(getClass().getResource("/SaberSounds/Orange/DeflectOrange.mp3").toURI().toString());
				case "Purple":
					return deflect = new Media(getClass().getResource("/SaberSounds/Blue/DeflectBlue.mp3").toURI().toString());
				case "Red":
					return deflect = new Media(getClass().getResource("/SaberSounds/Blue/DeflectBlue.mp3").toURI().toString());
				case "Silver":
					return deflect = new Media(getClass().getResource("/SaberSounds/Silver/DeflectSilver.mp3").toURI().toString());
				case "Yellow":
					return deflect = new Media(getClass().getResource("/SaberSounds/Yellow/DeflectYellow.mp3").toURI().toString());
			}
			return deflect;
		}
		
		// Return hum based on color
		public Media getHum() throws URISyntaxException {
			switch (color) {
				case "Black":
					return hum = new Media(getClass().getResource("/SaberSounds/Black/HumBlack.mp3").toURI().toString());
				case "Blue":
					return hum = new Media(getClass().getResource("/SaberSounds/Blue/HumBlue.mp3").toURI().toString());
				case "Green":
					return hum = new Media(getClass().getResource("/SaberSounds/Blue/HumBlue.mp3").toURI().toString());
				case "Orange":
					return hum = new Media(getClass().getResource("/SaberSounds/Orange/HumOrange.mp3").toURI().toString());
				case "Purple":
					return hum = new Media(getClass().getResource("/SaberSounds/Blue/HumBlue.mp3").toURI().toString());
				case "Red":
					return hum = new Media(getClass().getResource("/SaberSounds/Blue/HumBlue.mp3").toURI().toString());
				case "Silver":
					return hum = new Media(getClass().getResource("/SaberSounds/Silver/HumSilver.mp3").toURI().toString());
				case "Yellow":
					return hum = new Media(getClass().getResource("/SaberSounds/Yellow/HumYellow.mp3").toURI().toString());
			}
			return hum;
		}
		
		// Return ignite based on color
		public Media getIgnite() throws URISyntaxException {
			switch (color) {
				case "Black":
					return ignite = new Media(getClass().getResource("/SaberSounds/Black/IgniteBlack.mp3").toURI().toString());
				case "Blue":
					return ignite = new Media(getClass().getResource("/SaberSounds/Blue/IgniteBlue.mp3").toURI().toString());
				case "Green":
					return ignite = new Media(getClass().getResource("/SaberSounds/Blue/IgniteBlue.mp3").toURI().toString());
				case "Orange":
					return ignite = new Media(getClass().getResource("/SaberSounds/Orange/IgniteOrange.mp3").toURI().toString());
				case "Purple":
					return ignite = new Media(getClass().getResource("/SaberSounds/Blue/IgniteBlue.mp3").toURI().toString());
				case "Red":
					return ignite = new Media(getClass().getResource("/SaberSounds/Blue/IgniteBlue.mp3").toURI().toString());
				case "Silver":
					return ignite = new Media(getClass().getResource("/SaberSounds/Silver/IgniteSilver.mp3").toURI().toString());
				case "Yellow":
					return ignite = new Media(getClass().getResource("/SaberSounds/Yellow/IgniteYellow.mp3").toURI().toString());
			}
			return ignite;
		}
		
		// Return swoosh1 based on color
		public Media getSwoosh1() throws URISyntaxException {
			switch (color) {
				case "Black":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Black/Swoosh1Black.mp3").toURI().toString());
				case "Blue":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh1Blue.mp3").toURI().toString());
				case "Green":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh1Blue.mp3").toURI().toString());
				case "Orange":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Orange/Swoosh1Orange.mp3").toURI().toString());
				case "Purple":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh1Blue.mp3").toURI().toString());
				case "Red":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh1Blue.mp3").toURI().toString());
				case "Silver":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Silver/Swoosh1Silver.mp3").toURI().toString());
				case "Yellow":
					return swoosh1 = new Media(getClass().getResource("/SaberSounds/Yellow/Swoosh1Yellow.mp3").toURI().toString());
			}
			return swoosh1;
		}
		
		// Return swoosh2 based on color
		public Media getSwoosh2() throws URISyntaxException {
			switch (color) {
				case "Black":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Black/Swoosh2Black.mp3").toURI().toString());
				case "Blue":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh2Blue.mp3").toURI().toString());
				case "Green":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh2Blue.mp3").toURI().toString());
				case "Orange":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Orange/Swoosh2Orange.mp3").toURI().toString());
				case "Purple":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh2Blue.mp3").toURI().toString());
				case "Red":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh2Blue.mp3").toURI().toString());
				case "Silver":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Silver/Swoosh2Silver.mp3").toURI().toString());
				case "Yellow":
					return swoosh2 = new Media(getClass().getResource("/SaberSounds/Yellow/Swoosh2Yellow.mp3").toURI().toString());
			}
			return swoosh2;
		}
		
		// Return swoosh3 based on color
		public Media getSwoosh3() throws URISyntaxException {
			switch (color) {
				case "Black":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Black/Swoosh3Black.mp3").toURI().toString());
				case "Blue":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh3Blue.mp3").toURI().toString());
				case "Green":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh3Blue.mp3").toURI().toString());
				case "Orange":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Orange/Swoosh3Orange.mp3").toURI().toString());
				case "Purple":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh3Blue.mp3").toURI().toString());
				case "Red":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Blue/Swoosh3Blue.mp3").toURI().toString());
				case "Silver":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Silver/Swoosh3Silver.mp3").toURI().toString());
				case "Yellow":
					return swoosh3 = new Media(getClass().getResource("/SaberSounds/Yellow/Swoosh3Yellow.mp3").toURI().toString());
			}
			return swoosh3;
		}
}
