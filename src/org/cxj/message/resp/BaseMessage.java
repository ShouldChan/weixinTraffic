package org.cxj.message.resp;

/*
 * ��Ϣ���ࣨ�����˺�->��ͨ�û���
 * @author cxj
 * @date 2016-04-06
 * */
public class BaseMessage {

//	���շ��˺ţ�OpenId��
	private String ToUserName;
//	������΢�ź�
	private String FromUserName;
//	��Ϣ����ʱ�䣨���ͣ�
	private long CreateTime;
//	��Ϣ���ͣ�text/music/news��
	private String MsgType;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
