package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelActivityDBEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelActivityRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", uses = AddressValueObjectMapper.class)
public interface HotelActivityEntityMapper extends EntityGenericMapper<HotelActivityRootDomainEntity, HotelActivityDBEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    HotelActivityRootDomainEntity toDomain(HotelActivityDBEntity entity);

    @Override
//    @Mapping(target = "sourceDomainEntityId", source = "domainEntityId")
    HotelActivityDBEntity toDb(HotelActivityRootDomainEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
    HotelActivityRootDomainEntity mapWithoutData(HotelActivityDBEntity entity);

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

 