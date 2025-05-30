package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDtoMapper //extends DtoGenericMapper<CustomerRootDomainEntity, UserClientDTO> 
{

//    @Override
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now().toString()")
    CustomerDTO toDto(CustomerRootDomainEntity domain);

    CustomerDTO toFullDto(CustomerRootDomainEntity customerDomainEntity);

    @Mapping(target = "domainEntityId", expression = "java( customerDomainEntity.getDomainEntityId() )")
    CustomerDTO toDtoWithHotelInfo(CustomerRootDomainEntity customerDomainEntity, HotelDomainEntityId domainEntityId);

    @Mapping(target = "domainEntityId", expression = "java( dto.getDomainEntityId() )")
    CustomerDTO fillDtoWithHotelInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin);

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

