package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.ImmutableSet;
import eu.getsoftware.hotelico.clients.common.domain.IRootDomainEntity;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.DBAddress;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelWallPost;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor - NEVER for Entity
public class HotelDomainEntity implements IRootDomainEntity /*MultitenantDocument, Scoreable*/ {
    
    public static final String TYPE_NAME = "hotel";

//    @Id
//    private String id; // UUID can not be used as document ID yet. https://jira.spring.io/browse/DATAES-163

    @Getter
    protected HotelDomainEntityId domainEntityId;

    @Getter
    protected DBAddress address;
    
//    @With
    private LocalDateTime indexedAt;

    @Setter
    private Float score;

//    @JsonView(HotelPartialUpdateView.class)
    private Integer tenantId;

//    @JsonView(HotelPartialUpdateView.class)
    private boolean visible;

//    @JsonView(HotelPartialUpdateView.class)
//    private String sku;

//    @JsonView(HotelPartialUpdateView.class)
    @Setter
    protected String name;

//    @JsonView(HotelPartialUpdateView.class)
    private String description;

//    @JsonView(HotelPartialUpdateView.class)
//    private String manufacturer;

//    @JsonView(HotelPartialUpdateView.class)
//    private String essentialFeatures;

//    @JsonView(HotelPartialUpdateView.class)
//    private String defaultImageRelUri;

//    @JsonView(HotelPartialUpdateView.class)
    private LocalDateTime createdAt;

    //eugen: mappedBy entity!
    Set<? extends IHotelWallPost> hotelWallPosts;

    //eugen: mappedBy entity!
    Set<HotelDomainEntity> hotelActivities;

    public String getPlainFilePath(final int upperOrderId){
        return null;
    };

    public void setMediaUploaded(boolean mediaUploaded){
        
    };

    public boolean isVirtual;

    String getCurrentHotelAccessCode;

//    Collection<CustomerDomainEntityId> getStaffIdList;

    double latitude;

    double longitude;

    String guestPushIds;


//    @JsonView(HotelPartialUpdateView.class)
//    private boolean onSale;


//    @Singular
//    @JsonView(HotelPartialUpdateView.class)
//    private ImmutableSet<String> tags;

//    @Singular
//    @JsonView(AttributePartialUpdateView.class)
//    private ImmutableSet<Attribute> attributes;

//    @Singular
//    @JsonView(ImagePartialUpdateView.class)
//    private ImmutableSet<Image> images;

    private static <T> ImmutableSet.Builder<T> remove(ImmutableSet<T> originalSet, T elementToRemove) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();
        originalSet.forEach(element -> {
            if (!element.equals(elementToRemove)) {
                builder.add(element);
            }
        });
        return builder;
    }

    public String getCurrentHotelAccessCode() { return null;
    }

//    public Collection<CustomerDomainEntityId> getStaffIdList() { return null;
//    }

    @Override
    public void setInitValues(Map<String, String> fieldToValues) {
        
    }

//    /**
//     * @param tag tag to be added to this product
//     * @return <tt>true</tt> if this product did not already contain the specified
//     * tag
//     */
//    public boolean addOrReplaceTag(@NotNull String tag) {
//        boolean contained = tags.contains(checkNotNull(tag));
//        tags = remove(tags, tag).add(tag).build();
//        return !contained;
//    }
//
//    /**
//     * @param tag tag to be removed from this product, if present
//     * @return <tt>true</tt> if this product contained the specified tag
//     */
//    public boolean removeTag(@NotNull String tag) {
//        boolean contained = tags.contains(checkNotNull(tag));
//        tags = remove(tags, tag).build();
//        return contained;
//    }
//
//    /**
//     * @param image image to be added to this product
//     * @return <tt>true</tt> if this product did not already contain the specified
//     * image
//     */
//    public boolean addOrReplaceImage(@NotNull Image image) {
//        boolean contained = images.contains(checkNotNull(image));
//        images = remove(images, image).add(image).build();
//        return !contained;
//    }
//
//    /**
//     * @param image image to be removed from this product, if present
//     * @return <tt>true</tt> if this product contained the specified image
//     */
//    public boolean removeImage(@NotNull Image image) {
//        boolean contained = images.contains(checkNotNull(image));
//        images = remove(images, image).build();
//        return contained;
//    }
//
//    /**
//     * @param attribute attribute to be added to this product
//     * @return <tt>true</tt> if this product did not already contain the specified
//     * attribute
//     */
//    public boolean addOrReplaceAttribute(@NotNull Attribute attribute) {
//        boolean contained = attributes.contains(checkNotNull(attribute));
//        attributes = remove(attributes, attribute).add(attribute).build();
//        return !contained;
//    }
//
//    /**
//     * @param attribute attribute to be removed from this product, if present
//     * @return <tt>true</tt> if this product contained the specified attribute
//     */
//    public boolean removeAttribute(@NotNull Attribute attribute) {
//        boolean contained = attributes.contains(checkNotNull(attribute));
//        attributes = remove(attributes, attribute).build();
//        return contained;
//    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class HotelBuilder {
    }
}
 




