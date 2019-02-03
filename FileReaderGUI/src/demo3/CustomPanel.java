package demo3;

import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {
	private static final long serialVersionUID = -737855555751315381L;
	private Image image;
	private int imgWidth;
	private int imgHeight;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	@Override
	protected void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		if (null == image) {
			return;
		}
		g.drawImage(image, x, y, image.getWidth(this), image.getHeight(this)  , this);
		g = null;
	}

	public void setImagePath(String imgPath) {
		try {
			// 该方法会将图像加载到内存，从而拿到图像的详细信息。
			image = ImageIO.read(new FileInputStream(imgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setImgWidth(image.getWidth(this));
		setImgHeight(image.getHeight(this));
	}
}
