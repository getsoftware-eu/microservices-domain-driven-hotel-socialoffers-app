package de.hotelico.service;


/**
 * Allow to upload files/images for this objects.
 * 
 * @author l.rein
 */
public interface IFileUploadable
{
	long getId();
	
	String getPlainFilePath(final int upperObjectId);
	
//	Set<CommentHistory> getCommentHistory();
	
	void setMediaUploaded(final boolean mediaUploaded);
	
}
