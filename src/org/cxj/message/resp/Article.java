package org.cxj.message.resp;

/*
 * ͼ��model
 * @author cxj
 * @date 2016-04-06
 * */
public class Article {

//	ͼ����Ϣ����
	private String Title;
//	ͼ����Ϣ����
	private String Description;
//	ͼ������ ֧��jpg��png��ʽ���Ϻõ�Ч��Ϊ��ͼ640*320���أ�Сͼ80����*80����
	private String PicUrl;
//	���ͼ����Ϣ��ת����
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
