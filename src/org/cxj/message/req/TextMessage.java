package org.cxj.message.req;

/*
 * 文本消息    需要继承基类BaseMessage
 * @author cxj
 * @date 2016-04-01
 * */
public class TextMessage extends BaseMessage{
	// 消息内容  
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	} 

}
