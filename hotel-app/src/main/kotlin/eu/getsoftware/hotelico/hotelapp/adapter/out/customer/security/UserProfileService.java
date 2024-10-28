package eu.getsoftware.hotelico.hotelapp.adapter.out.customer.security;

import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.IRepository.ICustomerRepository;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.service.FileStore;
import eu.getsoftware.hotelico.hotelapp.main.config.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService
{

    private ICustomerRepository customerRepository;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(CustomerRepository customerRepository,
                              FileStore fileStore) {
        this.customerRepository = customerRepository;
        this.fileStore = fileStore;
    }

    List<CustomerRootEntity> getUserProfiles() {
        return customerRepository.findAll();
    }
    
    /**
     * need to save in S3: 
     * path in S3
     * filename
     * List<String> metadata
     * file as InputStream
     * @param userProfileId
     * @param file
     */
    void uploadUserProfileImage(long userProfileId, MultipartFile file) {
        // 1. Check if image is not empty
        isFileEmpty(file);
        // 2. If file is an image
        isImage(file);

        // 3. The user exists in our database
        CustomerRootEntity user = customerRepository.findById(userProfileId).orElseThrow(() -> new NoSuchElementException("not found"));

        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.getEntityAggregate().setProfileImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
    
    /**
     * S3fileStore.download(pathInS3, key)
     * @param userProfileId
     * @return
     */
    byte[] downloadUserProfileImage(long userProfileId) {
        CustomerRootEntity user = customerRepository.getById(userProfileId);

        String pathInS3 = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getEntityAggregate().getProfileImageUrl());

        return user.getEntityAggregate().getProfileImageLink()
                .map(key -> fileStore.download(pathInS3, key))
                .orElse(new byte[0]);

    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

}
