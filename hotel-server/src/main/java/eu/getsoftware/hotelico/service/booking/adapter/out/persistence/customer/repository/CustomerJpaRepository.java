// path: hotelico/service/booking/adapter/out/persistence/customer/repository/CustomerRepository.java

package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerDBEntity, Long>, JpaSpecificationExecutor<CustomerDBEntity> {

	// ====== JPQL Query Constants ======

	String FIND_ALL_ONLINE = "SELECT c " +
			"FROM  CustomerDBEntity c " +
			"WHERE c.active = TRUE " +
			"AND c.loggedValue = TRUE " +
			"AND c.lastSeenOnline > :checkDate";

	String FIND_CUSTOMER_CITIES = "SELECT DISTINCT d.city " +
			"FROM  CustomerDBEntity c " +
			"JOIN c.customerDetails d " +
			"WHERE c.active = TRUE " +
			"AND c.hotelStaff = FALSE " +
			"AND d.city IS NOT NULL";

	String FIND_ANONYM_CUSTOMER = "SELECT c " +
			"FROM  CustomerDBEntity c " +
			"WHERE c.active = TRUE " +
			"AND c.email = '[anonym]'";

	String CHECK_STAFF_OR_ADMIN_CUSTOMER = "SELECT CASE WHEN (c.hotelStaff = TRUE OR c.admin = TRUE) THEN true ELSE false END " +
			"FROM  CustomerDBEntity c " +
			"WHERE c.active = TRUE " +
			"AND c.id = :customerId";

	// ====== Methods ======

	Optional<CustomerDBEntity> findByDomainEntityIdValueAndActive(CustomerDomainEntityId domainEntityId, boolean active);

	List<CustomerDBEntity> findByEmailAndActive(String email, boolean active);

	List<CustomerDBEntity> findByLinkedInIdAndActive(String linkedInId, boolean active);

	List<CustomerDBEntity> findByFacebookIdAndActive(String facebookId, boolean active);

	List<CustomerDBEntity> findByActive(boolean active);
	List<CustomerDBEntity> findByName(String name);

	Page<CustomerDBEntity> findByActive(boolean active, Pageable pageable);

	List<CustomerDBEntity> findByLoggedValueAndActive(boolean logged, boolean active);

	@Query(FIND_ALL_ONLINE)
	List<CustomerDBEntity> findAllOnline(@Param("checkDate") Timestamp checkDate);

	List<CustomerDBEntity> findByIdIn(List<Long> ids);

	List<CustomerDBEntity> findByDomainEntityIdValueIn(List<CustomerDomainEntityId> ids);

	// В Kotlin эта сигнатура ошибочна: List<String>, но явно возвращался список Customer
	List<CustomerDBEntity> findByHotelStaffAndActive(boolean hotelStaff, boolean active);

	@Query(FIND_ANONYM_CUSTOMER)
	List<CustomerDBEntity> getAnonymeCustomer();

	@Query(CHECK_STAFF_OR_ADMIN_CUSTOMER)
	boolean checkStaffOrAdmin(@Param("customerId") CustomerDomainEntityId customerId);

	@Query(FIND_CUSTOMER_CITIES)
	List<String> findNotStaffUniqueCities();

	Optional<CustomerDBEntity> findByDomainEntityIdValue(CustomerDomainEntityId customerId);
}
