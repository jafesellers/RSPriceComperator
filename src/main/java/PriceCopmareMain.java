import java.io.InputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PriceCopmareMain {
	public static void main(String[] args){
		ObjectMapper mapper = new ObjectMapper();
		try {
			InputStream file = PriceCopmareMain.class.getResourceAsStream("user.json");
			User user = mapper.readValue(file, User.class);
			System.out.println("FIRST NAME: " + user.getName().getFirst());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
