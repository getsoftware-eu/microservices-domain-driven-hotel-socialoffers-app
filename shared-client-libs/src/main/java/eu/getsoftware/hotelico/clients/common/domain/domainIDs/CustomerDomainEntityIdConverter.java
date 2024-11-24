//package eu.getsoftware.hotelico.clients.common.domain.domainIDs;
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class CustomerDomainEntityIdConverter implements AttributeConverter<CustomerDomainEntityId, String> {
//    
//    @Override
//    public String convertToDatabaseColumn(CustomerDomainEntityId attribute) {
//        return attribute != null ? attribute.uuidValue() : null;
//    }
//
//    @Override
//    public CustomerDomainEntityId convertToEntityAttribute(String dbData) {
//        return dbData != null ? new CustomerDomainEntityId(dbData) : null;
//    }
//}
