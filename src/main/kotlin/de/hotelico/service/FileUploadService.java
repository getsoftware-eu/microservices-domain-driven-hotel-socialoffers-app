package de.hotelico.service;

import de.hotelico.dto.CustomerDTO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Eugen on 22.08.2015.
 */
public interface FileUploadService
{
//	// Retrieve file
//	@Transactional
//	FileUpload findByFilename(String filename) ;
//
//	// Upload the file
//	@Transactional
//	void uploadFile(FileUpload doc) ;


	//	/**
	//	 * 
	//	 * @param fileUploadable
	//	 * @param serverFilesPath
	//	 * @param uploadFile
	//	 * @return path for added file
	//	 */
	//	public Path addFile(final IFileUploadable fileUploadable, final String serverFilesPath, final UploadedFile uploadFile);

	/**
	 * 
	 * @param fileUploadable
	 * @param serverFilesPath
	 * @param filename
	 * @param fileType
	 * @param input
	 * @return
	 */
	Path addResizeImage(IFileUploadable fileUploadable, String serverFilesPath, String filename, String fileType, InputStream input);


	/**
	 * Foegt dem Auftrag eine Datei im Dateisystem hinzu, gibt true zuroeck wenn erfolgreich, false wenn nicht.
	 *
	 * @param fileUploadable
	 * @param serverFilesPath
	 * @param filename
	 *            is a just simple filename with the name of the file and an extension without any path descriptions.
	 * @param input
	 *            is a source of the content
	 * @return path for added file
	 * @author a.hofmann, 25.02.2014 16:59:43
	 */
	Path addFile(final IFileUploadable fileUploadable, final String serverFilesPath, final String filename, InputStream input);

	/**
	 * Liefert eine Liste von allen Dateinamen die unter der Auftragsnummer gespeichert sind
	 *
	 * @param fileUploadable
	 * @param serverFilesPath
	 * @return
	 */
	public List<String> getFileNamesList(final IFileUploadable fileUploadable, final String serverFilesPath);

	/**
	 * Liefert eine Liste von allen Dateipfaden
	 *
	 * @param fileUploadable
	 * @param serverFilesPath
	 * @return
	 */
	public List<Path> getFiles(final IFileUploadable fileUploadable, final String serverFilesPath);

	/**
	 * Loescht eine gespeicherte Datei des Auftrags, gibt true zuroeck wenn Datei geloescht, false wenn nicht
	 *
	 * @param fileUploadable
	 * @param serverFilesPath
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile(final IFileUploadable fileUploadable, final String serverFilesPath, final String fileName);

	/**
	 * Loescht alle gespeicherten Datein des Auftrags, gibt true zuroeck wenn Dateien geloescht, false wenn nicht
	 *
	 * @param fileUploadable
	 * @param serverFilesPath
	 * @return
	 */
	public boolean deleteAllFiles(final IFileUploadable fileUploadable, final String serverFilesPath);


	//	/**
	//	 * Upload files and create dirs
	//	 * 
	//	 * @param uploadFile
	//	 * @param folderName
	//	 * @return file name without Umlaute
	//	 */
	//	public String uploadFile(UploadedFile uploadFile, String folderName);
	//	


	/**
	 * It creates a link for a folder from sourceFilesHolder to targetFilesHolder
	 *
	 * @param sourceFilesHolder
	 * @param targetFilesHolder
	 * @param realPath
	 * @throws FileAlreadyExistsException
	 */
	public boolean createLinkToFilesHolderDir(IFileUploadable sourceFilesHolder, IFileUploadable targetFilesHolder, String realPath) throws FileAlreadyExistsException;

	/**
	 * It creates a link for a file from sourceFilesHolder to targetFilesHolder
	 *
	 * @param sourceFilesHolder
	 * @param targetFilesHolder
	 * @param realPath
	 * @param filename
	 * @param isSoftLink
	 *            - should a softlink or a hardlink to be created
	 * @return modified Path to the linked file if already exists else unmodified file path
	 */
	public Path createLinkToFilesHolderFile(IFileUploadable sourceFilesHolder, IFileUploadable targetFilesHolder, String realPath, String filename, boolean isSoftLink);

	/**
	 * Resolves full real path to uploaded files and created directories
	 *
	 * @param realPath
	 *            Real path on Server
	 * @param fileUploadable
	 *            Object which should have previously updated file
	 * @param doc
	 *            Name of file
	 * @return Full real path on default filesystem
	 */
	public Path resolvePathToUploadedFile(String realPath, IFileUploadable fileUploadable, String doc);

	/**
	 * Resolves full real path to uploaded files and created directories by Plain (String) file path
	 *
	 * @param realPath
	 *            Real path on Server
	 * @param plainFilePath
	 *            Plain (String) File Path of Object file directory
	 * @param doc
	 *            Name of file
	 * @return Full real path on default file system
	 */
	public Path resolvePathToUploadedFile(String realPath, String plainFilePath, String doc);

	/**
	 * Resolves full real path to directory containing previously uploaded files
	 *
	 * @param realPath
	 *            Real path on Server
	 * @param fileUploadable
	 *            Object which should have previously updated file
	 * @return Full real path on default filesystem
	 */
	public Path resolvePathToUploadDirectory(String realPath, IFileUploadable fileUploadable);

	/**
	 * Creates zip File System in the temporary directoryvoid saveFileToOwner(MultipartFile , String );
	 */
	public FileSystem createZipFileSystem(String fileName) throws IOException;

	@Transactional
	boolean saveFileToOwner(MultipartFile file, long senderId, String model, long modelId, String absolutePath) throws Exception;
	
	@Transactional CustomerDTO getEntityImage(long requesterId, String entityType, long entityId);
}
