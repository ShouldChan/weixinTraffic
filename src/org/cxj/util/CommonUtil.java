package org.cxj.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.cxj.pojo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * ͨ�ù�����
 * @author cxj
 * @date 2016-04-12
 * */
public class CommonUtil {

	//ʹ��ָ�����ʼ����־����
	private static Logger log=LoggerFactory.getLogger(CommonUtil.class);
	
	//ƾ֤��ȡ��GET��
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/*
	 * ����https����
	 * @param requestUrl �����ַ
	 * @param requestMethod ����ʽ��GET��POST��
	 * @param outputStr �ύ������
	 * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 *   */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,
			String outputStr){
		JSONObject jsonObject=null;
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			conn.setRequestMethod(requestMethod);

			// ��outputStr��Ϊnullʱ�������д����
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// ע������ʽ
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// ����������ȡ��������
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// �ͷ���Դ
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("���ӳ�ʱ��{}", ce);
		} catch (Exception e) {
			log.error("https�����쳣��{}", e);
		}
				return jsonObject;
		
	}
	/*
	 * ��ȡ�ӿڷ���ƾ֤
	 * @param appid ƾ֤
	 * @param appsecret ��Կ
	 * @return 
	 * */
	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		// ����GET�����ȡƾ֤
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				token = null;
				// ��ȡtokenʧ��
				log.error("��ȡtokenʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
}
