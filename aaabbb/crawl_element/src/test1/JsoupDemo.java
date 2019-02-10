package test1;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.w3c.dom.Node;

public class JsoupDemo {
	public static void main(String[] args) throws IOException, InterruptedException {

		Connection connect = Jsoup.connect("https://danci.911cha.com/book_47.html");
		connect.timeout(100000000);
		connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		connect.header("Accept-Encoding", "gzip, deflate, sdch");
		connect.header("Accept-Language", "zh-CN,zh;q=0.8");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		Document document = connect.get();
		Elements elements = document.select(".l5>li>a");
		int count = 0;
		for (Element element : elements) {

			count++;
			System.out.println(element.text() + "====" + count);

			Connection conn = Jsoup.connect("https://danci.911cha.com/" + element.text()+ ".html");
			//Connection conn = Jsoup.connect("https://danci.911cha.com/brand.html");
			conn.timeout(1000000000);
			conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.header("Accept-Encoding", "gzip, deflate, sdch");
			conn.header("Accept-Language", "zh-CN,zh;q=0.8");
			conn.header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			Document document2 = conn.get();
			Elements element2 = document2.select("div.f12.mt");
			for (Element e : element2) {
				// System.err.println(e.getElementsByClass("mr"));
				if(!e.getElementsContainingOwnText("第三人称单数").toString().equals("")) {
					System.err.println(e.getElementsContainingOwnText("第三人称单数").toString());
				}else {
					System.out.println("该单词无三人称单数");
				}
			 
			}
			Thread.sleep(1500);

		}

	}

}
