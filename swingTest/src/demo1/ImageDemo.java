package demo1;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class ImageDemo extends JFrame {
	 private static final long serialVersionUID = -2216276219179107707L;  
	    private Container con;  
	  
	    private ZPanel zPanel;  
	  
	  
	    private ImageDemo() {  
	        con = getContentPane();  
	  
	        zPanel = new ZPanel();  
	        zPanel.setImagePath("./cc6875a4716ffb996e79ed2b5c541047.png");  
	        zPanel.setPreferredSize(new Dimension(zPanel.getImgWidth(), zPanel  
	                .getImgHeight()));  
	  
	        
	       
	  
	        con.add(zPanel, BorderLayout.CENTER);  
	  
	        finalSetting();  
	    }  
	  
	    private void finalSetting() {  
	        setTitle("ZakiSoft ZFileRenamerV0.2");  
	  
		/*
		 * Image image = Toolkit.getDefaultToolkit().getImage(
		 * this.getClass().getResource("1dfddfa.png")); setIconImage(image);
		 */
	  
	       
	        setSize(zPanel.getImgWidth(),zPanel.getImgHeight());  
	        setVisible(true);  
	      setResizable(false);
	      this.setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    }  
	  
	    public static void main(String[] args) {  
	        new ImageDemo();  
	    }  
}
