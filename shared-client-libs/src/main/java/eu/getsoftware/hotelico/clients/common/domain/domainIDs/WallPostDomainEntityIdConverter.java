//package eu.getsoftware.hotelico.clients.common.domain.domainIDs;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class WallPostDomainEntityIdConverter implements AttributeConverter<WallPostDomainEntityId, String> {
//    
//    @Override
//    public String convertToDatabaseColumn(WallPostDomainEntityId attribute) {
//        return attribute != null ? attribute.uuidValue() : null;
//    }
//
//    @Override
//    public WallPostDomainEntityId convertToEntityAttribute(String dbData) {
//        return dbData != null ? new WallPostDomainEntityId(dbData) : null;
//    }
//}
