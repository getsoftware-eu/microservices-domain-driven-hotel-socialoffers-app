package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository;

import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CustomerSpecifications {
    // Спецификация для поиска по имени
    public static Specification<CustomerDBEntity> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    // Спецификация для поиска по email
    public static Specification<CustomerDBEntity> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }

    // Спецификация для поиска по дате регистрации, позже указанной даты
    public static Specification<CustomerDBEntity> isRegisteredAfter(LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("registrationDate"), date);
    }
}
