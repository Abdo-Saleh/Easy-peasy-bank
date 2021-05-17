package guiclient;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class GuiTools {

	public static void centerFrame(JFrame frame) {
		if (frame instanceof JFrame) {
			// Get the screen size
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			// Calculate the frame location
			int x = (screenSize.width - frame.getWidth()) / 2;
			int y = (screenSize.height - frame.getHeight()) / 2;
			// Set the new frame location
			frame.setLocation(x, y);
		}
	}

	public static void setLogo(JLabel label) {
		Image logo = new ImageIcon(GuiTools.class.getResource("/mylogo.png")).getImage();
		label.setIcon(new ImageIcon(logo));
	}
	public static void setEpbLogo(JFrame frame) {
		Image logo = new ImageIcon(GuiTools.class.getResource("/mylogo.png")).getImage();
		frame.setIconImage(logo);
	}
	
	public static Country[] createCountryList() {
	    String[] countryCodes = Locale.getISOCountries();
	    Country[] listCountry = new Country[countryCodes.length];
	 
	    for (int i = 0; i < countryCodes.length; i++) {
	        Locale locale = new Locale("", countryCodes[i]);
	        String code = locale.getCountry();
	        String name = locale.getDisplayCountry();
	 
	        listCountry[i] = new Country(code, name);
	    }
	 
	    Arrays.sort(listCountry);
	 
	    return listCountry;
	}
}
