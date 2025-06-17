package eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Eugen on 10.11.2015.
 */
public interface ImageService
{
	boolean saveLogo(InputStream imageInputStream, File emptyFileToWrite, String imageType);
	
	boolean saveAvatar(InputStream imageInputStream, File emptyFileToWrite, String imageType);
	
	boolean savePreview(InputStream imageInputStream, File emptyFileToWrite, String imageType);
}
