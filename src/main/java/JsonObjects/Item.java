package JsonObjects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Item {
	public String icon, type, typeIcon, name, description, icon_large;
	public int id;
	public Current current, today;
	public Change day30, day90, day180;
	public boolean members;
	public ImageIcon image_icon;
	public ImageIcon image_icon_large;
	private URL URL_icon_large;
	private URL URL_icon;
	private Graph graph;
	public void init() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.graph = mapper.readValue(new URL("http://services.runescape.com/m=itemdb_rs/api/graph/"+this.id+".json"), Graph.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public class Current {
		private String trend, price;

		public String getTrend() {
			return this.trend;
		}

		public String getPrice() {
			return this.price;
		}
		
		public int getPriceDouble() {
			double calculatedValue = 0;
			if (this.price.charAt(this.price.length() - 1) == 'm') {
				double temp = Double.valueOf(this.price.substring(0, this.price.length() - 1));
				calculatedValue = temp * 1000000;
			} else if (this.price.charAt(this.price.length() - 1) == 'k') {
				double temp = Double.valueOf(this.price.substring(0, this.price.length() - 1));
				calculatedValue = temp * 1000;
			}else if(this.price.contains(",")){
				String split[] = this.price.split(",");
				String combine = split[0]+split[1];
				calculatedValue = Double.valueOf(combine);
			}else {
				calculatedValue = Double.valueOf(this.getPrice());
			}
			return (int)calculatedValue;
		}
	}

	public class Change {
		private String trend, change;

		public String getTrend() {
			return this.trend;
		}

		public String getChange() {
			return this.change;
		}
	}

	public void getImages() {
		try {
			this.URL_icon = new URL(this.icon);
			this.URL_icon_large = new URL(this.icon_large);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.image_icon = new ImageIcon(URL_icon);
		this.image_icon_large = new ImageIcon(URL_icon_large);
	}

	public ImageIcon getImageIcon() {
		return this.image_icon;
	}

	public ImageIcon getImageIconLarge() {
		return this.image_icon_large;
	}
	
	public int getGraphPrice() {
		if (this.graph ==null) {
			return this.current.getPriceDouble();
		}
		return this.graph.daily.get(graph.daily.keySet().toArray()[0]);
	}
}
