package org.cxj.message.req;

/*
 * ��Ƶ��Ϣ
 * @author cxj
 * @date 2016-04-01
 * */
public class VideoMessage extends BaseMessage{

//	��Ƶ��Ϣý��ID
	private String MediaId;
//	��Ƶ��Ϣ����ͼ��ý��ID
	private String ThumbMedia;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMedia() {
		return ThumbMedia;
	}
	public void setThumbMedia(String thumbMedia) {
		ThumbMedia = thumbMedia;
	}
}
