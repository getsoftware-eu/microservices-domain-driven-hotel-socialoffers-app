package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.mapper;

import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper//(uses = AddressValueObjectMapper.class)
public interface CustomerEntityMapper extends EntityGenericMapper<CustomerRootDomainEntity, CustomerDBEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    CustomerRootDomainEntity toDomain(CustomerDBEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    CustomerRootDomainEntity mapWithoutData(CustomerDBEntity entity);

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
}

 