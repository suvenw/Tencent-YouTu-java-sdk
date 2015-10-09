# tencentyun-youtu-java

java sdk for [腾讯云智能优图服务](http://www.qcloud.com/product/fr.html) & [腾讯优图开放平台](http://open.youtu.qq.com)

##名词

- `AppId` 平台添加应用后分配的AppId
- `SecretId` 平台添加应用后分配的SecretId
- `SecretKey` 平台添加应用后分配的SecretKey
- `签名` 接口鉴权凭证，由AppId、SecretId、SecretKey等生成，详见<http://open.youtu.qq.com/welcome/authentication>

##使用方法
```
1. 下载sdk到您的目录
	git clone https://github.com/TencentYouTu/java_sdk.git
2. 在你的项目里引入本项目dist目录下的json.jar和youtu-java-sdk.jar包或者直接引入源码
3. 导入包并创建Youtu对象，然后调用相应方法
```

## 使用示例
```
import org.json.JSONObject;
import com.youtu.*; 
// 请把下面的APP_ID、SECRET_ID和SECRET_KEY换成你自己的数据，下面的数据已经不可用
public static final String APP_ID = "1000234";
public static final String SECRET_ID = "AKIDUIsdfDlPDt5mZT0GisFcQh1nMOox";
public static final String SECRET_KEY = "ind5yAd55ZspBcjuXi8YU8RL";

//优图初始化方式
Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_YOUTU_END_POINT);
//腾讯云初始化方式;2种初始化方式选择一种即可，优图是免费提供给大家使用的
Youtu faceQcloud = new Youtu(APP_ID, SECRET_ID, SECRET_KEY,Youtu.API_TENCENTYUN_END_POINT);

//人脸检测调用示例
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
```



##SDK API介绍
接口调用统一返回值说明
- 返回值类型为 `JSONObject`，具体字段参考API文档

###人脸检测
- 接口
`JSONObject DetectFace(String image_path,int mode)`
`JSONObject DetectFaceUrl(String url, int mode)`
- 参数
	- `image_path` 待检测的图片路径
	- `url` 待检测的图片url
	-  `mode` 是否大脸模式，1为大脸、0为非大脸

###人脸配准
- 接口
`JSONObject FaceShape(String image_path,int mode)`
`JSONObject FaceShapeUrl(String url,int mode)`
- 参数
	- `image_path` 待检测的图片路径
	- `mode` 是否大脸模式，1为大脸、0为非大脸

###人脸比对
- 接口
`JSONObject FaceCompare(String image_path_a, String image_path_b)`
`JSONObject FaceCompareUrl(String urlA, String urlB)`
- 参数
	- `image_path_a` 待检测的图片A路径
	- `image_path_b` 待检测的图片B路径
	- `urlA` 待检测的图片A的url
	- `urlB` 待检测的图片B的url

###人脸验证
- 接口
`JSONObject FaceVerify(String image_path, String person_id)`
`JSONObject FaceVerifyUrl(String url, String person_id)`
- 参数
	- `person_id` 待验证的人脸id
	- `url` 待检测的图片url
	- `image_path` 待验证的图片路径

###人脸识别
- 接口
`JSONObject FaceIdentify(String image_path, String group_id)`
`JSONObject FaceIdentifyUrl(String url, String group_id)`
- 参数
	- `group_id` 待识别的组id
	- `url` 待检测的图片url
	- `image_path` 待识别的图片数据路径

###新建个体
- 接口
        `JSONObject NewPerson(String image_path, String person_id, List<String> group_ids)`
        `JSONObject NewPersonUrl(String url, String person_id,List<String> group_ids)`
- 参数
	- `person_id` 新建的个体id，用户指定，需要保证app_id下的唯一性
	- `person_name` 新建的个体名称
	- `group_ids` 新建的个体存放的组id，可以指定多个组id，用户指定（组默认创建）
	- `url` 待检测的图片url
	- `image_path` 包含个体人脸的图片路径
	- `tag` 备注信息，用户自解释字段

###删除个体
- 接口
`JSONObject DelPerson(String person_id)`
- 参数
	- `person_id` 待删除的个体id

###增加人脸
- 接口
`JSONObject AddFace(String person_id, List<String> image_path_arr)`
`JSONObject AddFaceUrl(String person_id, List<String> url_arr)`
- 参数
	- `person_id` 新增人脸的个体身份id
	- `url_arr` 待检测的图片url构成的数组
	- `image_path_arr` 待增加的包含人脸的图片数据的路径，可加入多张（总包体大小<2m）

###删除人脸
- 接口
`JSONObject DelFace(String person_id, List<String> face_id_arr)`
- 参数
	- `person_id` 待删除人脸的个体身份id
	- `face_id_arr` 待删除的人脸id集合

###获取信息
- 接口
`JSONObject GetInfo(String person_id)`
- 参数
	- `person_id` 待查询的个体身份id

###设置信息
- 接口
`JSONObject SetInfo(String person_name, String person_id)`
- 参数
	- `person_id` 待设置的个体身份id
	- `person_name` 新设置的个体名字

###获取组列表
- 接口
`JSONObject GetGroupIds()`
- 参数
	- 无
###获取个体列表
- 接口
`JSONObject GetPersonIds(String group_id)`
- 参数
	- `group_id` 待查询的组id

###获取人脸列表
- 接口
`JSONObject GetFaceIds(String person_id)`
- 参数
	- `person_id` 待查询的个体id

###获取人脸信息
- 接口
`JSONObject GetFaceInfo(String face_id)`
- 参数
	- `face_id` 待查询人脸的id

###判断一个图像的模糊程度
- 接口
`JSONObject FuzzyDetect(String image_path)`
`JSONObject FuzzyDetectUrl(String url)`
- 参数
	- `url` 待检测的图片url
	- `image_path` 待识别的图片数据路径

###识别一个图像是否为美食图像
- 接口
`JSONObject FoodDetect(String image_path)`
`JSONObject FoodDetectUrl(String url)`
- 参数
	- `url` 待检测的图片url
	- `image_path` 待识别的图片数据路径

###识别一个图像的标签信息,对图像分类
- 接口
`JSONObject ImageTag(String image_path)`
`JSONObject ImageTagUrl(String url)`
- 参数
	- `url` 待检测的图片url
	- `image_path` 待识别的图片数据路径

```
```
更多详情和文档说明参见
[腾讯云智能优图服务](http://www.qcloud.com/product/fr.html)
[腾讯优图开放平台](http://open.youtu.qq.com)