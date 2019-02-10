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

	// 初始化图片
	void initImage() {
		setTitle("文件查看器");

		contentPane = this.getContentPane();
		CustomIcon icon = new CustomIcon(new ImageIcon(getRandomImageName()));
	//	CustomIcon icon = new CustomIcon(new ImageIcon("./image/1549185396188.jpg"));
		JLabel iconLabel = new JLabel(icon);
		contentPane.add(iconLabel);

		// 绑定事件件
		showAndSetAttribute();
		try {
			Thread.currentThread().sleep(2500 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		contentPane.remove(iconLabel);
		// 初始化图片之后
		showAndSetAttribute();
	}

	// 随机获取图片
	String getRandomImageName() {
		File file = new File("./image");
		String[] list = file.list();
		Random random = new Random();
		int index = random.nextInt(list.length);
		
		System.out.println(list[index]);
		return "./image/"+list[index];
	}

	// 初始化组件
	void initComponents() {
		contentPane.setLayout(null);

		jsp1 = new JScrollPane();
		jsp2 = new JScrollPane();

		jsp1.setBounds(0, 0, 260, 800);
		jsp2.setBounds(260, 0, 940, 800);
 
		String[] array = getArray("./files");
		jList = new JList<String>();
		jList.setFont(new Font("黑体", Font.BOLD, 13));
		jList.setListData(array);
		jList.setBackground(new Color(240, 255, 240));
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp1.setViewportView(jList);

		jta = new JTextArea();
		jta.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		jsp2.setViewportView(jta);

		menu = new JPopupMenu();
		mAdd = new JMenuItem("添加");
		mDelete = new JMenuItem("删除");
		mReload = new JMenuItem("刷新");
		menu.add(mAdd);
		menu.add(mDelete);
		menu.add(mReload);

		jDialog = new JDialog(this, "提示信息", true);
		JPanel panelLable = new JPanel();
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(1, 3));

		// JDialog 美化操作
		jDialog.setBounds(0, 0, 300, 200);
		jDialog.setLayout(new GridLayout(6, 1));
		okButton = new JButton("确定");
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

//显示
	void showAndSetAttribute() {
		setVisible(true);
		setSize(1200, 830);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}

	// 获取目录下的文件列表
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

	// 自定义事件
	private void customEvent() {
		// 右键菜单事件
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.show(NotesManager.this, e.getX(), e.getY());
				}
			}
		});
		// 右键菜单事件
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
		// 删除事件
		mDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 判断列表是否被选中
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
					jLabel.setText("你还未选中文件就进行了删除操作");
					jDialog.setVisible(true);
				}
			}
		});
		// 添加事件
		mAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				addFrame = new AddFrame();
				addFrame.showWindow();
			}
		});
		// 点击按钮关闭dialog
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jDialog.setVisible(false);
			}

		});
	}

//	读取文件
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

	// 对比文件名(与文件列表中对比）
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
