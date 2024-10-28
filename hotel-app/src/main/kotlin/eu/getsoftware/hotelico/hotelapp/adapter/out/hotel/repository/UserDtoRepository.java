package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository;//package de.hotelico.repository;
//

import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDtoRepository extends JpaRepository<User, Integer> {

	public final static String FIND_BY_EMAIL_QUERY = "SELECT u " +
			"FROM User u " +
			"WHERE u.email = :email";

	public final static String FIND_BY_HOTEL_ID_QUERY = "SELECT u " +
			"FROM User u " +
			"WHERE u.hotelId = :hotelId";

	/**
	 * Find user by eMail.
	 */
	@Query(FIND_BY_EMAIL_QUERY)
	public User findByEMail(@Param("email") String eMail);
	
	public List<User> findByUserName(@Param("userName") String userName);

	/**
	 * Find user by hotelId.
	 */
	@Query(FIND_BY_HOTEL_ID_QUERY)
	public List<User> findByHotelId(@Param("hotelId") Long hotelId);
}
