//package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;
//
//import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
//import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
//import eu.getsoftware.hotelico.clients.common.utils.FileUtils;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelDbActivity;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.repository.CustomerRepository;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelDbEntity;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelEvent;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.ActivityRepository;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.HotelRepository;
//import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
//import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
//import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.nio.file.*;
//import java.util.*;
//
///**
// * Created by Eugen on 22.08.2015.
// */
//@Service
//@RequiredArgsConstructor
//public class FileUploadServiceImpl implements FileUploadService
//{
//
//	private final CustomerRepository customerRepository;
//
//	private final ActivityRepository activityRepository;
//
//	private final HotelRepository hotelRepository;
//	
//	private final IHotelService hotelService;		
//	
//	private final INotificationService notificationService;	
//		
//	private final CustomerPortService customerService;	
//	
//	private final LastMessagesService lastMessagesService;
//	
//	private final ImageService imageService;
//	
////	@Autowired
////	private CheckinRepository checkinRepository;
////
////	@Autowired
////	private ModelMapper modelMapper;
//	//	@Autowired
////	FileUploadRepository fileUploadRepository;
////
////	// Retrieve file
////	public FileUpload findByFilename(String filename) {
////		return fileUploadRepository.findByFilename(filename);
////	}
////
////	// Upload the file
////	public void uploadFile(FileUpload doc) {
////		fileUploadRepository.saveAndFlush(doc);
////	}
//
//	protected static final Set<String> executingFileNames = Collections.synchronizedSet(new HashSet<String>());
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Path addResizeImage(IFileUploadable fileUploadable, String serverFilesPath, String filename, String fileType, InputStream input)
//	{
//		final Path dirPath = resolvePathToUploadDirectory(serverFilesPath, fileUploadable);
//
//		String fullName = filename + "." + fileType;
//		
//		File newEmptyImageFile = FileUtils.getFileToImageWrite(dirPath, fullName, input, false);
//
//		if("logo".equalsIgnoreCase(filename))
//		{
//			imageService.saveLogo(input, newEmptyImageFile, fileType);
//		}
//		else if("avatar".equalsIgnoreCase(filename))
//		{
//			imageService.saveAvatar(input, newEmptyImageFile, fileType);
//		}
//		else if("preview".equalsIgnoreCase(filename))
//		{
//			imageService.savePreview(input, newEmptyImageFile, fileType);
//		}
//
//		Path addedFilePath = newEmptyImageFile.toPath();
//		
//		if (addedFilePath != null)
//		{
//			fileUploadable.setMediaUploaded(true);
//			return addedFilePath;
//		}
//		return null;
//	}
//	
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Path addFile(IFileUploadable fileUploadable, String serverFilesPath, String filename, InputStream input)
//	{
//		final Path dirPath = resolvePathToUploadDirectory(serverFilesPath, fileUploadable);
//
//		Path addedFilePath = FileUtils.addFile(dirPath, filename, input, false);
//		if (addedFilePath != null)
//		{
//			fileUploadable.setMediaUploaded(true);
//			return addedFilePath;
//		}
//		return null;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public List<String> getFileNamesList(final IFileUploadable fileUploadable, final String serverFilesPath)
//	{
//
//		List<Path> filePaths = getFiles(fileUploadable, serverFilesPath);
//
//		final Path[] filePathArray = filePaths.toArray(new Path[0]);
//
//		Arrays.sort(filePathArray, new Comparator<Path>()
//		{
//			@Override
//			public int compare(final Path o1, final Path o2)
//			{
//				try
//				{
//					return Files.getLastModifiedTime(o2).compareTo(Files.getLastModifiedTime(o1));
//				}
//				catch (IOException e)
//				{
//					e.printStackTrace();
//				}
//
//				return 0;
//			}
//		});
//
//		final List<String> fileNameList = new ArrayList<>();
//
//		for (int i = 0; i < filePathArray.length; i++)
//		{
//			try
//			{
//				fileNameList.add(filePathArray[i].getFileName().toString());
//			}
//			catch (final Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		return fileNameList;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public List<Path> getFiles(final IFileUploadable fileUploadable, final String serverFilesPath)
//	{
//		Path dirPath = resolvePathToUploadDirectory(serverFilesPath, fileUploadable);
//
//		if (!Files.exists(dirPath))
//		{
//			return new ArrayList<>();
//		}
//
//		List<Path> filePaths = new ArrayList<>();
//
//		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath))
//		{
//			for (Path nextPath : directoryStream)
//			{
//				filePaths.add(nextPath);
//			}
//		}
//		catch (IOException ex)
//		{
//			// no-op
//		}
//
//		return filePaths;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean deleteFile(final IFileUploadable fileUploadable, final String serverFilesPath, final String fileName)
//	{
//		if (fileName == null)
//		{
//			return false;
//		}
//		try
//		{
//			if (getFileNamesList(fileUploadable, serverFilesPath).size() == 1)
//			{
//				fileUploadable.setMediaUploaded(false);
//			}
//
//			final Path filePath = resolvePathToUploadedFile(serverFilesPath, fileUploadable, fileName);
//
//			Files.deleteIfExists(filePath);
//		}
//		catch (final Exception e)
//		{
//			e.printStackTrace();
//			return false;
//		}
//
//		return true;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean deleteAllFiles(final IFileUploadable fileUploadable, final String serverFilesPath)
//	{
//		boolean successful = true;
//		for (final String nextFileName : getFileNamesList(fileUploadable, serverFilesPath))
//		{
//			if (!deleteFile(fileUploadable, serverFilesPath, nextFileName))
//			{
//				successful = false;
//			}
//		}
//
//		return successful;
//	}
//
//
//
//	//	/**
//	//	 * {@inheritDoc}
//	//	 */
//	//	@Override
//	//	public String uploadFile(UploadedFile uploadFile, String folderName)
//	//	{
//	//		String realPath = globals.getServletContext().getRealPath("/" + folderName);
//	//		Path dirPath = Paths.get(realPath);
//	//		
//	//		FileUtils.addFile(dirPath, uploadFile.getFileName(), uploadFile.getStream(), true);
//	//		
//	//		return uploadFile.getFileName();
//	//	}
//
//
//
//	/**
//	 * Create some kind of Link on the FileSystem.<br>
//	 * CAUTION!!! Some operations and link kinds are not allowed on several filesystems
//	 *
//	 * @param sourcePath
//	 *            Path to the existing file or directory
//	 * @param targetPath
//	 *            Path for the new link
//	 * @param softLink
//	 *            Switch mode of link creation (default is false [creation of symbolic links])
//	 * @throws IOException
//	 */
//	private void createLink(Path sourcePath, Path targetPath, boolean softLink)
//	{
//		// Hard Links on Directories are not allowed
//		if (Files.isDirectory(sourcePath) && !softLink)
//		{
//			//			throw new TapestryException("Hard links on directories are not supported", null, new IllegalArgumentException());
//		}
//
//		if (!Files.exists(sourcePath))
//		{
//			//			throw new TapestryException("Sourcepath " + sourcePath.toAbsolutePath().toString() + "  does not exist!", null, new FileNotFoundException());
//		}
//		try
//		{
//			if (softLink)
//			{
//				/* targetPath - Folder, wo der Link erstellt wird */
//				/* sourcePath - Folder, der verlink wird */
//				Files.createSymbolicLink(targetPath, sourcePath);
//			}
//			else
//			{
//				Files.createLink(targetPath, sourcePath);
//			}
//		}
//		catch (Exception x)
//		{
//			//			logger.error(x.getLocalizedMessage(), x);
//			//			throw new TapestryException(x.getLocalizedMessage(), x);
//		}
//
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean createLinkToFilesHolderDir(IFileUploadable sourceFilesHolder targetFilesHolder, String realPath) throws FileAlreadyExistsException
//	{
//		try
//		{
//			/* Parent, existiert bereits, real file/folder */
//			Path sourceUploadDir = resolvePathToUploadDirectory(realPath, sourceFilesHolder);
//			/* Child, kann existierren, darf auch nicht existieren, vertual link to real file or contains a virtual link to real folder */
//			Path targetUploadDir = resolvePathToUploadDirectory(realPath, targetFilesHolder);
//
//			// create a softlink of a whole folder
//			FileUtils.createFolderIfNotExists(sourceUploadDir);
//			createLink(sourceUploadDir, targetUploadDir, true);
//
//		}
//		catch (Exception e)
//		{
//			return false;
//		}
//
//		return true;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Path createLinkToFilesHolderFile(IFileUploadable sourceFilesHolder targetFilesHolder, String realPath, String filename, boolean isSoftLink)
//	{
//		File file;
//
//		try
//		{
//			if (filename == null)
//			{
//				return null;
//			}
//			
//			/* Parent, existiert bereits, real file/folder */
//			Path sourceUploadDir = resolvePathToUploadDirectory(realPath, sourceFilesHolder);
//			/* Child, kann existierren, darf auch nicht existieren, vertual link to real file or contains a virtual link to real folder */
//			Path targetUploadDir = resolvePathToUploadDirectory(realPath, targetFilesHolder);
//
//			FileUtils.createFolderIfNotExists(targetUploadDir);
//
//			Path source = sourceUploadDir.resolve(filename);
//
//			file = FileUtils.getUniqueFile(targetUploadDir, filename, false);
//
//			createLink(source, file.toPath(), isSoftLink);
//
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//
//		return file.toPath();
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Path resolvePathToUploadedFile(String realPath fileUploadable, String doc)
//	{
//		Path directoryPath = resolvePathToUploadDirectory(realPath, fileUploadable);
//
//		Path filePath = directoryPath.resolve(doc);
//
//		return filePath;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Path resolvePathToUploadedFile(String realPath, String filePathString, String doc)
//	{
//		Path directoryPath = Paths.get(realPath, filePathString);
//
//		Path filePath = directoryPath.resolve(doc);
//
//		return filePath;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Path resolvePathToUploadDirectory(String realPath fileUploadable)
//	{
//		int upperObjectId = Integer.MAX_VALUE;//applicationParameterDAO.getMaxUploadOrderId();
//		String filePath = fileUploadable.getPlainFilePath(upperObjectId);
//		return Paths.get(realPath, filePath);
//	}
//
//	
//	
//	
//	@Override
//	/**
//	 *
//	 * {@inheritDoc}
//	 * @author r.mertyn
//	 */
//	public FileSystem createZipFileSystem(String fileName) throws IOException
//	{
//		// convert the filename to a URI
//		final Path path = FileUtils.TMPDIRPATH.resolve(fileName);
//
//		// delete temp file when the virtual machine terminates
//		path.toFile().deleteOnExit();
//
//		// FIXME Datei wird nicht gel�scht, wenn der Server nicht neugestartet wurde
//		Files.deleteIfExists(path);
//
//		final URI uri = URI.create("jar:file:" + path.toUri().getPath());
//		final Map<String, String> env = new HashMap<>();
//		env.put("create", "true");
//		return FileSystems.newFileSystem(uri, env, null);
//	}
//
//
////	private void notificateAboutNewLogo(long customerId, int hotelId, String url, HotelEvent event, long entityId)
////	{
////		List<Integer> allOnlineCustomers = cacheService.getOnlineCustomerIds();
////
////		
////		
////		for(Integer nextLoggedCustomerId: allOnlineCustomers)
////		{
////			CustomerNotificationDto receiverNotification = customerService.getCustomerNotification(nextLoggedCustomerId, HotelEvent.EVENT_LOGIN);
////
////			receiverNotification.setCustomerEvent(customerId, hotelId, event, url, entityId);
////
////			simpMessagingTemplate.convertAndSend(SOCKET_NOTIFICATION_TOPIC+nextLoggedCustomerId, receiverNotification);
////
////		}
////	}
//	
//	@Override
//	public boolean saveFileToOwner(MultipartFile file, long senderId, String model, long modelId, String absolutePath) throws Throwable
//	{
//		
//			String mimeType = file.getContentType();
//			String filename = file.getOriginalFilename();
//	//		byte[] bytes = file.getBytes();
//	
//			String[] fileTypeSplit = filename.split("\\.");
//			String fileType = mimeType.replace("image/", "");
//	
////			String [] sendInfo = uploadedFile.split("#");
//	
//			//				FileUpload newFile = new FileUpload(uploadedFile, bytes, mimeType);
//	
//	
////			if(sendInfo.length>3)
//			{
////				Integer hotelId = Integer.parseInt(sendInfo[0]);
////				Integer senderDomainId = Integer.parseInt(sendInfo[1]);
////				String model = sendInfo[2];
////				Integer modelId = Integer.parseInt(sendInfo[3]);
//	
//				CustomerDBEntity sender = customerRepository.getOne(senderId);
//	
//				//Eugen: only creation of a new Hotel is allowed without login!!!
//				if (sender == null && !(model.equalsIgnoreCase("hotel") && modelId>9999))
//				{
//					//TODO eugen: return error descr.
//					return false;
//				}
//	
//				//					newFile.setCreatorId(senderDomainId);
//	
//				switch (model)
//				{
//					case "avatar":
//					case "me":
//					{
//						
//						Path fileAdded = null;
//						
////						if("jpeg".equalsIgnoreCase(fileType))
//						{
//							fileAdded = this.addResizeImage(sender, absolutePath, "avatar", fileType, file.getInputStream());
//						}
////						else{
////							fileAdded = this.addFile(sender, absolutePath, "avatar." + fileType, file.getInputStream());
////						}
//						
//						if (fileAdded != null)
//						{
//							sender.setPictureUrl(AppConfigProperties.getRelativePath(fileAdded));
//							
//							long newConsistencyId = System.currentTimeMillis();
//							
//							sender.setConsistencyId(newConsistencyId);
//							lastMessagesService.updateCustomerConsistencyId(sender.getDomainEntityId(), newConsistencyId);
//						}
//						customerRepository.saveAndFlush(sender);
//						notificationService.notificateAboutEntityEvent(customerService.convertCustomerWithHotelToDto(sender, new HotelDomainEntityId("-")), HotelEvent.EVENT_LOGO_CUSTOMER_CHANGE_MESSAGE, sender.getPictureUrl(), sender.getId());
//
//						break;
//					}
//					case "activity":
//					case "activities":
//					{
//						HotelDbActivity activity = (HotelDbActivity) hotelService.getActivityByIdOrInitId(modelId, modelId).orElseThrow(()->new RuntimeException("not found"));
//	
//						if (activity != null)
//						{
//							Path fileAdded = this.addResizeImage(activity, absolutePath, "logo", fileType, file.getInputStream());
//							Path previewAdded = this.addResizeImage(activity, absolutePath, "preview", fileType, file.getInputStream());
//							
//							if (fileAdded != null)
//							{
//								activity.setPictureUrl(AppConfigProperties.getRelativePath(fileAdded));
//								activity.setConsistencyId(System.currentTimeMillis());
//							}
//							
//							if (previewAdded != null)
//							{
//								activity.setPreviewPictureUrl(AppConfigProperties.getRelativePath(previewAdded));
//								activity.setConsistencyId(System.currentTimeMillis());
//							}
//							activityRepository.saveAndFlush(activity);
//							
//							//TODO Eugen: send activity image notification
//							HotelActivityDTO activityDto = hotelService.convertActivityToDto(activity, sender);
//							
//							notificationService.broadcastActivityNotification(activityDto);
//
//							notificationService.notificateAboutEntityEvent(customerService.convertCustomerWithHotelToDto(sender, activity.getHotelId()), HotelEvent.EVENT_LOGO_ACTIVITY_CHANGE_MESSAGE, activity.getPictureUrl(), activity.getInitId());
//
//						}
//	
//						break;
//					}
//					case "hotel":
//					{
//						long hotelId =  modelId;
//						HotelDbEntity hotelRootEntity = hotelRepository.getOne(hotelId);
//
//						if(hotelRootEntity ==null)
//						{
//							long hotelCreationTime = modelId;
//							Optional<HotelDbEntity> hotelOptional = hotelRepository.findByCreationTimeAndActive(hotelCreationTime, true).stream().findAny();
//						
//							if(hotelOptional.isPresent())
//							{
//								hotelRootEntity = hotelOptional.get();
//							}
//						}
//						
//						if (hotelRootEntity != null)
//						{
//							Path fileAdded = this.addResizeImage(hotelRootEntity, absolutePath, "logo", fileType, file.getInputStream());
//							Path previewAdded = this.addResizeImage(hotelRootEntity, absolutePath, "preview", fileType, file.getInputStream());
//							
//							if (fileAdded != null)
//							{
//								hotelRootEntity.setPictureUrl(AppConfigProperties.getRelativePath(fileAdded));
//								hotelRootEntity.setConsistencyId(System.currentTimeMillis());
//							}
//							
//							if (previewAdded != null)
//							{
//								hotelRootEntity.setPreviewPictureUrl(AppConfigProperties.getRelativePath(previewAdded));
//								hotelRootEntity.setConsistencyId(System.currentTimeMillis());
//							}
//							hotelRepository.saveAndFlush(hotelRootEntity);
//
//							//TODO Eugen: send hotel image notification
//							notificationService.notificateAboutEntityEvent(customerService.convertCustomerWithHotelToDto(sender, hotelRootEntity.getDomainEntityId()), HotelEvent.EVENT_LOGO_HOTEL_CHANGE_MESSAGE, hotelRootEntity.getPictureUrl(), hotelRootEntity.getId());
//						}
//
//						break;
//					}
//				}
//			}
//			
//
//			
//		 
//		return true;
//
//	}
//	
//	@Override
//	public CustomerDTO getEntityImage(long requesterId, String entityType, long entityId) throws Throwable {
//		CustomerDTO answerObj =   CustomerDTO.builder().build();
//		
//		String pictureUrl = "";
//		
//		switch (entityType.toLowerCase())
//		{
//			case "avatar":
//			case "me":
//			case "customer":
//			{
//				
//				CustomerDBEntity customerEntity = customerRepository.getOne(entityId);
//				
//				pictureUrl = customerEntity.getPictureUrl();
//				
//				break;
//			}
//			case "activity":
//			case "activities":
//			{
////				List<HotelActivity> activities = activityRepository.getByInitId(entityId);
//				 HotelDbActivity activity = (HotelDbActivity) hotelService.getActivityByIdOrInitId((int)entityId, entityId).orElseThrow(()->new RuntimeException("not found"));
//				
//				if (activity != null)
//				{
//					pictureUrl = activity.getPictureUrl();
//				}
//				
//				break;
//			}
//			case "hotel":
//			{
//				long hotelId = entityId;
//				HotelDbEntity hotelRootEntity = hotelRepository.getOne(hotelId);
//				
//				if(hotelRootEntity ==null)
//				{
//					long hotelCreationTime = entityId;
//					Optional<HotelDbEntity> hotelOptional = hotelRepository.findByCreationTimeAndActive(hotelCreationTime, true).stream().findAny();
//				
//					if(hotelOptional.isPresent())
//					{
//						hotelRootEntity = hotelOptional.get();
//					}
//				}
//				
//				if (hotelRootEntity != null)
//				{
//					pictureUrl = hotelRootEntity.getPictureUrl();				
//				}
//				break;
//			}
//		}
//		
//		answerObj.addSystemMessage("entityType", entityType);
//		answerObj.addSystemMessage("entityId", entityId+"");
//		answerObj.addSystemMessage("pictureUrl", pictureUrl);
//		
//		return answerObj;
//	}
//}
