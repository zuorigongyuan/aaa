package demo3;

import java.io.File;
import java.util.Random;

public class Test {
	public static void main(String[] args) {
		File file = new File("./image");
		String[] list = file.list();
		System.out.println(list.length);
		Random random = new Random();
		int nextInt = random.nextInt(list.length);
		System.out.println(nextInt);
		System.out.println(list[nextInt]);
	}
}
