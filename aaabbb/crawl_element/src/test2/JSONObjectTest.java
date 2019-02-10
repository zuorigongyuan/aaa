package test2;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONObjectTest {
	public static void main(String[] args) {
		JSONObject jsonData = new JSONObject();
		jsonData.put("name", "el");
		jsonData.put("age", "33");

		JSONObject jsonData2 = new JSONObject();
		jsonData2.put("name", "el2");
		jsonData2.put("age", "332");
		JSONArray jsonArray = new JSONArray();

		jsonArray.put(jsonData);
		jsonArray.put(jsonData2);

		System.out.println(jsonArray.toString());
	}
}
