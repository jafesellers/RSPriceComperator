package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import JsonObjects.ItemDetailed;

public class ItemBuilder extends Thread {
	private static ObjectMapper mapper = new ObjectMapper();
	int item;
	int location;
	ItemDetailed[] items;
	public ItemBuilder(int location,int itemNumber,ItemDetailed[] list) {
		super();
		this.item=itemNumber;
		this.location = location;
		this.items = list;
	}
	public void run() {
		System.out.println(this.item+"starting processing");
		try {
			URL url = getDetailedURL();
			ItemDetailed detailed=getDetailed(url);
			items[location]=detailed;
			detailed.init();
			detailed.item.getImages();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private URL getDetailedURL() {
		URL url = null;
		try {
			url = new URL("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item="+
		String.valueOf(this.item));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	private ItemDetailed getDetailed(URL url) {
		ItemDetailed item = null;
		try {
			item = mapper.readValue(url, ItemDetailed.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println(this.item+" failed to map properly trying again in 10 seconds");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return getDetailed(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
}
