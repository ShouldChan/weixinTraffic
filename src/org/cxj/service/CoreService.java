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
 * 核心服务类
 * @author cxj
 * @date1 2016-03-30
 * @date2 2016-04-13  修改实现业务逻辑
 * */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		//默认返回文本消息内容
		String respContent=null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("您好，欢迎关注交警微信服务平台！");
					// 将消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("jgwdcx")) {
						//交管网点查询
						Article article = new Article();
						article.setTitle("交管网点查询");
						article.setDescription("镇江车管所地处镇江市丹徒区辛丰镇石城村，位于338省道216公里处，于2007年9月迁建新址对外办公。\n\n车管所现为正科级建制，民警44人，建有综合楼、机动车业务办证大楼、驾驶证业务办证大厅、650亩驾驶人训考场。");
						article.setPicUrl("http://ezanmydream-jgwdcx.stor.sinaapp.com/SJS_8378111111.jpg");
						article.setUrl("http://lemonyo.duapp.com/jgwdcx.html");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("jgzx")) {
						//交管咨询
						textMessage.setContent("车管所对号牌安装相关问题的提示\n"+"1. 号牌安装究竟要不要装号牌框？\n"+"答：不提倡使用号牌框安装号牌。因为国产机动车一般都预留有相当的号牌安装位置，只有少部分进口车辆由于预留的位置比我国的号牌尺寸偏小，才使用号牌架安装号牌。市面上大部分使用的牌照框只是起到美观装饰的作用，并不能起到保护作用，相反使用牌照框安装号牌更容易整体拆卸，换句话说更容易被盗。同时，部分车辆每块号牌安装位置已具备4个螺丝的安装孔，但由于市面上的牌照框大多只有2个安装孔，反而4个螺丝安装不上。"+"2. 车子已上了牌，并安装了号牌框，要立即拆掉吗？\n"+"答：使用规范的号牌框安装的号牌，需保证牌架内侧边缘距离号牌的字符边缘要大于5mm，且保证号牌无任何变形和遮盖，保证号牌横向水平，纵向基本垂直于地面，纵向夹角≤15°的，可暂无需立即拆掉号牌框。牌照框上如印有销售单位名称、电话的并不影响使用。");
						respXml = MessageUtil.messageToXml(textMessage);
					}else if(eventKey.equals("zbss")){
						// 周边搜索
						textMessage.setContent("您是否有过出门在外四处寻找ATM或厕所的经历\n\n？您是否有过出差在外搜寻美食或娱乐场所的经历？\n\n周边搜索为您保驾护航，为您提供专业的周边生活指南，回复“附近”开始体验吧！");
						respXml = MessageUtil.messageToXml(textMessage);
						
					}else if(eventKey.equals("mncz")){
						//模拟操作
						textMessage.setContent("当前是体验模式，仅供模拟操作，不作为事故依据。\n欢迎你使用镇江市事故处理网上服务中心自助服务系统！\n服务时间：7：00-19：00。\n服务专线：0511-xxxxxxxx\n按照跳转界面的提示输入事故信息即可。<a href='http://lemonyo.duapp.com/accident_monisend.html'>我们一起进行模拟操作！</a>");
						respXml=MessageUtil.messageToXml(textMessage);
					}
				}
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				String content=requestMap.get("Content").trim();
				if(content.equals("附近")){
					respContent=getUsage();
				}
				//周边搜索
				else if(content.startsWith("附近")){
					String keyWord=content.replace("附近", "").trim();
					//获取用户最后一次发送的地理位置
					UserLocation location=
							MySQLUtil.getLastLocation(request, fromUserName);
					//未获取到
					if(null==location){
						respContent=getUsage();
					}else{
						//根据转换后（纠偏）的坐标搜索周边POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
						// 未搜索到POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format("/猪头，您发送的位置附近未搜索到“%s”信息！", keyWord);
						} else {
							List<Article> articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
							// 回复图文消息
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
				// 用户发送的经纬度
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// 坐标转换后的经纬度
				String bd09Lng = null;
				String bd09Lat = null;
				// 调用接口转换坐标
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// 保存用户地理位置
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat, bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				buffer.append("[阴险]").append("成功接收您的位置！").append("\n\n");
				buffer.append("您可以输入搜索关键词获取周边信息了，例如：").append("\n");
				buffer.append("        附近ATM").append("\n");
				buffer.append("        附近KTV").append("\n");
				buffer.append("        附近厕所").append("\n");
				buffer.append("必须以“附近”两个字开头！");
				respContent = buffer.toString();
			}
			/*// 当用户发消息时
			else{
				textMessage.setContent("请通过菜单使用交警微信服务功能！");
				respXml = MessageUtil.messageToXml(textMessage);
			}*/
			if(null!=respContent){
				//设置文本消息内容
				textMessage.setContent(respContent);
				//将文本消息对象转换成XML
				respXml=MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	/*
	 * 使用说明
	 * @return 
	 * */
	private static String getUsage(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("周边搜索使用说明").append("\n\n");
		buffer.append("1)发送地理位置").append("\n");
		buffer.append("点击窗口底部的“+”按钮，选择“位置”，点“发送”").append("\n\n");
		buffer.append("2)指定关键词搜索").append("\n");
		buffer.append("格式：附近+关键词\n例如：附近ATM、附近KTV、附近厕所");
		return buffer.toString();
	}
}
