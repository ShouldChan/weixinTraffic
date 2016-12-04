package org.cxj.message.resp;

/*
 * 图文model
 * @author cxj
 * @date 2016-04-06
 * */
public class Article {

//	图文消息名称
	private String Title;
//	图文消息描述
	private String Description;
//	图文链接 支持jpg、png格式，较好的效果为大图640*320像素，小图80像素*80像素
	private String PicUrl;
//	点击图文信息跳转链接
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
