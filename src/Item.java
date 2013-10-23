
public class Item {
	private String icon, typeIcon, name, description, icon_large;
	private int id;
	private Current currentPrice;
	private Today todaysTrend;
	
	public static class Current{
		private String trend, price;
		public String getTrend(){
			return this.trend;
		}
		public String getPrice(){
			return this.price;
		}
	}
	public static class Today{
		private String trend;
		private int price;
		public String getTrend(){
			return this.trend;
		}
		public int getPrice(){
			return this.price;
		}
	}
	public String getIcon(){
		return this.icon;
	}
	public String getTypeIcon(){
		return this.typeIcon;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	public String getIconLarge(){
		return this.icon_large;
	}
	public int getId(){
		return this.id;
	}
	public Current getCurrentPrice(){
		return this.currentPrice;
	}
	public Today getTodaysTrend(){
		return this.todaysTrend;
	}
}
