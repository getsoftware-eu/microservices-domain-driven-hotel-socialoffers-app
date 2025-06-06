package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;//package de.hotelico.repository;
//

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public final static String FIND_BY_EMAIL_QUERY = "SELECT u " +
			"FROM UserEntity u " +
			"WHERE u.email = :email";

	public final static String FIND_BY_HOTEL_ID_QUERY = "SELECT u " +
			"FROM UserEntity u " +
			"WHERE u.hotelId = :hotelId";

	/**
	 * Find user by eMail.
	 */
	@Query(FIND_BY_EMAIL_QUERY)
	public UserEntity findByEMail(@Param("email") String eMail);
	
	public List<UserEntity> findByUserName(@Param("userName") String userName);
	
	public void deleteByDomainEntityIdValue(@Param("domainEntityId") CustomerDomainEntityId domainEntityId);

	/**
	 * Find user by hotelId.
	 */
	@Query(FIND_BY_HOTEL_ID_QUERY)
	public List<UserEntity> findByHotelId(@Param("hotelId") Long hotelId);

	UserEntity findByDomainEntityIdValue(CustomerDomainEntityId domainEntityId);
}
