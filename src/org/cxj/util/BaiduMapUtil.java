package org.cxj.util;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONObject;

import org.cxj.message.resp.Article;
import org.cxj.pojo.BaiduPlace;
import org.cxj.pojo.UserLocation;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/*
 * �ٶȵ�ͼ������
 * @author cxj
 * @date 2016-04-14
 * */
public class BaiduMapUtil {
	/*
	 * Բ���������
	 * 
	 * @param query �����ؼ���
	 * @param lng ����
	 * @param lat γ��
	 * @return List<BaiduPlace>
	 * @throws UnsupportedEncodingException
	 * */

	public static List<BaiduPlace> searchPlace(String query, String lng, String lat) throws Exception {
		// ƴװ�����ַ
		String requestUrl = "http://api.map.baidu.com/place/v2/search?&query=QUERY&location=LAT,LNG&radius=2000&output=xml&scope=2&page_size=10&page_num=0&ak=gxaD8jNy91I3KZx3TsNFhnMRK1bHnoUc";
		requestUrl = requestUrl.replace("QUERY", URLEncoder.encode(query, "UTF-8"));
		requestUrl = requestUrl.replace("LAT", lat);
		requestUrl = requestUrl.replace("LNG", lng);
		// ����Place APIԲ���������
		String respXml = httpRequest(requestUrl);
		// �������ص�xml
		List<BaiduPlace> placeList = parsePlaceXml(respXml);
		return placeList;
	}
	/**
	 * ����http����
	 * 
	 * @param requestUrl �����ַ
	 * @return String
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	/**
	 * ���ݰٶȵ�ͼ���ص�����������ַ��Ϣ
	 * 
	 * @param inputStream ������
	 * @return List<BaiduPlace>
	 */
	@SuppressWarnings("unchecked")
	private static List<BaiduPlace> parsePlaceXml(String xml) {
		List<BaiduPlace> placeList = null;
		try {
			Document document = DocumentHelper.parseText(xml);
			// �õ�xml��Ԫ��
			Element root = document.getRootElement();
			// �Ӹ�Ԫ�ػ�ȡ<results>
			Element resultsElement = root.element("results");
			// ��<results>�л�ȡ<result>����
			List<Element> resultElementList = resultsElement.elements("result");
			// �ж�<result>���ϵĴ�С
			if (resultElementList.size() > 0) {
				placeList = new ArrayList<BaiduPlace>();
				// POI����
				Element nameElement = null;
				// POI��ַ��Ϣ
				Element addressElement = null;
				// POI��γ������
				Element locationElement = null;
				// POI�绰��Ϣ
				Element telephoneElement = null;
				// POI��չ��Ϣ
				Element detailInfoElement = null;
				// �������ĵ�ľ���
				Element distanceElement = null;
				// ����<result>����
				for (Element resultElement : resultElementList) {
					nameElement = resultElement.element("name");
					addressElement = resultElement.element("address");
					locationElement = resultElement.element("location");
					telephoneElement = resultElement.element("telephone");
					detailInfoElement = resultElement.element("detail_info");

					BaiduPlace place = new BaiduPlace();
					place.setName(nameElement.getText());
					place.setAddress(addressElement.getText());
					place.setLng(locationElement.element("lng").getText());
					place.setLat(locationElement.element("lat").getText());
					// ��<telephone>Ԫ�ش���ʱ��ȡ�绰����
					if (null != telephoneElement)
						place.setTelephone(telephoneElement.getText());
					// ��<detail_info>Ԫ�ش���ʱ��ȡ<distance>
					if (null != detailInfoElement) {
						distanceElement = detailInfoElement.element("distance");
						if (null != distanceElement)
							place.setDistance(Integer.parseInt(distanceElement.getText()));
					}
					placeList.add(place);
				}
				// �������ɽ���Զ����
				Collections.sort(placeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return placeList;
	}
	/**
	 * ����Place��װͼ���б�
	 * 
	 * @param placeList
	 * @param bd09Lng ����
	 * @param bd09Lat γ��
	 * @return List<Article>
	 */
	public static List<Article> makeArticleList(List<BaiduPlace> placeList, String bd09Lng, String bd09Lat) {
		// ��Ŀ�ĸ�·��
		String basePath = "http://1.justdemo.sinaapp.com/";
		List<Article> list = new ArrayList<Article>();
		BaiduPlace place = null;
		for (int i = 0; i < placeList.size(); i++) {
			place = placeList.get(i);
			Article article = new Article();
			article.setTitle(place.getName() + "\n����Լ" + place.getDistance() + "��");
			// P1��ʾ�û����͵�λ�ã�����ת���󣩣�p2��ʾ��ǰPOI����λ��
			article.setUrl(String.format(basePath + "route.jsp?p1=%s,%s&p2=%s,%s", bd09Lng, bd09Lat, place.getLng(), place.getLat()));
			// ������ͼ�ĵ�ͼƬ����Ϊ��ͼ
			if (i == 0)
				article.setPicUrl(basePath + "images/poisearch.png");
			else
				article.setPicUrl(basePath + "images/navi.png");
			list.add(article);
		}
		return list;
	}

	/**
	 * ��΢�Ŷ�λ������ת���ɰٶ����꣨GCJ-02 -> Baidu��
	 * 
	 * @param lng ����
	 * @param lat γ��
	 * @return UserLocation
	 */
	public static UserLocation convertCoord(String lng, String lat) {
		// �ٶ�����ת���ӿ�
		String convertUrl = "http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x={x}&y={y}";
		convertUrl = convertUrl.replace("{x}", lng);
		convertUrl = convertUrl.replace("{y}", lat);

		UserLocation location = new UserLocation();
		try {
			String jsonCoord = httpRequest(convertUrl);
			JSONObject jsonObject = JSONObject.fromObject(jsonCoord);
			// ��ת������������Base64����
			location.setBd09Lng(Base64.decode(jsonObject.getString("x"), "UTF-8"));
			location.setBd09Lat(Base64.decode(jsonObject.getString("y"), "UTF-8"));
		} catch (Exception e) {
			location = null;
			e.printStackTrace();
		}
		return location;
	}
}
