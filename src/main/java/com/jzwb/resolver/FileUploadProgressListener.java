package com.jzwb.resolver;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 文件上传进度监听器
 */
@Component
public class FileUploadProgressListener implements ProgressListener, Serializable {

	public final static String FILE_UPLOAD_PROPRESS_LISTENER = "fileUploadProgressListener";

	private int percentDone = 0;
	private boolean contentLengthKnown = false;

	public void update(long bytesRead, long contentLength, int items) {
		if (contentLength > -1)
			contentLengthKnown = true;
		if (contentLengthKnown)
			percentDone = BigDecimal.valueOf(bytesRead).multiply(BigDecimal.valueOf(100L)).divide(BigDecimal.valueOf(contentLength), 0, BigDecimal.ROUND_FLOOR).intValue();
	}

	public int getPercentDone() {
		return percentDone;
	}
}
