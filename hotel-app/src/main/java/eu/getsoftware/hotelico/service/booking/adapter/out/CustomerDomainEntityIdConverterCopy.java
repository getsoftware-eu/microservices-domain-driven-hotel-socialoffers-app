//package eu.getsoftware.hotelico.hotelapp.adapter.out;
//
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class CustomerDomainEntityIdConverterCopy implements AttributeConverter<String, CustomerDomainEntityId> {
//    
////    @Override
////    public String convertToDatabaseColumn(CustomerDomainEntityId attribute) {
////        return attribute != null ? attribute.toString() : null;
////    }
////
////    @Override
////    public CustomerDomainEntityId convertToEntityAttribute(String dbData) {
////        return dbData != null ? new CustomerDomainEntityId(dbData) : null;
////    }
//
//    @Override
//    public CustomerDomainEntityId convertToDatabaseColumn(String dbData) {
//        return dbData != null ? new CustomerDomainEntityId(dbData) : null;
//    }
//
//    @Override
//    public String convertToEntityAttribute(CustomerDomainEntityId attribute) {
//        return attribute != null ? attribute.toString() : null;
//    }
//}
