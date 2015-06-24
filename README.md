# TencentYoutuyun-person-face-service
java sdk for [腾讯优图云人脸服务](http://open.youtu.qq.com/)  

How to start
----------------------------------- 

### 1. 在[腾讯优图](http://open.youtu.qq.com/)申请业务的授权
授权包括：
        
    APP_ID 
    SECRET_ID
    SECRET_KEY

### 2. 在你的项目里引入本项目dist目录下的json.jar和youtu-java-sdk.jar包或者直接引入源码  

    
### 3. 导入包并创建Youtu对象，然后调用相应方法

    import org.json.JSONObject;
    import com.youtu.*; 
    // 请把下面的APP_ID、SECRET_ID和SECRET_KEY换成你自己的数据，下面的数据已经不可用
    public static final String APP_ID = "1000234";
    public static final String SECRET_ID = "AKIDUIsdfDlPDt5mZutfr46sdNT0GisFcQh1nMOox";
    public static final String SECRET_KEY = "ind5yAd55ZspBc7MCANcxsdEjuXi8YU8RL";

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