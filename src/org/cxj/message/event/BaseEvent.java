package org.cxj.message.event;

/*
 * �¼�����
 * @author cxj
 * @date 2016-04-02
 * */
public class BaseEvent {

	//������΢�ź�
	private String ToUserName;
	//���ͷ��˺ţ�һ��Open ID��
	private String FromUserName;
	//��Ϣ����ʱ�䣨���ͣ�
	private long CreateTime;
	//��Ϣ����
	private String MsgType;
	//�¼�����
	private String Event;
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
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
}
