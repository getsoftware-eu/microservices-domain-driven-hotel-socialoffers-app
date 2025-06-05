package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDealDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerDealRootDomainEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDealDtoMapper //extends DtoGenericMapper<CustomerDealRootDomainEntity, UserClientDTO> 
{

//    @Override
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now().toString()")
    CustomerDealDTO toDto(CustomerDealRootDomainEntity domain);

    CustomerDealDTO toFullDto(CustomerDealRootDomainEntity customerDomainEntity);

    @Mapping(target = "domainEntityId", expression = "java( customerDomainEntity.getDomainEntityId() )")
    CustomerDealDTO toDtoWithHotelInfo(CustomerDealRootDomainEntity customerDomainEntity, HotelDomainEntityId domainEntityId);

    @Mapping(target = "domainEntityId", expression = "java( dto.getDomainEntityId() )")
    CustomerDealDTO fillDtoWithHotelInfo(CustomerDealDTO dto, CheckinRootDomainEntity validCheckin);

    CustomerDBEntity toEntity(CustomerDealDTO dto);

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

