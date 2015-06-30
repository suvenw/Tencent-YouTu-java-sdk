
import org.json.JSONObject;
import com.youtu.*;

public class Demo {

	// appid, secretid secretkey请到http://open.youtu.qq.com/注册
	// 请把下面的APP_ID、SECRET_ID和SECRET_KEY换成你自己的数据，下面的数据已经不可用
	public static final String APP_ID = "1000054";
	public static final String SECRET_ID = "AKIDqs3ba9ki8BvvswsqsDQw8d6rSSRZvcXn";
	public static final String SECRET_KEY = "Umig8oZlynO77s9H7q19W01kzP7qnMNq";

	public static void main(String[] args) {

		try {
			Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY);
			
			JSONObject respose = faceYoutu.DetectFace("test.jpg");
			//get respose 
			System.out.println(respose);
			//get detail info
			if(respose.getInt("errorcode")==0){
				
				System.out.println(respose.get("image_height"));
				System.out.println(respose.get("face"));
				System.out.println(respose.getJSONArray("face").getJSONObject(0).get("yaw"));
				System.out.println(respose.getInt("errorcode"));
				System.out.println(respose.get("errormsg"));
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
