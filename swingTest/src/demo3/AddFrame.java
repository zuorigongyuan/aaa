package demo3;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class AddFrame extends javax.swing.JFrame {
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private static File file = new File("./files");

	public AddFrame() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		setTitle("添加文件");
		jTextField1 = new javax.swing.JTextField();
		jSeparator1 = new javax.swing.JSeparator();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

	 
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jLabel1.setFont(new java.awt.Font("黑体", 0, 12)); // NOI18N
		jLabel1.setText("标题：");

		jLabel2.setFont(new java.awt.Font("黑体", 0, 12)); // NOI18N
		jLabel2.setText("内容：");

		jButton1.setFont(new java.awt.Font("黑体", 0, 14)); // NOI18N
		jButton1.setText("取消");
		// 取消按钮绑定事件
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setBackground(new java.awt.Color(51, 255, 51));
		jButton2.setFont(new java.awt.Font("黑体", 1, 14)); // NOI18N
		jButton2.setText("添加");
		// 取消按钮绑定事件
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}

		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(0,
														0, Short.MAX_VALUE)))
										.addContainerGap())
								.addGroup(layout
										.createSequentialGroup().addComponent(jLabel2).addGap(0, 0, Short.MAX_VALUE))))
				.addComponent(jSeparator1)
				.addGroup(layout.createSequentialGroup().addGap(148, 148, 148)
						.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
						.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(148, 148, 148)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(7, 7, 7).addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(2, 2, 2).addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}

//取消按钮事件
	private void jButton1ActionPerformed(ActionEvent evt) {

		closeWindow();

	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		String jtf = jTextField1.getText().trim();
		String jta = jTextArea1.getText().trim();
		if (jtf.equals("") || jta.equals("")) {
			NotesManager.jLabel.setText("添加文件的标题和内容不能为空");
			NotesManager.jDialog.setVisible(true);
		} else {

			addFile(jtf, jta);
			this.setVisible(false);
		}
	}

	void addFile(String name, String content) {
		FileWriter fw = null;
		if (!file.exists()) {
			file.mkdirs();
		} else {
			if (file.isDirectory()) {
				boolean bool = NotesManager.matchListName(name);
				if (!bool) {
					File newFile = new File(file + File.separator + name + ".txt");
					try {
						newFile.createNewFile();
						fw = new FileWriter(newFile, true);
						System.out.println(content);
						fw.write(content);
						fw.flush();
						fw.close();
						NotesManager.jList.setListData(NotesManager.getArray("./files"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

	}

//关闭窗体
	public void closeWindow() {
		this.setVisible(false);
	}

	public void showWindow() {
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE ); 
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AddFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				AddFrame newJFrame2 = new AddFrame();
				newJFrame2.setVisible(true);
				newJFrame2.setLocationRelativeTo(null);
			}
		});
	}

}
