package demo3;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Container;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class NotesManager extends JFrame {

	private static final long serialVersionUID = 256011037388875083L;
	private Container contentPane;
	private JScrollPane jsp1;
	private JScrollPane jsp2;
	protected static JList<String> jList;
	private JTextArea jta;
	private static File file = new File("./files");

	private JPopupMenu menu;
	private JMenuItem mAdd, mDelete, mReload;
	private AddFrame addFrame;

	protected static JDialog jDialog;
	protected static JLabel jLabel;
	protected static JButton okButton;

	NotesManager() {
		initImage();
		initComponents();
	}

	// ��ʼ��ͼƬ
	void initImage() {
		setTitle("�ļ��鿴��");

		contentPane = this.getContentPane();
		CustomIcon icon = new CustomIcon(new ImageIcon(getRandomImageName()));
	//	CustomIcon icon = new CustomIcon(new ImageIcon("./image/1549185396188.jpg"));
		JLabel iconLabel = new JLabel(icon);
		contentPane.add(iconLabel);

		// ���¼���
		showAndSetAttribute();
		try {
			Thread.currentThread().sleep(2500 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		contentPane.remove(iconLabel);
		// ��ʼ��ͼƬ֮��
		showAndSetAttribute();
	}

	// �����ȡͼƬ
	String getRandomImageName() {
		File file = new File("./image");
		String[] list = file.list();
		Random random = new Random();
		int index = random.nextInt(list.length);
		
		System.out.println(list[index]);
		return "./image/"+list[index];
	}

	// ��ʼ�����
	void initComponents() {
		contentPane.setLayout(null);

		jsp1 = new JScrollPane();
		jsp2 = new JScrollPane();

		jsp1.setBounds(0, 0, 260, 800);
		jsp2.setBounds(260, 0, 940, 800);
 
		String[] array = getArray("./files");
		jList = new JList<String>();
		jList.setFont(new Font("����", Font.BOLD, 13));
		jList.setListData(array);
		jList.setBackground(new Color(240, 255, 240));
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp1.setViewportView(jList);

		jta = new JTextArea();
		jta.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		jsp2.setViewportView(jta);

		menu = new JPopupMenu();
		mAdd = new JMenuItem("���");
		mDelete = new JMenuItem("ɾ��");
		mReload = new JMenuItem("ˢ��");
		menu.add(mAdd);
		menu.add(mDelete);
		menu.add(mReload);

		jDialog = new JDialog(this, "��ʾ��Ϣ", true);
		JPanel panelLable = new JPanel();
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(1, 3));

		// JDialog ��������
		jDialog.setBounds(0, 0, 300, 200);
		jDialog.setLayout(new GridLayout(6, 1));
		okButton = new JButton("ȷ��");
		okButton.setSize(30, 20);
		jLabel = new JLabel();
		jDialog.add(new JPanel());
		jDialog.add(panelLable);
		jDialog.add(new JPanel());
		jDialog.add(new JPanel());
		jDialog.add(panelButton);
		panelButton.add(new JPanel());
		panelButton.add(okButton);
		panelButton.add(new JPanel());
		jDialog.add(new JPanel());
		panelLable.add(jLabel);
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);

		contentPane.add(jsp1);
		contentPane.add(jsp2);

		showAndSetAttribute();
		customEvent();
	}

//��ʾ
	void showAndSetAttribute() {
		setVisible(true);
		setSize(1200, 830);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}

	// ��ȡĿ¼�µ��ļ��б�
	static String[] getArray(String path) {
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

	// �Զ����¼�
	private void customEvent() {
		// �Ҽ��˵��¼�
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(NotesManager.this, e.getX(), e.getY());
				}
			}
		});
		// �Ҽ��˵��¼�
		jta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(NotesManager.this, e.getX() + 200, e.getY());
				}
			}
		});
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = jList.getSelectedIndex();
				if (index != -1) {
					List<String> list = jList.getSelectedValuesList();
					String name = (String) list.get(0);
					boolean b = matchListName(name);
					if (b == true) {
						String fileName = file.getPath() + File.separator + name + ".txt";
						readFile(fileName);
					}
				}
			}
		});
		// ɾ���¼�
		mDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// �ж��б��Ƿ�ѡ��
				if (!jList.isSelectionEmpty()) {
					List list = jList.getSelectedValuesList();
					String name = (String) list.get(0);
					String path = file.getPath() + File.separator + name + ".txt";
					System.out.println(path);
					File file2 = new File(path);
					if (file2.exists()) {
						file2.delete();
						String[] array = getArray("./files");
						jList.setListData(array);
						jta.setText("");
					}
				} else {
					jLabel.setText("�㻹δѡ���ļ��ͽ�����ɾ������");
					jDialog.setVisible(true);
				}
			}
		});
		// ����¼�
		mAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				addFrame = new AddFrame();
				addFrame.showWindow();
			}
		});
		// �����ť�ر�dialog
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jDialog.setVisible(false);
			}

		});
	}

//	��ȡ�ļ�
	private void readFile(String fileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			jta.setText(new String(buf));
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �Ա��ļ���(���ļ��б��жԱȣ�
	protected static boolean matchListName(String name) {
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

	public static void main(String[] args) {
		new NotesManager();
	}
}
