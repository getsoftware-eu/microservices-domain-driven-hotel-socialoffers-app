package eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService;


/**
 * Allow to upload files/images for this objects.
 * 
 */
public interface IFileUploadable
{
	long getId();
	
	String getPlainFilePath(final int upperObjectId);
	
	void setMediaUploaded(final boolean mediaUploaded);
}
