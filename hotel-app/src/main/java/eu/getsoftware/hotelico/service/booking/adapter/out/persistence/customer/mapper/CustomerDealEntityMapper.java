package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper;

import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.viewEntity.model.CustomerDealDBEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerDealRootDomainEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))//(uses = AddressValueObjectMapper.class)
public interface CustomerDealEntityMapper extends EntityGenericMapper<CustomerDealRootDomainEntity, CustomerDealDBEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    CustomerDealRootDomainEntity toDomain(CustomerDealDBEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
    CustomerDealRootDomainEntity mapWithoutData(CustomerDealDBEntity entity);

//    @AfterMapping
//    default CustomerDetails toCustomerDetails(@MappingTarget ICustomerDetails customer) {
//        if (customer instanceof CustomerDetails) {
//            return (CustomerDetails) customer;
//        } else {
//            // вручную скопировать поля
//             var temp = new CustomerDetails();
//
//                temp.setBirthday(customer.getBirthday());
//             
//             return temp;
//        }    
//    }
    
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

 