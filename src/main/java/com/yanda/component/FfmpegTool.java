package com.yanda.component;

import java.io.File;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.MultimediaInfo;

@Component
public class FfmpegTool {
	
	private static final Logger LOG = Logger.getLogger(FfmpegTool.class);
	
	@Value("${ffmpeg.path}")
	private String ffmpegPath;
	
	private Encoder encoder;

	@PostConstruct
	public void setEncoder() {
		FFMPEGLocator locator = new FFMPEGLocator() {  
            @Override  
            protected String getFFMPEGExecutablePath() {  
            	return ffmpegPath;   
            }  
        };
		encoder = new Encoder(locator);
	}
	
	public Long getMediaDuration(File media) {
		Long duration = null;
		try {
			MultimediaInfo m = encoder.getInfo(media);
			duration = m.getDuration();
		} catch (EncoderException e) {
			LOG.error("获取媒体文件播放时长异常:" + e);
		}
		return duration;
	}
	
}
