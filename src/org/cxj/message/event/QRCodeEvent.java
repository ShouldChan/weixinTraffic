package org.cxj.message.event;

/*
 * ɨ��������Ķ�ά���¼�
 * @author cxj
 * @date 2016-04-02
 * */
public class QRCodeEvent extends BaseEvent{

//	�¼�KEYֵ
	private String EventKey;
//	���ڻ�ȡ��ά��ͼƬ
	private String Ticket;
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
