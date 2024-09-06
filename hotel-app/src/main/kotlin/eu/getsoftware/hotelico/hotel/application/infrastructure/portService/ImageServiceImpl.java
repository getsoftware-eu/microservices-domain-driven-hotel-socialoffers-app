package eu.getsoftware.hotelico.hotel.application.infrastructure.portService;

import eu.getsoftware.hotelico.hotel.application.port.in.iService.ImageService;
import eu.getsoftware.hotelico.hotel.common.utils.ImageUtils;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Eugen on 10.11.2015.
 */
@Service
public class ImageServiceImpl implements ImageService
{
	public final int logoMaxHeight = 300;
	public final int logoMaxWidth = 800;	
	
	public final int avatarMaxHeight = 66;
	public final int avatarMaxWidth = 66;	
	
	public final int previewMaxHeight = 200;
	public final int previewMaxWidth = 200;
	
	String AVATAR = "avatar";
	String PREVIEW = "preview";
	String LOGO = "logo";
	
	public int getImageType(String imageType){
		
		int res = ImageUtils.IMAGE_UNKNOWN;
		
		switch (imageType.toLowerCase()){
			case "jpeg": {
				res = ImageUtils.IMAGE_JPEG;
				break;
			}
			case "png": {
				res = ImageUtils.IMAGE_PNG;
				break;
			}
			case "gif": {
				res = ImageUtils.IMAGE_GIF;
				break;
			}
			default: {
				res = ImageUtils.IMAGE_UNKNOWN;
				break;
			}
		}
		
		return res;
	}
	
	public boolean saveLogo(InputStream imageInputStream, File emptyFileToWrite, String imageType){

		int imageTypeValue = getImageType(imageType);
		
		BufferedImage resizedImage = ImageUtils.resizeAndCropImage(imageInputStream, imageTypeValue, logoMaxWidth, logoMaxHeight, LOGO);

		ImageUtils.saveCompressedImage(resizedImage, emptyFileToWrite, imageTypeValue);
		
		return true;
	}

	public boolean saveAvatar(InputStream imageInputStream, File emptyFileToWrite, String imageType){

		int imageTypeValue = getImageType(imageType);

		BufferedImage resizedImage = ImageUtils.resizeAndCropImage(imageInputStream, imageTypeValue, avatarMaxWidth, avatarMaxHeight, AVATAR);
		
		ImageUtils.saveCompressedImage(resizedImage, emptyFileToWrite, imageTypeValue);

		return true;
	}
	
	public boolean savePreview(InputStream imageInputStream, File emptyFileToWrite, String imageType){

		int imageTypeValue = getImageType(imageType);

		BufferedImage resizedImage = ImageUtils.resizeAndCropImage(imageInputStream, imageTypeValue, previewMaxWidth, previewMaxHeight, PREVIEW);
		
		ImageUtils.saveCompressedImage(resizedImage, emptyFileToWrite, imageTypeValue);

		return true;
	}
}
