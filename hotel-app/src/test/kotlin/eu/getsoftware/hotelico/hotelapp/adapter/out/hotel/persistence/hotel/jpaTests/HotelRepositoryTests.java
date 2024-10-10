package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.jpaTests;

import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.checkin.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:13.2-alpine://payment"
})
class HotelRepositoryTests
{
    @Autowired
    private CheckinRepository checkinRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void existingPaymentCanBeFound() {
        ICustomerRootEntity customer = new CustomerRootEntity();
        CustomerHotelCheckin checkin = new CustomerHotelCheckin();

        // when:
        Long customerId = entityManager.persist(customer).getId();
        entityManager.persist(checkin);

        Optional<CustomerRootEntity> savedCustomer = customerRepository.findById(customerId);

        // then:
        assertThat(savedCustomer).isPresent();
        assertThat(savedCustomer.get().getFirstName().isBlank()).isFalse();
    }

    @Test
    void paymentsAreUniquePerOrder() {
        CustomerRootEntity order = new CustomerRootEntity();
        CustomerHotelCheckin first = new CustomerHotelCheckin();
        CustomerHotelCheckin second = new CustomerHotelCheckin();

        // when:
        entityManager.persist(order);
        entityManager.persist(first);

        // then:
        assertThrows(PersistenceException.class, () -> entityManager.persistAndFlush(second));
    }

    @Test
    @Sql("/multiple-payments.sql")
    void findPaymentsAfterDate() {
//        List<Payment> payments = paymentRepository.findAllAfter(LocalDateTime.now().minusDays(1));

//        assertThat(payments).extracting("order.id").containsOnly(1L);
    }

    @Test
    @Sql("/multiple-payments.sql")
    void findPaymentsByCreditCard() {
//        List<Payment> payments = paymentRepository.findByCreditCardNumber("4532756279624064");

//        assertThat(payments).extracting("order.id").containsOnly(1L);
    }
}
