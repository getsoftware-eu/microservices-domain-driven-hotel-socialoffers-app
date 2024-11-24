package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.mapper;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckinDtoMapper //extends DtoGenericMapper<CheckinRootDomainEntity, CheckinDTO> 
{

//    @Override
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now().toString()")
    CheckinDTO toDto(CheckinRootDomainEntity domain);

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

