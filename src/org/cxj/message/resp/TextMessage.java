package org.cxj.message.resp;

/*
 * 文本消息
 * @author cxj
 * @date 2016-04-06
 * */
public class TextMessage extends BaseMessage{

//	回复消息的内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
