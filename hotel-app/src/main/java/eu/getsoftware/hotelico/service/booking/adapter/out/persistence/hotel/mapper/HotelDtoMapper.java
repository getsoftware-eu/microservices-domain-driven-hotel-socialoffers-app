package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.mapper;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.common.domain.mapper.DtoGenericMapper;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelDtoMapper extends DtoGenericMapper<HotelRootDomainEntity, HotelDTO> 
{

//    @Override
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now().toString()")
    HotelDTO toDto(HotelRootDomainEntity domain);

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

