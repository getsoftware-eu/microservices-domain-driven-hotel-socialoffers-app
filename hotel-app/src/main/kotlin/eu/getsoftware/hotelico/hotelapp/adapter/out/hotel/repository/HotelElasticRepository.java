//package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository;
//
//import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelRootEntity;
//import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.HotelRepositoryExtension;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//import java.util.Optional;
//
///**
// * Проекции: Все изменения агрегатов отправляются в Elasticsearch, чтобы можно было эффективно искать продукты, фильтровать их по атрибутам, находить похожие продукты и делать аналитику.
// * 
// * CQRS: Commands (например, добавление продукта) выполняются в основной базе, а запросы (например, найти продукты с определенными характеристиками) — через Elasticsearch.
// * 
// * OpenSearch (free version of ElasticSearch) получает обновления по Events (z.b. data_persisted), via Kafka from Microservices
// */
//@RepositoryRestResource(exported = false)
//public interface HotelElasticRepository extends ElasticsearchRepository<HotelRootEntity, String>, HotelRepositoryExtension {
//
//    Optional<HotelRootEntity> findVisibleById(String id);
//}