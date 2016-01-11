
import org.json.JSONObject;
import com.youtu.*;

public class Demo {

	// appid, secretid secretkey请到http://open.youtu.qq.com/注册
	// 请把下面的APP_ID、SECRET_ID和SECRET_KEY换成你自己的数据，下面的数据已经不可用
	
	public static final String APP_ID = "1006168";
	public static final String SECRET_ID = "AKIDKG9lStYOPFxoIKopIIwNkgCictcMOcRh";
	public static final String SECRET_KEY = "Kl2ljF7q4l0m0mNCxTnzJQfTlWVHUOf3";
	public static final String USER_ID = "2127322016";
	
	public static void main(String[] args) {

		try {
			Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, USER_ID, Youtu.API_YOUTU_END_POINT);
			JSONObject respose;
			//respose= faceYoutu.FaceCompareUrl("http://open.youtu.qq.com/content/img/slide-1.jpg","http://open.youtu.qq.com/content/img/slide-1.jpg");
			respose = faceYoutu.DetectFace("a.jpg",1);
			//get respose 
			System.out.println(respose);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
