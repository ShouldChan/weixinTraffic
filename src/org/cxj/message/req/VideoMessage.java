package org.cxj.message.req;

/*
 * 视频消息
 * @author cxj
 * @date 2016-04-01
 * */
public class VideoMessage extends BaseMessage{

//	视频消息媒体ID
	private String MediaId;
//	视频消息缩略图的媒体ID
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
