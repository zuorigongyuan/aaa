
package net.tencent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;

import demo1.ZPanel;
import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;
import sun.net.www.content.image.jpeg;

public class NewJFrame extends javax.swing.JFrame {

	private JList jList1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private JPopupMenu menu;
	private JMenuItem mAdd, mDelete, mReload;
	private File file = new File("./files");
	  
	private ZPanel zPanel;  
	   
	   
	public NewJFrame() {

		initBefore();
		initComponents();
		initAfter();
	}

	// ��ȡĿ¼�µ��ļ��б�
	String[] getArray(String path) {
		File file = new File(path);
		String[] list = file.list();
		String[] fileList = new String[list.length];
		for (int i = 0; i < list.length; i++) {
			int index = list[i].lastIndexOf(".");
			String sub = list[i].substring(0, index);
			fileList[i] = sub;
		}
		return fileList;
	}

	private void initBefore() {
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new JList();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		String[] array = this.getArray("./files");
		jList1.setListData(array);

		jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jScrollPane1.setViewportView(jList1);
		jList1.setBackground(new Color(211, 211, 211));
		jTextArea1.setBackground(new Color(255, 255, 204));
		
	 
		
		menu = new JPopupMenu();
		mAdd = new JMenuItem("���");
		mDelete = new JMenuItem("ɾ��");
		mReload = new JMenuItem("ˢ��");
		menu.add(mAdd);
		menu.add(mDelete);
		menu.add(mReload);
		
		  zPanel = new ZPanel();  
	        zPanel.setImagePath("./cc6875a4716ffb996e79ed2b5c541047.png");  
	        zPanel.setPreferredSize(new Dimension(zPanel.getImgWidth(), zPanel  
	                .getImgHeight()));  
	    	jPanel2.add(zPanel);
		customEvent();
	}

	// �Զ����¼�
	void customEvent() {
		// �Ҽ��˵��¼�
		jList1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(NewJFrame.this, e.getX(), e.getY());
				}
			}
		});
		// �Ҽ��˵��¼�
		jTextArea1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(NewJFrame.this, e.getX() + 150, e.getY());
				}
			}
		});
		// ����б��� �¼���
		jList1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = jList1.getSelectedIndex();
				List list = jList1.getSelectedValuesList();
				String name = (String) list.get(0);
				boolean b = matchListName(name);
				if (b == true) {
					String fileName = file.getPath() + File.separator + name + ".txt";
					readFile(fileName);
				}
			}
		});
		// ɾ���¼�
		mDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// �ж��б��Ƿ�ѡ��
				if (jList1.isSelectionEmpty()) {
					System.out.println("He");
				} else {
					List list = jList1.getSelectedValuesList();
					String name = (String) list.get(0);
					String path = file.getPath() + File.separator + name + ".txt";
					System.out.println(path);
					File file2 = new File(path);
					if (file2.exists()) {
						file2.delete();
						String[] array = getArray("./files");
						jList1.setListData(array);
						jTextArea1.setText("");
					}
				}
			}

		});

	}

	void readFile(String fileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			jTextArea1.setText(new String(buf));
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �Ա��ļ���(���ļ��б��жԱȣ�
	boolean matchListName(String name) {
		String[] list = file.list();
		boolean bool = false;
		for (String string : list) {
			int index = string.lastIndexOf(".");
			String substring = string.substring(0, index);
			if (substring.trim().equals(name)) {
				bool = true;
				return bool;
			}
		}
		return bool;
	}

	private void initAfter() {
		this.setResizable(false);
		
		pack();
	}

	// ϵͳ�Զ����ɵĴ���
	private void initComponents() {

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1));

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
	//	jScrollPane2.setViewportView(jTextArea1);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jScrollPane2,
						javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));

	}

	public static void main(String args[]) {
		NewJFrame newJFrame = new NewJFrame();
		newJFrame.setLocationRelativeTo(null);
		newJFrame.setVisible(true);
	 
	}
}
