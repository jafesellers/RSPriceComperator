import java.io.IOException;
import java.net.URL;

import GUI.MainGUI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PriceCopmareMain {
	private static MainGUI GUI;
	public static void main(String args[]){
		GUI = new MainGUI();
		ObjectMapper mapper = new ObjectMapper();
		try {
			URL GrandExchangeAPI = new URL("http://services.runescape.com/m=itemdb_rs/api/catalogue/items.json?category=19&alpha=a&page=1");
			//InputStream file = PriceCopmareMain.class.getResourceAsStream();
			JsonNode rootNode= mapper.readValue(GrandExchangeAPI,JsonNode.class);
			JsonNode itemNode = rootNode.path("items");
			URL icon = new URL(itemNode.get(0).path("icon_large").asText());
			GUI.setIcon(icon);
			GUI.displayImage();
			System.out.println("total: " + rootNode.path("total"));
			System.out.println("First Item: " + itemNode.get(0).path("name") + "\t"+  itemNode.get(0).path("current").get("price"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
