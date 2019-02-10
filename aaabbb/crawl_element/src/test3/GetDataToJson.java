package test3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetDataToJson {
	public static void main(String[] args) throws IOException, InterruptedException {
		List list1 = new ArrayList<String>();
		List list2 = new ArrayList<String>();
		List list3 = new ArrayList<String>();
		int a = 0;
		int x;
		for (x = 47494; x <=47536; x = x + 3) {
			for (int i = 1; i <= 6; i++) {
				Connection connect = Jsoup.connect("https://www.shanbay.com/wordlist/6418/" + x + "/?page=" + i);
				connect.timeout(100000);
				connect.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				connect.header("Accept-Encoding", "gzip, deflate, sdch");
				connect.header("Accept-Language", "zh-CN,zh;q=0.8");
				connect.header("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
				Document document = connect.get();
				Elements elements = document.select("tbody>.row");

				String str = null;
				for (Element element : elements) {

					int b = (x - 47494) / 3 + 1;
					if (x >= 47494 && x < 47509) {
						str = "必修三第" + b + "单元";
					} else if (x >= 47509 && x < 47524) {
						b = b - 5;
						str = "必修四第" + b + "单元";
					} else {
						b = b - 10;
						str = "必修五第" + b + "单元";
					}

					String text1 = element.select(".span2").text().trim();
					System.err.println(text1 + "===" + a++ + "===" + str);
					list3.add(str);
					list1.add(text1);
				}
				for (Element element : elements) {
					String text2 = element.select(".span10").text().trim();
					System.out.println(text2);
					list2.add(text2);
				}

			}

		}

		JSONArray jsonArray = new JSONArray();
		for (int j = 0; j < list1.size(); j++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("word", (String) list1.get(j));
			jsonObject.put("name", (String) list2.get(j));
			jsonObject.put("title", (String) list3.get(j));
			jsonArray.put(jsonObject);
		}
		System.out.println(jsonArray.toString());
	}

}
