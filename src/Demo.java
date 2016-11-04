
import org.json.JSONObject;
import com.youtu.*;

public class Demo {

	// appid, secretid secretkey请到http://open.youtu.qq.com/获取
	// 请正确填写把下面的APP_ID、SECRET_ID和SECRET_KEY
	public static final String APP_ID = "";
	public static final String SECRET_ID = "";
	public static final String SECRET_KEY = "";
	public static final String USER_ID = "";

	public static void main(String[] args) {

		try {
			Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_YOUTU_END_POINT,USER_ID);
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
