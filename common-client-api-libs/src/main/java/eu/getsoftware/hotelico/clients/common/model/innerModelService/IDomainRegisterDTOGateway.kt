package eu.getsoftware.hotelico.clients.common.model.innerModelService

import eu.getsoftware.hotelico.clients.api.clients.domain.user.IUserDomain
import java.util.*


/**
 * Only setter methods. Only RequestDTO
 */
public interface IDomainRegisterDTOGateway<T: IUserDomain, I : IUserDomainRequestDTO, O : IUserDomainResponseDTO> {

    fun getModelDTOById(id: Long): O?

    fun createEntityFromDTO(userRequestDTO: I): T

    /**
     * we allow just "entity" parameter and not "requestGatewayDTO" and "responseGatewayDTO" for persistence (onion border)
     */
    fun saveEntity(entity: T)

    fun saveFromDTO(userRequestDTO: I)
    
    fun existsByName(name: String): Boolean
}
