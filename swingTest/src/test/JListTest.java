package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JListTest {
 

	public static void main(String[] args) {
		JFrame jf = new JFrame("事件监听测试");
		jf.setVisible(true);
		jf.setSize(100, 200);
		JButton jbutton = new JButton("触发事件");
		jf.add(jbutton);
		jbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("触发了事件");
			}
		});

	}
}
