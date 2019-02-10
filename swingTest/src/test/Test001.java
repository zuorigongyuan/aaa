package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Test001 {

	private JFrame f;
	private JList jList;
	

	Test001() {
		init();
	}

	public void init() {
		f = new JFrame();
	//	Container container = f.getContentPane();
		/* f.setLayout(new GridLayout(1, 5)); */
		f.setLayout(null);
		Vector<String> vector = new Vector<String>();
		vector.addElement("aa");
		vector.addElement("bb");
		vector.addElement("cc");

		jList = new JList(vector);
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.green);
		jList.setBounds(0,0,200,600);
		jPanel.setBounds(200,0 , 500, 600);
		 
		ImageIcon imageIcon = new ImageIcon("cc6875a4716ffb996e79ed2b5c541047.png");
		JLabel	imageLabel = new JLabel(imageIcon); 
		imageLabel.setBounds(0,0,200,100);
		jPanel.add(imageLabel);
		
	/*	container.add(new JScrollPane(jList));
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.green);
		JPanel jPanel2 = new JPanel();
		jPanel2.setBackground(Color.blue);
		container.add(jPanel);
		container.add(jPanel);
		*/
		  
		f.add(jList);
		f.add(jPanel);
		
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0, 0, 700, 600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new Test001();
	}
}
