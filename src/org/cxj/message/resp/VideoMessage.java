package org.cxj.message.resp;

/*
 * 视频消息
 * @author cxj
 * @date 2016-04-06
 * */
public class VideoMessage extends BaseMessage{

//	视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
