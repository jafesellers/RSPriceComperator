package JsonObjects;

import java.util.List;

public class ItemDetailed {
	public String total;
	public Item item;
	public void init() {
		this.item.init();
	}
}