package demo2;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test2 {
	static ImageIcon i = new ImageIcon("./484536e6gy1fbx14dc0zng20f008g4qp.gif");

	public static void main(String[] s) {
		JFrame frame = new JFrame();
		JLabel lable = new JLabel(new Test2().i);
		frame.add(lable);
		frame.setBounds(0, 0, i.getIconWidth(), i.getIconHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
