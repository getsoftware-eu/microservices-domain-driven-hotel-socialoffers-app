package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper;

import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(builder = @Builder(disableBuilder = true))//(uses = AddressValueObjectMapper.class)
public interface CustomerEntityMapper extends EntityGenericMapper<CustomerRootDomainEntity, CustomerDBEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    CustomerRootDomainEntity toDomain(CustomerDBEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
    CustomerRootDomainEntity mapWithoutData(CustomerDBEntity entity);

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

 