package eu.getsoftware.hotelico.infrastructure.hotel.repository;//package de.hotelico.repository;
//
//import eu.getsoftware.hotelico.model.Customer;
//import eu.getsoftware.hotelico.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface UserDtoRepository extends JpaRepository<User, Integer> {
//
//	public final static String FIND_BY_EMAIL_QUERY = "SELECT u " +
//			"FROM User u " +
//			"WHERE u.email = :email";
//
//	public final static String FIND_BY_HOTEL_ID_QUERY = "SELECT u " +
//			"FROM User u " +
//			"WHERE u.hotelId = :hotelId";
//
//	/**
//	 * Find user by eMail.
//	 */
//	@Query(FIND_BY_EMAIL_QUERY)
//	public User findByEMail(@Param("email") String eMail);
//
//	/**
//	 * Find user by hotelId.
//	 */
//	@Query(FIND_BY_HOTEL_ID_QUERY)
//	public List<User> findByHotelId(@Param("hotelId") Long hotelId);
//}
