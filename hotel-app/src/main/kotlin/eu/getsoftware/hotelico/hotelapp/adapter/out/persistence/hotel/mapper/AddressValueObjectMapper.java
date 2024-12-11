package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.mapper;

import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.AddressDBEmbeddable;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.AddressValueObject;
import org.mapstruct.Mapper;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper//(componentModel = "spring")
public interface AddressValueObjectMapper extends EntityGenericMapper<AddressValueObject, AddressDBEmbeddable> {

    @Override
//    @Mapping(target = "password", ignore = true)
    AddressValueObject toDomain(AddressDBEmbeddable entity);
    
    // Специальный метод с @Named для частичного маппинга
//    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
//    AddressValueObject mapWithoutData(AddressDBEmbeddable entity);
}

 