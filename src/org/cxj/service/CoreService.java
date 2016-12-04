package org.cxj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cxj.message.resp.Article;
import org.cxj.message.resp.NewsMessage;
import org.cxj.message.resp.TextMessage;
import org.cxj.pojo.BaiduPlace;
import org.cxj.pojo.UserLocation;
import org.cxj.util.BaiduMapUtil;
import org.cxj.util.MessageUtil;
import org.cxj.util.MySQLUtil;


/*
 * ���ķ�����
 * @author cxj
 * @date1 2016-03-30
 * @date2 2016-04-13  �޸�ʵ��ҵ���߼�
 * */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		//Ĭ�Ϸ����ı���Ϣ����
		String respContent=null;
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// �¼�����
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("���ã���ӭ��ע����΢�ŷ���ƽ̨��");
					// ����Ϣ����ת����xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO �ݲ�������
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					// ����keyֵ�ж��û�����İ�ť
					if (eventKey.equals("jgwdcx")) {
						//���������ѯ
						Article article = new Article();
						article.setTitle("���������ѯ");
						article.setDescription("�򽭳������ش����е�ͽ��������ʯ�Ǵ壬λ��338ʡ��216���ﴦ����2007��9��Ǩ����ַ����칫��\n\n��������Ϊ���Ƽ����ƣ���44�ˣ������ۺ�¥��������ҵ���֤��¥����ʻ֤ҵ���֤������650Ķ��ʻ��ѵ������");
						article.setPicUrl("http://ezanmydream-jgwdcx.stor.sinaapp.com/SJS_8378111111.jpg");
						article.setUrl("http://lemonyo.duapp.com/jgwdcx.html");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// ����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("jgzx")) {
						//������ѯ
						textMessage.setContent("�������Ժ��ư�װ����������ʾ\n"+"1. ���ư�װ����Ҫ��Ҫװ���ƿ�\n"+"�𣺲��ᳫʹ�ú��ƿ�װ���ơ���Ϊ����������һ�㶼Ԥ�����൱�ĺ��ư�װλ�ã�ֻ���ٲ��ֽ��ڳ�������Ԥ����λ�ñ��ҹ��ĺ��Ƴߴ�ƫС����ʹ�ú��Ƽܰ�װ���ơ������ϴ󲿷�ʹ�õ����տ�ֻ��������װ�ε����ã��������𵽱������ã��෴ʹ�����տ�װ���Ƹ����������ж�����仰˵�����ױ�����ͬʱ�����ֳ���ÿ����ư�װλ���Ѿ߱�4����˿�İ�װ�ף������������ϵ����տ���ֻ��2����װ�ף�����4����˿��װ���ϡ�"+"2. �����������ƣ�����װ�˺��ƿ�Ҫ���������\n"+"��ʹ�ù淶�ĺ��ƿ�װ�ĺ��ƣ��豣֤�Ƽ��ڲ��Ե������Ƶ��ַ���ԵҪ����5mm���ұ�֤�������κα��κ��ڸǣ���֤���ƺ���ˮƽ�����������ֱ�ڵ��棬����нǡ�15��ģ�������������������ƿ����տ�����ӡ�����۵�λ���ơ��绰�Ĳ���Ӱ��ʹ�á�");
						respXml = MessageUtil.messageToXml(textMessage);
					}else if(eventKey.equals("zbss")){
						// �ܱ�����
						textMessage.setContent("���Ƿ��й����������Ĵ�Ѱ��ATM������ľ���\n\n�����Ƿ��й�����������Ѱ��ʳ�����ֳ����ľ�����\n\n�ܱ�����Ϊ�����ݻ�����Ϊ���ṩרҵ���ܱ�����ָ�ϣ��ظ�����������ʼ����ɣ�");
						respXml = MessageUtil.messageToXml(textMessage);
						
					}else if(eventKey.equals("mncz")){
						//ģ�����
						textMessage.setContent("��ǰ������ģʽ������ģ�����������Ϊ�¹����ݡ�\n��ӭ��ʹ�������¹ʴ������Ϸ���������������ϵͳ��\n����ʱ�䣺7��00-19��00��\n����ר�ߣ�0511-xxxxxxxx\n������ת�������ʾ�����¹���Ϣ���ɡ�<a href='http://lemonyo.duapp.com/accident_monisend.html'>����һ�����ģ�������</a>");
						respXml=MessageUtil.messageToXml(textMessage);
					}
				}
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				String content=requestMap.get("Content").trim();
				if(content.equals("����")){
					respContent=getUsage();
				}
				//�ܱ�����
				else if(content.startsWith("����")){
					String keyWord=content.replace("����", "").trim();
					//��ȡ�û����һ�η��͵ĵ���λ��
					UserLocation location=
							MySQLUtil.getLastLocation(request, fromUserName);
					//δ��ȡ��
					if(null==location){
						respContent=getUsage();
					}else{
						//����ת���󣨾�ƫ�������������ܱ�POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
						// δ������POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format("/��ͷ�������͵�λ�ø���δ��������%s����Ϣ��", keyWord);
						} else {
							List<Article> articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
							// �ظ�ͼ����Ϣ
							NewsMessage newsMessage = new NewsMessage();
							newsMessage.setToUserName(fromUserName);
							newsMessage.setFromUserName(toUserName);
							newsMessage.setCreateTime(new Date().getTime());
							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
							newsMessage.setArticles(articleList);
							newsMessage.setArticleCount(articleList.size());
							respXml = MessageUtil.messageToXml(newsMessage);
						}
					}
				}else
					respContent=getUsage();
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// �û����͵ľ�γ��
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// ����ת����ľ�γ��
				String bd09Lng = null;
				String bd09Lat = null;
				// ���ýӿ�ת������
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// �����û�����λ��
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat, bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				buffer.append("[����]").append("�ɹ���������λ�ã�").append("\n\n");
				buffer.append("���������������ؼ��ʻ�ȡ�ܱ���Ϣ�ˣ����磺").append("\n");
				buffer.append("        ����ATM").append("\n");
				buffer.append("        ����KTV").append("\n");
				buffer.append("        ��������").append("\n");
				buffer.append("�����ԡ������������ֿ�ͷ��");
				respContent = buffer.toString();
			}
			/*// ���û�����Ϣʱ
			else{
				textMessage.setContent("��ͨ���˵�ʹ�ý���΢�ŷ����ܣ�");
				respXml = MessageUtil.messageToXml(textMessage);
			}*/
			if(null!=respContent){
				//�����ı���Ϣ����
				textMessage.setContent(respContent);
				//���ı���Ϣ����ת����XML
				respXml=MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	/*
	 * ʹ��˵��
	 * @return 
	 * */
	private static String getUsage(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("�ܱ�����ʹ��˵��").append("\n\n");
		buffer.append("1)���͵���λ��").append("\n");
		buffer.append("������ڵײ��ġ�+����ť��ѡ��λ�á����㡰���͡�").append("\n\n");
		buffer.append("2)ָ���ؼ�������").append("\n");
		buffer.append("��ʽ������+�ؼ���\n���磺����ATM������KTV����������");
		return buffer.toString();
	}
}
