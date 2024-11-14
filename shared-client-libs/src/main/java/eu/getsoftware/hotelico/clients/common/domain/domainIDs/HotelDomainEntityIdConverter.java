package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class HotelDomainEntityIdConverter implements AttributeConverter<HotelDomainEntityId, String> {
    @Override
    public String convertToDatabaseColumn(HotelDomainEntityId attribute) {
        return attribute != null ? attribute.uuidValue(): null;
    }

    @Override
    public HotelDomainEntityId convertToEntityAttribute(String dbData) {
        return dbData != null ? new HotelDomainEntityId(dbData) : null;
    }
}
