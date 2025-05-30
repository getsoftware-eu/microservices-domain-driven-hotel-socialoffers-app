//package eu.getsoftware.hotelico.clients.common.domain.domainGateway;
//
//import java.util.Optional;
//
///**
// * Eu: interface for repository, for usage in abstract persistService!!!+++
// * 
// * In domain-driven design, each aggregate root has a repository. But as we said, the domain model should not be familiar with any infrastructure mechanisms. That’s why we don’t put repository implementations in the domain model but the infrastructure layer.
// * In the domain model, we put only the contracts (interfaces) of the repositories. 
// *
// * Maybe interface for Repository is redundant, because we should not use repository directly and should make all actions via extra (Gateway)EntityService (implemented in infrastructure-layer)!
// */
//public interface IDomainEntityGateway<T, ID> //extends PagingAndSortingRepository<T, ID> 
//{
//    Optional<T> findById(ID id);
//    Optional<T> findByName(String name);
//    <S extends T> S save(S entity);
//    T create(String name);
//
//    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
//    boolean existsById(ID id);
//
//    Iterable<T> findAll();
//    Iterable<T> findAllById(Iterable<ID> ids);
//}
