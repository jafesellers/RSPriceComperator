import com.fasterxml.jackson.databind.ObjectMapper;

import GUI.MainGUI;
import JsonObjects.ItemDetailed;
import JsonObjects.Page;
import utils.ItemBuilder;

/**
 * @author jafes
 *
 */
public class ThreadedMain {
	private static final MainGUI GUI = new MainGUI();
	private static Page adamant;


	// { 0 , 1 , 2 , 3 , 4 , 5 , 6 }
	// makes an array for easy access
	// {set(lg),set(sk),legs,skirt,body,helmet,shield};
	private static final int KEY_BRONZE_ITEMS[] = { 11814, 11816, 1075, 1087, 1117, 1155, 1189 };
	private static final int KEY_STEEL_ITEMS[] = { 11822, 11824, 1069, 1083, 1119, 1157, 1193 };
	private static final int KEY_IRON_ITEMS[] = { 11818, 11820, 1067, 1081, 1115, 1153, 1191 };
	private static final int KEY_BLACK_ITEMS[] = { 11826, 11828, 1077, 1089, 1125, 1165, 1195 };
	private static final int KEY_MITHRIL_ITEMS[] = { 11830, 11832, 1071, 1085, 1121, 1159, 1197 };
	private static final int KEY_ADAMANT_ITEMS[] = { 11834, 11834, 1073, 1091, 1123, 1161, 1199 };
	private static final int KEY_RUNE_ITEMS[] = { 11838, 11840, 1079, 1093, 1127, 1163, 1201 };

	// main method starts everything running
	public static void main(String args[]) {
		// setupPageAdamant(GUI);
		setupSetsComparison(KEY_BRONZE_ITEMS);
		setupSetsComparison(KEY_STEEL_ITEMS);
		setupSetsComparison(KEY_IRON_ITEMS);
		setupSetsComparison(KEY_BLACK_ITEMS);
		setupSetsComparison(KEY_MITHRIL_ITEMS);
		setupSetsComparison(KEY_ADAMANT_ITEMS);
		setupSetsComparison(KEY_RUNE_ITEMS);

		GUI.displayImage();
	}

	private static void setupSetsComparison(int[] ids) {
		ItemDetailed items[] = new ItemDetailed[ids.length];
		Thread threads[]= new Thread[ids.length];
		for(int i =0;i<ids.length;i++) {
			ItemBuilder item = new ItemBuilder(i,ids[i],items);
			item.start();
			threads[i]=item;
		}
		for(int i=0; i<threads.length;i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0; i<items.length;i++) {
			GUI.addLabel(items[i].item.image_icon_large,String.valueOf(items[i].item.current.getPriceDouble()));
		}
		int skPrice = items[1].item.getGraphPrice()-items[3].item.getGraphPrice()+items[2].item.getGraphPrice();
		int lgPrice = items[0].item.getGraphPrice()-items[2].item.getGraphPrice()+items[3].item.getGraphPrice();
		//int skPrice = items[1].item.current.getPriceDouble()-items[3].item.current.getPriceDouble()+items[2].item.current.getPriceDouble();
		//int lgPrice = items[0].item.current.getPriceDouble()-items[2].item.current.getPriceDouble()+items[3].item.current.getPriceDouble();
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
}
