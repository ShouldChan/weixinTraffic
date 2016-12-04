package org.cxj.message.resp;

/*
 * 音乐model
 * @author cxj
 * @date 2016-04-06
 * */
public class Music {

//	音乐标题
	private String Title;
//	音乐描述
	private String Description;
//	音乐链接
	private String MusicUrl;
//	高质量音乐链接，wifi环境下优先使用该链接播放音乐
	private String HQMusicUrl;
//	缩略图的媒体ID，通过上传多媒体文件得到的id
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
