package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.HotelWallPost;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelWallRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(uses = AddressValueObjectMapper.class)
public interface HotelWallEntityMapper extends EntityGenericMapper<HotelWallRootDomainEntity, HotelWallPost> {

    @Override
//    @Mapping(target = "password", ignore = true)
    HotelWallRootDomainEntity toDomain(HotelWallPost entity);

    @Override
//    @Mapping(target = "sourceDomainEntityId", source = "domainEntityId")
    HotelWallPost toDb(HotelWallRootDomainEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
    HotelWallRootDomainEntity mapWithoutData(HotelWallPost entity);

//    @Mapping(source = "addressJson", target = "address", qualifiedByName = "dbToAddress")
//    UserRootDomainEntity mapToDomainEntity(UserMappedDBEntity dbEntity);
    
//    @Named("addressToDb")
//    static AddressDBEmbeddable addressToDB(AddressValueObject address) {
//        return address != null ? address.toDb() : null;
//    }
//
//    @Named("dbToAddress")
//    static AddressValueObject dbToAddress(AddressDBEmbeddable json) {
//        return json != null ? AddressValueObject.fromDb(json) : null;
//    }

    default <I extends EntityIdentifier> HotelDomainEntityId map( I value) {
        return HotelDomainEntityId.from(value.toString());
    }
}

 