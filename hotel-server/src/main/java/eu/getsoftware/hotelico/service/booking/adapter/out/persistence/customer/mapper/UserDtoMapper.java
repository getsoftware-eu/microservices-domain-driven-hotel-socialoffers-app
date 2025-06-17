package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper;

import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.UserEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.common.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper //extends DtoGenericMapper<UserEntity, UserClientDTO> 
{

//    @Override
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now().toString()")
    UserDTO toDto(UserEntity domain);

    UserDTO toFullDto(UserEntity customerDomainEntity);

//    @Mapping(target = "domainEntityId", expression = "java( customerDomainEntity.getDomainEntityId() )")
//    UserDTO toDtoWithHotelInfo(UserEntity customerDomainEntity, HotelDomainEntityId domainEntityId);

    @Mapping(target = "entityId", expression = "java( dto.getEntityId() )")
    UserDTO fillDtoWithHotelInfo(UserDTO dto, CheckinRootDomainEntity validCheckin);

    UserEntity toEntity(UserDTO dto);

//    @Mapping(source = "schadenAussenwirkungId", target = "schadenAussenwirkung")
//    @Mapping(source = "haufSchadenAussenwirkungId", target = "haufSchadenAussenwirkung")
//    @Mapping(source = "schadenFinanziellId", target = "schadenFinanziell")
//    @Mapping(source = "haufSchadenFinanziellId", target = "haufSchadenFinanziell")
//    @Mapping(source = "schadenGeschaeftssteuerungId", target = "schadenGeschaeftssteuerung")
//    @Mapping(source = "haufSchadenGeschaeftssteuerungId", target = "haufSchadenGeschaeftssteuerung")
//    @Mapping(source = "schadenVorschriftenVerletzungId", target = "schadenVorschriftenVerletzung")
//    @Mapping(source = "haufSchadenVorschriftenVerletzungId", target = "haufSchadenVorschriftenVerletzung")
//    fun updateFromDto(formDto: BiaFormDTO?, @MappingTarget bia: Bia?)
}

