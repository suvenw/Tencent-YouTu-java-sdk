package com.youtu;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.youtu.sign.*;
import java.io.IOException;

/**
 * 
 * @author tyronetao
 */
public class Youtu {
	public final static  String API_YOUTU_END_POINT = "http://api.youtu.qq.com/youtu/api/";
	public final static String API_TENCENTYUN_END_POINT = "http://youtu.api.qcloud.com/youtu/api/";
	// 30 days
	protected static int EXPIRED_SECONDS = 2592000;
	protected String m_appid;
	protected String m_secret_id;
	protected String m_secret_key;
	protected String m_end_point;
	/**
	 * PicCloud 构造方法
	 * 
	 * @param appid
	 *            授权appid
	 * @param secret_id
	 *            授权secret_id
	 * @param secret_key
	 *            授权secret_key
	 */
	public Youtu(String appid, String secret_id, String secret_key,String end_point) {
		m_appid = appid;
		m_secret_id = secret_id;
		m_secret_key = secret_key;
		m_end_point=end_point;
	}

	private void GetBase64FromFile(String filePath, StringBuffer base64)
			throws IOException {
		File imageFile = new File(filePath);
		if (imageFile.exists()) {
			InputStream in = new FileInputStream(imageFile);
			byte data[] = new byte[(int) imageFile.length()]; // 创建合适文件大小的数组
			in.read(data); // 读取文件中的内容到b[]数组
			in.close();
			base64.append(Base64Util.encode(data));

		} else {
			throw new FileNotFoundException(filePath + " not exist");
		}

	}
	
	private JSONObject SendRequest(JSONObject postData, String mothod)
			throws IOException, JSONException {

		StringBuffer mySign = new StringBuffer("");
		YoutuSign
				.appSign(m_appid, m_secret_id, m_secret_key,
						System.currentTimeMillis() / 1000 + EXPIRED_SECONDS,
						"", mySign);

		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		URL url = new URL(m_end_point + mothod);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// set header
		connection.setRequestMethod("POST");
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("Host", "api.youtu.qq.com");
		connection.setRequestProperty("user-agent", "youtu-java-sdk");
		connection.setRequestProperty("Authorization", mySign.toString());

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/json");
		connection.connect();

		// POST请求
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());

		postData.put("app_id", m_appid);
		out.write(postData.toString().getBytes("utf-8"));
		out.flush();
		out.close();

		// 读取响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String lines;
		StringBuffer resposeBuffer = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			resposeBuffer.append(lines);
		}
		// System.out.println(resposeBuffer+"\n");
		reader.close();
		// 断开连接
		connection.disconnect();

		JSONObject respose = new JSONObject(resposeBuffer.toString());

		return respose;

	}

	public JSONObject DetectFace(String image_path,int mode) throws IOException,
			JSONException {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		data.put("mode", mode);
		JSONObject respose = SendRequest(data, "detectface");

		return respose;
	}
	
	public JSONObject FaceShape(String image_path,int mode) throws IOException,
			JSONException  {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		data.put("mode", mode);
		JSONObject respose = SendRequest(data, "faceshape");

		return respose;
    }
	
	public JSONObject FaceCompare(String image_path_a, String image_path_b)
			throws IOException, JSONException {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path_a, image_data);
		data.put("imageA", image_data.toString());
		image_data.setLength(0);

		GetBase64FromFile(image_path_b, image_data);
		data.put("imageB", image_data.toString());
		//URL url = new URL(API_YOUTU_END_POINT + "facecompare");
		JSONObject respose = SendRequest(data, "facecompare");

		return respose;
	}

	public JSONObject FaceVerify(String image_path, String person_id)
			throws IOException, JSONException {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		image_data.setLength(0);

		data.put("person_id", person_id);

		JSONObject respose = SendRequest(data, "faceverify");

		return respose;
	}

	public JSONObject FaceIdentify(String image_path, String group_id)
			throws IOException, JSONException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		image_data.setLength(0);

		data.put("group_id", group_id);

		JSONObject respose = SendRequest(data, "faceidentify");

		return respose;
	}

	public JSONObject NewPerson(String image_path, String person_id,
			List<String> group_ids) throws IOException, JSONException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		image_data.setLength(0);

		data.put("person_id", person_id);
		data.put("group_ids", group_ids);

		JSONObject respose = SendRequest(data, "newperson");

		return respose;
	}

	public JSONObject DelPerson(String person_id) throws IOException,
			JSONException {

		JSONObject data = new JSONObject();

		data.put("person_id", person_id);

		JSONObject respose = SendRequest(data, "delperson");

		return respose;
	}

	public JSONObject AddFace(String person_id, List<String> image_path_arr)
			throws IOException, JSONException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();
		List<String> images = new ArrayList<String>();
		for (String image_path : image_path_arr) {
			image_data.setLength(0);
			GetBase64FromFile(image_path, image_data);
			images.add(image_data.toString());
		}

		data.put("images", images);
		image_data.setLength(0);

		data.put("person_id", person_id);

		JSONObject respose = SendRequest(data, "addface");

		return respose;
	}

	public JSONObject DelFace(String person_id, List<String> face_id_arr)
			throws IOException, JSONException {

		JSONObject data = new JSONObject();

		data.put("face_ids", face_id_arr);
		data.put("person_id", person_id);
		JSONObject respose = SendRequest(data, "delface");

		return respose;

	}

	public JSONObject SetInfo(String person_name, String person_id)
			throws IOException, JSONException {
		JSONObject data = new JSONObject();

		data.put("person_name", person_name);
		data.put("person_id", person_id);
		JSONObject respose = SendRequest(data, "setinfo");

		return respose;

	}

	public JSONObject GetInfo(String person_id) throws IOException,
			JSONException {
		JSONObject data = new JSONObject();

		data.put("person_id", person_id);
		JSONObject respose = SendRequest(data, "getinfo");

		return respose;
	}

	public JSONObject GetGroupIds() throws IOException, JSONException {
		JSONObject data = new JSONObject();

		JSONObject respose = SendRequest(data, "getgroupids");

		return respose;
	}

	public JSONObject GetPersonIds(String group_id) throws IOException,
			JSONException {
		JSONObject data = new JSONObject();

		data.put("group_id", group_id);
		JSONObject respose = SendRequest(data, "getpersonids");

		return respose;
	}

	public JSONObject GetFaceIds(String person_id) throws IOException,
			JSONException {
		JSONObject data = new JSONObject();

		data.put("person_id", person_id);
		JSONObject respose = SendRequest(data, "getfaceids");

		return respose;
	}

	public JSONObject GetFaceInfo(String face_id) throws IOException,
			JSONException {
		JSONObject data = new JSONObject();

		data.put("face_id", face_id);
		JSONObject respose = SendRequest(data, "getfaceinfo");

		return respose;
	}

};