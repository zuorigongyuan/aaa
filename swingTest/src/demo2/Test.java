package demo2;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Test extends JPanel {
	Image i = null;
	String s = "./484536e6gy1fbx14dc0zng20f008g4qp.gif";
	{
		try {
			i = ImageIO.read(new File(s));
		} catch (Exception e) {
 
			e.printStackTrace();
			System.out.println("¶ÁÈ¡Í¼Æ¬³ö´í");
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(i, 0, 0, null);
	}
	public static void main(String[] s) {
		JFrame frame = new JFrame();
		frame.add(new Test());
		frame.setBounds(100, 100, 500, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
 
