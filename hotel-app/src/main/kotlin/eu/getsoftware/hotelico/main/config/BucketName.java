package eu.getsoftware.hotelico.main.config;

public enum BucketName
{

    PROFILE_IMAGE("hotelico-image-upload-123");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
