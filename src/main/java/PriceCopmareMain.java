
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import GUI.MainGUI;
import JsonObjects.ItemDetailed;
import JsonObjects.Page;

public class PriceCopmareMain {
	private static final MainGUI GUI = new MainGUI();
	private static Page adamant;

	// mapper is core jsons to object parsing
	private static ObjectMapper mapper = new ObjectMapper();
	
	//                                                  {   0   ,  1    , 2  ,  3  ,  4 ,  5   , 6    }  
	// makes an array for easy access                   {set(lg),set(sk),legs,skirt,body,helmet,shield};
	private static final int KEY_BRONZE_ITEMS[]       = {11814  ,11816  ,1075,1087 ,1117,1155  ,1189  };
	private static final int KEY_STEEL_ITEMS[]        = {11822  ,11824  ,1069,1083 ,1119,1157  ,1193  };
	private static final int KEY_IRON_ITEMS[]         = {11818  ,11820  ,1067,1081 ,1115,1153  ,1191  };
	private static final int KEY_BLACK_ITEMS[]        = {11826  ,11828  ,1077,1089 ,1125,1165  ,1195  };
	private static final int KEY_MITHRIL_ITEMS []     = {11830  ,11832  ,1071,1085 ,1121,1159  ,1197  };
	private static final int KEY_ADAMANT_ITEMS []     = {11834  ,11836  ,1073,1091 ,1123,1161  ,1199  };
	private static final int KEY_RUNE_ITEMS []        = {11838  ,11840  ,1079,1093 ,1127,1163  ,1201  };
	
	// main method starts everything running
	public static void main(String args[]) {
		//setupPageAdamant(GUI);
		setupSetsComparison(GUI, KEY_BRONZE_ITEMS);
		setupSetsComparison(GUI, KEY_STEEL_ITEMS);
		setupSetsComparison(GUI, KEY_IRON_ITEMS);
		setupSetsComparison(GUI, KEY_BLACK_ITEMS);
		setupSetsComparison(GUI, KEY_MITHRIL_ITEMS);
		setupSetsComparison(GUI,KEY_ADAMANT_ITEMS);
		setupSetsComparison(GUI, KEY_RUNE_ITEMS);
		
		GUI.displayImage();
	}
	

	private static void setupSetsComparison(MainGUI GUI,int[] ids) {
		// array to contain items for latter access
		ItemDetailed items[] = new ItemDetailed[ids.length];
		for(int i =0;i<ids.length;i++) {
			URL url = getDetailedURL(ids[i]);
			ItemDetailed detailed=getDetailed(url);
			items[i]=detailed;
			detailed.item.getImages();
			GUI.addLabel(detailed.item.image_icon_large,String.valueOf(detailed.item.current.getPriceDouble()));
		}
		double skPrice = items[1].item.current.getPriceDouble()-items[3].item.current.getPriceDouble()+items[2].item.current.getPriceDouble();
		double lgPrice = items[0].item.current.getPriceDouble()-items[2].item.current.getPriceDouble()+items[3].item.current.getPriceDouble();
		String skText ="SK difference " + String.valueOf(skPrice);
		String lgText ="LG difference " + String.valueOf(lgPrice);
		String comparison="";
		if(skPrice>lgPrice) {
			comparison = comparison + "buy lg sets. profit = "+String.valueOf(skPrice-lgPrice)+" Per set";
		}else if (lgPrice>skPrice) {
			comparison = comparison + "buy lg sets. profit = "+String.valueOf(lgPrice-skPrice)+" Per set";
		}else {
			comparison = comparison+"Don't buy the prices are the same";
		}
		GUI.addLabel(skText);
		GUI.addLabel(lgText);
		GUI.addLabel(comparison);
		
	}
	

	private static void setupPageAdamant(MainGUI GUI) {
		URL urlAdamant1 = getItemSearchURL("19", "adamant", "1");
		URL urlAdamant2 = getItemSearchURL("19", "adamant", "2");
		URL urlAdamant3 = getItemSearchURL("19", "adamant", "3");
		URL urlAdamant4 = getItemSearchURL("19", "adamant", "4");
		URL urlAdamant5 = getItemSearchURL("19", "adamant", "5");
		adamant = getPage(urlAdamant1);
		getImages();
		adamant = getPage(urlAdamant2);
		getImages();
		adamant = getPage(urlAdamant3);
		getImages();
		adamant = getPage(urlAdamant4);
		getImages();
		adamant = getPage(urlAdamant5);
		getImages();
	}
	private static Page getPage(URL url) {		
		Page page = null;

		// have mapper retrive the core information from the runescape api
		try {
			page = mapper.readValue(url, Page.class);
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
		return page;
	}

	private static ItemDetailed getDetailed(URL url) {	
		ItemDetailed item = null;
		try {
			item = mapper.readValue(url, ItemDetailed.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("failed to map properly trying again in .5 seconds");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
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

	// has the Item class pull there respective icons down
	private static void getImages() {
		for (int i = 0; i < adamant.items.length; i++) {
			adamant.items[i].getImages();
			// GUI.addIcon(adamant.items[i].getImageIconLarge());
			GUI.addButton(adamant.items[i].getImageIconLarge(), adamant.items[i].name+" "+String.valueOf(adamant.items[i].id));

		}
	}

	private static URL getItemSearchURL(String catagory, String name, String page) {
		URL url = null;
		try {
			url = new URL("http://services.runescape.com/m=itemdb_rs/api/catalogue/items.json?category=" + catagory
					+ "&alpha=" + name + "&page=" + page);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	private static URL getDetailedURL(int id) {
		URL url = null;
		try {
			url = new URL("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item="+
		String.valueOf(id));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
}
