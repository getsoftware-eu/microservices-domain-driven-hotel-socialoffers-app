package eu.getsoftware.hotelico.clients.common.domain.domainIDs;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CheckinDomainEntityIdConverter implements AttributeConverter<CheckinDomainEntityId, String> {
    @Override
    public String convertToDatabaseColumn(CheckinDomainEntityId attribute) {
        return attribute != null ? attribute.uuidValue() : null;
    }

    @Override
    public CheckinDomainEntityId convertToEntityAttribute(String dbData) {
        return dbData != null ? new CheckinDomainEntityId(dbData) : null;
    }
}
