package com.jzwb.resolver;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件上传解析器
 */
public class FileUploadCommonsMultipartResolver extends CommonsMultipartResolver {

	@Override
	protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		upload.setSizeMax(-1);
		return upload;
	}

	@Override
	public MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		FileUpload fileUpload = prepareFileUpload(getDefaultEncoding());
		FileUploadProgressListener fileUploadProgressListener = new FileUploadProgressListener();
		fileUpload.setProgressListener(fileUploadProgressListener);
		request.getSession().setAttribute(FileUploadProgressListener.FILE_UPLOAD_PROPRESS_LISTENER, fileUploadProgressListener);
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			return parseFileItems(fileItems, getDefaultEncoding());
		} catch (FileUploadBase.SizeLimitExceededException e) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), e);
		} catch (FileUploadException e) {
			throw new MultipartException("error", e);
		}
	}
}
