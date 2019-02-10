import java.io.File;
import java.io.IOException;

public class FileDemo {

	public static void main(String[] args) {
		/*File file = new File("./a");
		File file2 = new File(file.getAbsolutePath() + "/b.txt");
		try {
			System.out.println(file.getAbsolutePath());
			file.mkdirs();
			file2.createNewFile();
			System.out.println(file2.getAbsolutePath());
		} catch (Exception e) {
		}*/
		File file = new File("./files");
		String[] list = file.list();
		/*
		 * for (String string : list) { int indexOf = string.lastIndexOf(".");
		 * System.out.println(string.substring(0,indexOf));
		 * 
		 * }
		 */
		String[] lists=new String[list.length];
		for(int i=0;i<list.length;i++) {
		 
			int index = list[i].lastIndexOf(".");
			String sub = list[i].substring(0,index);
				  lists[i]=sub; 
				 System.out.println(lists[i]);
		 
		}
		System.out.println(lists.length);
		/*
		 * File[] ls = file.listFiles(); for(File f:ls) { System.err.println(f); }
		 */
	}
}
