package test2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadJSONFile {
	public static void main(String[] args) throws IOException {
		File file = new File(ReadJSONFile.class.getResource("/demo.json").getFile());
		String content = FileUtils.readFileToString(file);
		JSONArray jsonArray = new JSONArray(content);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			System.out.println(object.get("name"));
			System.out.println(object.get("age"));
		}
	}

}
