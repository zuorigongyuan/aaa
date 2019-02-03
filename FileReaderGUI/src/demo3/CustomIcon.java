package demo3;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

public class CustomIcon implements Icon {
	private BufferedImage i = null;
	private Icon icon = null;

public	CustomIcon(Icon icon) {
		this.icon = icon;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return icon.getIconHeight();
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return icon.getIconWidth();
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		float wid = c.getWidth();
		float hei = c.getHeight();
		int iconWid = icon.getIconWidth();
		int iconHei = icon.getIconHeight();

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.scale(wid / iconWid, hei / iconHei);
		icon.paintIcon(c, g2d, 0, 0);

	}

}
