package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainGateway

import eu.getsoftware.hotelico.clients.api.clients.domain.user.IUserDomain
import eu.getsoftware.hotelico.clients.common.model.innerModelService.IDomainRegisterDTOGateway
import java.util.*

/**
 * domain - because only entity service!
 * eu: For example, ON new foreign DOMAIN-EVENT => FIND(!!) AND UPDATE OWN LOCAL DOMAIN-Entity 
 * 
 * eu: This abstract helper Service use internal Entity, that is not defined in interface!!!
 * 
 * gateway - interface to get and persist entity to repository
 * 
 * its internal business conditions, that have to be internal treaten
 */
abstract class DomainEntityInnerGatewayServiceAbstr<T: IUserDomain, I : IUserDomainRequestDTO, O : IUserDomainResponseDTO>(
    private val domainMapper: IDomainMapper<T, I, O>,
    private val domainRepository: IDomainEntityGateway<T, Long>,
) : IDomainRegisterDTOGateway<T, I, O> {

    abstract val assetClass: Class<T>

    /**
     * eu: find a DomainEntity in Domain layer and call interface methods ++++
     */
    override fun getModelDTOById(id: Long): O? {
        val user = findEntityById(id)
        return domainMapper.toResponseDTO(user)
    }
    
    fun createEntity(name: String): T {
        val entity: T  
        try {
            entity = createInstance(assetClass);
            entity.setInitValues(name)
        } catch (e: Exception) {
            throw RuntimeException(e);
        }
        
        return entity
    }
    
    override fun createEntityFromDTO(userRequestDTO: I): T {
        val entity : T = createEntity(userRequestDTO.name())
        domainMapper.updateAllFromDto(userRequestDTO, entity)
        return entity
    }

    override fun saveEntity(entity: T) {
        domainRepository.save(entity)
    }    
    
    override fun saveFromDTO(userRequestDTO : I) {
        val entity : T = createEntityFromDTO(userRequestDTO)
        domainRepository.save(entity)
    }  
    
//    fun save(entity: T): T {
//        val entity : T = createInstance(assetClass)
//        entityMapper.updateAllFromDto(userDTO, entity)
////        asset.saStatus = StatusEnum.NEU
//        //asset.owner = userRepository.getReferenceById(ownerId)
//        entityRepository.save(entity)
//        return entity
//    }

    fun findEntityById(id: Long): T {
        val optionalUser: Optional<T> = domainRepository.findById(id)

        if (optionalUser.isPresent) {
            val user = optionalUser.get()
//            val dto: Z? = entityMapper.toDsRequestDTO(user)
            //            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
            return user
        } else throw UserNotFoundException(id)
    }

    override abstract fun existsByName(name: String): Boolean
    
    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }
}