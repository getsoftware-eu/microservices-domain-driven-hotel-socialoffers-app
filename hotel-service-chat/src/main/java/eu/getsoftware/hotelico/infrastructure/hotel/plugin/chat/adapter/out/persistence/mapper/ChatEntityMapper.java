package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.mapper;

import eu.getsoftware.hotelico.clients.common.domain.mapper.EntityGenericMapper;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageMappedEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.domain.model.ChatMsgDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper//(uses = AddressValueObjectMapper.class)
public interface ChatEntityMapper extends EntityGenericMapper<ChatMsgDomainEntity, ChatMessageMappedEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    ChatMsgDomainEntity toDomain(ChatMessageMappedEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
    ChatMsgDomainEntity mapWithoutData(ChatMessageMappedEntity entity);

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

 