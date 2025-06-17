//package eu.getsoftware.hotelico.service.booking.adapter.out.hotel.repository;
//
//import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
//import eu.getsoftware.hotelico.service.booking.adapter.out.checkin.repository.CheckinRepository;
//import eu.getsoftware.hotelico.service.booking.adapter.out.customer.model.CustomerDBEntity;
//import eu.getsoftware.hotelico.service.booking.adapter.out.customer.repository.CustomerRepository;
//import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
//import eu.getsoftware.hotelico.service.booking.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
//import eu.getsoftware.hotelico.service.booking.main.MainApplication;
//import jakarta.persistence.PersistenceException;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@Disabled
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(properties = {
//        "spring.datasource.url=jdbc:tc:postgresql:13.2-alpine://payment"
//})
//class HotelRepositoryTests
//{
//    @Autowired
//    private CheckinRepository checkinRepository;
//    
//    @Autowired
//    private CustomerRepository customerRepository;
//    
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    void existingPaymentCanBeFound() {
//        CustomerRootDomainEntity customer = new CustomerDBEntity();
//        CheckinRootDomainEntity checkin = new CheckinRootDomainEntity();
//
//        // when:
//        CustomerDomainEntityId customerId = entityManager.persist(customer).getDomainEntityId();
//        entityManager.persist(checkin);
//
//        Optional<CustomerDBEntity> savedCustomer = customerRepository.findByDomainEntityIdValue(customerId);
//
//        // then:
//        assertThat(savedCustomer).isPresent();
//        assertThat(savedCustomer.get().getFirstName().isBlank()).isFalse();
//    }
//
//    @Test
//    void paymentsAreUniquePerOrder() {
//        CustomerDBEntity order = new CustomerDBEntity();
//        CheckinRootDomainEntity first = new CheckinRootDomainEntity();
//        CheckinRootDomainEntity second = new CheckinRootDomainEntity();
//
//        // when:
//        entityManager.persist(order);
//        entityManager.persist(first);
//
//        // then:
//        assertThrows(PersistenceException.class, () -> entityManager.persistAndFlush(second));
//    }
//
//    @Test
//    @Sql("/multiple-payments.sql")
//    void findPaymentsAfterDate() {
////        List<Payment> payments = paymentRepository.findAllAfter(LocalDateTime.now().minusDays(1));
//
////        assertThat(payments).extracting("order.id").containsOnly(1L);
//    }
//
//    @Test
//    @Sql("/multiple-payments.sql")
//    void findPaymentsByCreditCard() {
////        List<Payment> payments = paymentRepository.findByCreditCardNumber("4532756279624064");
//
////        assertThat(payments).extracting("order.id").containsOnly(1L);
//    }
//}
