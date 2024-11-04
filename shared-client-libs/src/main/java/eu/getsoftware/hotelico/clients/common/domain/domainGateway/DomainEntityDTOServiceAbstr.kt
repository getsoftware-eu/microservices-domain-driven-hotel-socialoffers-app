package eu.getsoftware.hotelico.clients.common.domain.domainGateway

import eu.getsoftware.hotelico.clients.common.domain.IDomainEntity
import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO
import eu.getsoftware.hotelico.clients.common.domain.IDomainResponseDTO
import eu.getsoftware.hotelico.clients.common.domain.mapper.IDomainMapper

/**
 * domain DTO - because we want separate only entity service from DTO service!
 */
abstract class DomainEntityDTOServiceAbstr<T: IDomainEntity, I : IDomainRequestDTO, O : IDomainResponseDTO>(
    private val domainMapper: IDomainMapper<T, I, O>,
    private val domainRegisterDTOGateway: DomainEntityGatewayServiceAbstr<T>,
) 
{

    //override 
    fun createEntityFromDTO(userRequestDTO: I): T {
        val entity : T = domainRegisterDTOGateway.createEntity(userRequestDTO.name())
        domainMapper.updateAllFromDto(userRequestDTO, entity)
        return entity
    }
    
    //override 
    fun saveFromDTO(userRequestDTO : I) {
        val entity : T = createEntityFromDTO(userRequestDTO)
        domainRegisterDTOGateway.saveEntity(entity)
    }
    
    //override 
    fun getModelDTOById(id: Long): O? {
        val user = domainRegisterDTOGateway.findEntityById(id)
        return domainMapper.toResponseDTO(user)
    }

    fun convertToResponseDTO(entity: T): O? {
        return domainMapper.toResponseDTO(entity)
    }

   
}