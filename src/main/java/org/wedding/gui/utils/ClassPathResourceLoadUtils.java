package org.wedding.gui.utils;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ClassPathResourceLoadUtils implements ResourceLoadUtils {

	public Icon icon(String iconName) {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(iconName + ".png");
		if (resource == null) {
			throw new IllegalArgumentException("El icono " + iconName + ".png no existe");
		}
		return new ImageIcon(resource);
	}

}