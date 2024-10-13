//package eu.getsoftware.hotelico.clients.common.model.innerModelService
//
//import eu.getsoftware.hotelico.clients.common.domain.IDomainEntity
//
//
///**
// * Only setter methods. Only RequestDTO
// */
//public interface IDomainRegisterDTOGateway<T: IDomainEntity> {
//
//    /**
//     * we allow just "entity" parameter and not "requestGatewayDTO" and "responseGatewayDTO" for persistence (onion border)
//     */
//    fun saveEntity(entity: T)
//
//    fun existsByName(name: String): Boolean
//
//    fun findEntityById(id: Long): T 
//
//}
