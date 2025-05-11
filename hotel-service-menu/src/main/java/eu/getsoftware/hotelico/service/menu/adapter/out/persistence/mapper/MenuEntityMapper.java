package eu.getsoftware.hotelico.service.menu.adapter.out.persistence.mapper;

import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.service.menu.adapter.out.persistence.model.MenuOrderMappedEntity;
import eu.getsoftware.hotelico.service.menu.application.domain.model.MenuOrderRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper//(uses = AddressValueObjectMapper.class)
public interface MenuEntityMapper extends EntityGenericMapper<MenuOrderRootDomainEntity, MenuOrderMappedEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    MenuOrderRootDomainEntity toDomain(MenuOrderMappedEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
    MenuOrderRootDomainEntity mapWithoutData(MenuOrderMappedEntity entity);

//    @Mapping(source = "addressJson", target = "address", qualifiedByName = "dbToAddress")
//    MenuOrderRootDomainEntity mapToDomainEntity(MenuOrderMappedEntity dbEntity);
    
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

 