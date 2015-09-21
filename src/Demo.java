
import org.json.JSONObject;
import com.youtu.*;

public class Demo {

	// appid, secretid secretkey请到http://open.youtu.qq.com/注册
	// 请把下面的APP_ID、SECRET_ID和SECRET_KEY换成你自己的数据，下面的数据已经不可用
	public static final String APP_ID = "123456";
	public static final String SECRET_ID = "AKIDyyasNMe2rDZs82axRhPx379AZfNA2oL2";
	public static final String SECRET_KEY = "stDqHJa7NN36ZTxF4HzPaIClCIX1xlWw";

	public static void main(String[] args) {

		try {
			Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_YOUTU_END_POINT);
			JSONObject respose;
			//respose= faceYoutu.FaceCompareUrl("http://open.youtu.qq.com/content/img/slide-1.jpg","http://open.youtu.qq.com/content/img/slide-1.jpg");
			respose = faceYoutu.DetectFace("test.jpg",1);
			//get respose 
			System.out.println(respose);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
