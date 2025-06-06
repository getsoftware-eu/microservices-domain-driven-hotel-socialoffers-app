//package eu.getsoftware.hotelico.clients.common.domain.mapper
//
//import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier
//import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO
//import eu.getsoftware.hotelico.clients.common.domain.IDomainResponseDTO
//import eu.getsoftware.hotelico.clients.common.domain.IRootDomainEntity
//import org.mapstruct.Mapping
//import org.mapstruct.MappingTarget
//import org.mapstruct.Named
//
///**
// * central generic Interface for mapping Entity to Dto in lower layers 
// */
//interface IDomainMapper<T: IRootDomainEntity<out EntityIdentifier>, I : IDomainRequestDTO, O : IDomainResponseDTO>{
//
//    fun toEntityById(id: Long?): T
//
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTO(entity: T?): O?
//    
////    fun toDTO(entity: IUserEntity?): Z?
////    @Mapping(target = "login", source = "name")
////    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
////    fun toResponseDTOFromRequest(input: UserDsRequestApplicationModelDTO?): UserResponseApplicationModelDTO?
//
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toDsRequestDTO(entity: T?): I?
//
////    fun toResponseDTO(dto: I) : O
//    
//    fun toListDTO(assigmentFiles: List<T>): List<O>
//    
//    fun updateAllFromDto(assetFormDto: I?, @MappingTarget asset: T?)
//
//    @Named("mapWithoutData")
//    @Mapping(target = "saStatus", ignore = true)
//    fun updateFromDtoIgnoringSomeFields(assetDto: I?, @MappingTarget asset: T?)
//
//    @Mapping(target = "owner", ignore = true)
//    @Mapping(target = "vertreter", ignore = true)
//    @Mapping(target = "editor", ignore = true)
//    fun updateFromDtoWithoutUsers(assetFormDto: I?, @MappingTarget asset: T?)
//
////    @IterableMapping(qualifiedByName = ["mapWithoutData"])
////    fun updateListFromDtoIgnoringSomeFields(assigmentFileDTOs: List<Z?>): List<T?>
//
//
//}