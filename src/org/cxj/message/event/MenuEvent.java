package org.cxj.message.event;

/*
 * �Զ���˵��¼�
 * @author cxj
 * @date 2016-04-02
 * */
public class MenuEvent extends BaseEvent{
	//�¼�keyֵ�����Զ���˵��ӿ���keyֵ��Ӧ
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
