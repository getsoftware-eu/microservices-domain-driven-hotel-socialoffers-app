/**
 *
 */
package eu.getsoftware.hotelico.common.utils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

/**
 * @author a.hofmann 06.12.2013 21:41:51
 * @version 1.0
 */
public class FileUtils
{
	private static final String TEMP_FOLDER = System.getProperty("java.io.tmpdir");
	
	/**
	 * This is a constant for currently configured temporary folder (system-wide). For faster usage.
	 */
	public static final Path TMPDIRPATH = Paths.get(TEMP_FOLDER);
	
	/**
	 * File separator (\) ist kein line separator (\n)
	 */
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	/**
	 * Recreates full filesystem path if object indexed by path object doesn't exists
	 *
	 * @param folderPath
	 *            Real path on fileSystem
	 */
	public static void createFolderIfNotExists(Path folderPath)
	{
		if (!Files.exists(folderPath))
		{
			try
			{
				Files.createDirectories(folderPath);
			}
			catch (IOException e)
			{
//				throw new TapestryException("Target directory doesn't exist and couldn't be created", e);
			}
		}
	}
	
	/**
	 * @param parentPath
	 * @param folderName
	 * @param deleteOnExit
	 * @return
	 * @author a.hofmann, 06.12.2013 21:49:13
	 */
	public static final File createFolder(Path parentPath, String folderName, boolean deleteOnExit)
	{
		Path folderPath = parentPath.resolve(folderName);
		
		final File folder = folderPath.toFile();
		
		FileUtils.createFolderIfNotExists(folderPath);
		
		if (deleteOnExit)
		{
			folder.deleteOnExit();
		}
		
		return folder;
	}
	
	/**
	 * @param name
	 *            is the name of the new temporary folder, that is depending on 'deleteOnExit' parameter could be deleted on JVM exit or not.
	 * @param deleteOnExit
	 *            <b>true</b> to delete the created folder on process exit, <b>false</b> otherwise.
	 * @return a corresponding folder
	 * @author a.hofmann, 06.12.2013 21:50:49
	 */
	public static final File createTempSubFolder(String name, boolean deleteOnExit)
	{
		return createFolder(TMPDIRPATH, name, deleteOnExit);
	}
	
//	/**
//	 * convert uploaded file (by tapestry component) to ByteArrayInputStream
//	 *
//	 * @param file
//	 *            uploaded file to convert
//	 * @return ByteArrayInputStream
//	 */
//	public static ByteArrayInputStream asByteArrayInputStream(final UploadedFile file)
//	{
//		final byte[] array = new byte[(int) file.getSize()];
//		
//		final InputStream in = file.getStream();
//		
//		try
//		{
//			in.read(array);
//		}
//		catch (final IOException e)
//		{
//			throw new IllegalStateException("Unable to read file: " + file.getFileName(), e);
//		}
//		
//		return new ByteArrayInputStream(array);
//	}

	/**
	 * Fügt für ein DirPath eine Datei im Dateisystem hinzu, gibt true zurück wenn erfolgreich, false wenn nicht
	 *
	 * @param dirPath
	 * @param filename
	 * @param input
	 * @param rewrite
	 * @return
	 */
	public static File getFileToImageWrite(final Path dirPath, String filename, InputStream input, boolean rewrite)
	{
		if (filename == null || input == null || filename.isEmpty())
		{
			return null;
		}

		FileUtils.createFolderIfNotExists(dirPath);

		filename = getPrettyFileName(filename);

		File file = getUniqueFile(dirPath, filename, rewrite);

//		try
//		{
//			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		}
//		catch (IOException e1)
//		{
//			throw new IllegalStateException("Unable to add file: " + filename, e1);
//		}

		return file;
	}
	
	
	/**
	 * Fügt für ein DirPath eine Datei im Dateisystem hinzu, gibt true zurück wenn erfolgreich, false wenn nicht
	 *
	 * @param dirPath
	 * @param filename
	 * @param input
	 * @param rewrite
	 * @return
	 */
	public static Path addFile(final Path dirPath, String filename, InputStream input, boolean rewrite)
	{
		if (filename == null || input == null || filename.isEmpty())
		{
			return null;
		}
		
		FileUtils.createFolderIfNotExists(dirPath);
		
		filename = getPrettyFileName(filename);
		
		File file = getUniqueFile(dirPath, filename, rewrite);
		
		try
		{
			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e1)
		{
			throw new IllegalStateException("Unable to add file: " + filename, e1);
		}
		
		return file.toPath();
	}
	
	public static File getUniqueFile(final Path dirPath, String filename, boolean rewrite)
	{
		final File parentDir = dirPath.toFile();
		File file;
		
		if (!rewrite)
		{
			int count = 0;
			
			try
			{
				file = new File(parentDir, filename);
				
				String newFileName;
				while (!file.createNewFile())
				{
					newFileName = "(" + (++count) + ")-" + filename;
					file = new File(parentDir, newFileName);
				}
			}
			catch (IOException e)
			{
				throw new IllegalStateException(e);
			}
		}
		else
		{
			file = new File(parentDir, filename);
		}
		
		return file;
	}
	
	/**
	 * It removes all special character from a filename
	 *
	 * @param filename
	 * @return
	 */
	public static String getPrettyFileName(String filename)
	{
		filename = filename.replaceAll("#", "");
		filename = filename.replaceAll(",", "_");
		filename = filename.replaceAll("~", "_");
		filename = filename.replaceAll(Pattern.quote("?"), "_");
		filename = filename.replaceAll(Pattern.quote("$"), "_");
		filename = filename.replaceAll("/", "_");
		filename = filename.replaceAll(":", "_");
		filename = filename.replaceAll(">", "_");
		filename = filename.replaceAll("<", "_");
		filename = filename.replaceAll("\\|", "_");
		filename = filename.replaceAll("\\\\", "_");
		filename = filename.replaceAll("\"", "_");
		filename = filename.replaceAll("'", "_");
		filename = replaceUmlauts(filename);
		return filename;
	}
	
	/**
	 * Replace umlauts with letters from latin Alphabet, like: <br/>
	 * ä -> ae <br/>
	 * ü -> ue <br/>
	 * ö -> oe <br/>
	 * Ä -> Ae <br/>
	 * Ü -> Ue <br/>
	 * Ö -> Oe <br/>
	 * ß -> ss <br/>
	 *
	 * @param s
	 *            string to replace umlauts in
	 * @return string without umlauts
	 */
	private static String replaceUmlauts(String s)
	{
		s = s.replaceAll("ä", "ae");
		s = s.replaceAll("ü", "ue");
		s = s.replaceAll("ö", "oe");
		s = s.replaceAll("Ä", "Ae");
		s = s.replaceAll("Ü", "Ue");
		s = s.replaceAll("Ö", "Oe");
		s = s.replaceAll("ß", "ss");
		s = s.trim();
		s = s.replaceAll(" ", "");
		
		return s;
	}
}
