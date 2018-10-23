package testing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import JsonObjects.Graph;

public class testGraphMapping {
	private static ObjectMapper mapper = new ObjectMapper();
	public static void main(String args[]) {
		URL url = null;
		Graph graph = null;
		try {
			url = new URL("http://services.runescape.com/m=itemdb_rs/api/graph/21787.json");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			graph = mapper.readValue(url, Graph.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(graph.daily.get(graph.daily.keySet().toArray()[0]));
	}

}
