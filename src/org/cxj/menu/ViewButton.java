package org.cxj.menu;

/*
 * view类型的按钮
 * @author cxj
 * @date 2016-04-12
 * */
public class ViewButton extends Button{

	private String type;
	private String url;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
