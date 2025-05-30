package hotelico.service.booking.adapter.out.persistence.hotel.outPortServiceImpl;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import static com.google.common.primitives.Longs.toByteArray;

@Service
public class FileStore
{
    public void save(String path, String filename, Optional<Map<String, String>> metadata, InputStream inputStream) {
    }

    public byte[] download(String pathInS3, String key) {
        return toByteArray(1);
    }

//    private final AmazonS3 s3;
//
//    @Autowired
//    public FileStore(AmazonS3 s3) {
//        this.s3 = s3;
//    }
//
//    public void save(String path,
//                     String fileName,
//                     Optional<Map<String, String>> optionalMetadata,
//                     InputStream inputStream) {
//        ObjectMetadata metadata = new ObjectMetadata();
//
//        optionalMetadata.ifPresent(map -> {
//            if (!map.isEmpty()) {
//                map.forEach(metadata::addUserMetadata);
//            }
//        });
//
//        try {
//            s3.putObject(path, fileName, inputStream, metadata);
//        } catch (AmazonServiceException e) {
//            throw new IllegalStateException("Failed to store file to s3", e);
//        }
//    }
//
//    public byte[] download(String path, String key) {
//        try {
//            S3Object object = s3.getObject(path, key);
//            return IOUtils.toByteArray(object.getObjectContent());
//        } catch (AmazonServiceException | IOException e) {
//            throw new IllegalStateException("Failed to download file to s3", e);
//        }
//    }
}
