package org.cxj.message.resp;

/*
 * ����model
 * @author cxj
 * @date 2016-04-06
 * */
public class Music {

//	���ֱ���
	private String Title;
//	��������
	private String Description;
//	��������
	private String MusicUrl;
//	�������������ӣ�wifi����������ʹ�ø����Ӳ�������
	private String HQMusicUrl;
//	����ͼ��ý��ID��ͨ���ϴ���ý���ļ��õ���id
	private String ThumbMeidaId;
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
	public String getMusicUrl() {
		return MusicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	public String getThumbMeidaId() {
		return ThumbMeidaId;
	}
	public void setThumbMeidaId(String thumbMeidaId) {
		ThumbMeidaId = thumbMeidaId;
	}
}
