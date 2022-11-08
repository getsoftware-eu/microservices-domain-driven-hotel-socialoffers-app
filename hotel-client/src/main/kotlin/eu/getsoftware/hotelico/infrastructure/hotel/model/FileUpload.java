package eu.getsoftware.hotelico.infrastructure.hotel.model;//package eu.getsoftware.hotelico.model;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//
///**
// * Created by Eugen on 22.08.2015.
// */
//@Entity
//public class FileUpload {
//
//	public FileUpload(String filename, byte[] file, String mimeType) {
//
//		this.file = file;
//		this.filename = filename;
//		this.mimeType = mimeType;
//	}
//
//	public FileUpload() {
//		// Default Constructor
//	}
//
//	@Id
//	private String filename;
//
//	@Lob
//	private byte[] file;
//
//	private String mimeType;
//	
//	private int creatorId;
//	
////	private Customer creator;
////	
////	private HotelActivity activity;
////	
////	private Hotel hotel;
//	
//	public String getFilename() {
//		return filename;
//	}
//
//	public void setFilename(String filename) {
//		this.filename = filename;
//	}
//
//	public byte[] getFile() {
//		return file;
//	}
//
//	public void setFile(byte[] file) {
//		this.file = file;
//	}
//
//	public String getMimeType() {
//		return mimeType;
//	}
//
//	public void setMimeType(String mimeType) {
//		this.mimeType = mimeType;
//	}
//
//	public int getCreatorId()
//	{
//		return creatorId;
//	}
//
//	public void setCreatorId(int creatorId)
//	{
//		this.creatorId = creatorId;
//	}
//}