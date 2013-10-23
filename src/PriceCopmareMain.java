import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PriceCopmareMain {
	public static void main(String[] args){
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(new File("C:/Users/Jafe/workspace/RSPriceComperator/src/user.json"), User.class);
			System.out.println(user.getName().getFirst());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
