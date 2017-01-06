package com.kamontat.code.controller;

import java.awt.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 1/7/2017 AD - 4:56 AM
 */
public class Display {
	/**
	 * this variable is display size, <br>using by said <code>"display.getWidth"</code> and <code>"display.getHeight"</code>
	 */
	private static DisplayMode display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
	
	/**
	 * get point that stay in the center of the screen
	 *
	 * @param pageSize
	 * 		size of page that want to show in the center
	 * @return point of center screen
	 */
	public static Point getCenterLocation(Dimension pageSize) {
		return new Point((int) ((display.getWidth() / 2) - (pageSize.getWidth() / 2)), (int) ((display.getHeight() / 2) - (pageSize.getHeight() / 2)));
	}
}
