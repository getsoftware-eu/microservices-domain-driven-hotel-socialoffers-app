package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository;

// path: hotelico/service/booking/adapter/out/persistence/hotel/repository/ActivityQueries.java

public final class ActivityQueries {

    public static final String FIND_ALL_BY_HOTEL_QUERY = "SELECT a " +
            "FROM HotelActivity a " +
            "WHERE a.hotel.id = :hotelId " +
            "AND a.active = TRUE";

    public static final String COUNT_ALL_BY_HOTEL_QUERY = "SELECT count(a) " +
            "FROM HotelActivity a " +
            "WHERE a.hotel.id = :hotelId " +
            "AND a.active = TRUE";

    public static final String FIND_TIME_VALID_BY_HOTEL_QUERY = "SELECT a " +
            "FROM HotelActivity a " +
            "WHERE a.hotel.id = :hotelId " +
            "AND a.validTo > :checkDate " +
            "AND a.validFrom <= :checkDate " +
            "AND a.active = TRUE " +
            "AND a.hidden = FALSE";

    public static final String FIND_TIME_VALID_BY_HOTEL_COUNTER_QUERY = "SELECT count(a) " +
            "FROM HotelActivity a " +
            "WHERE a.hotel.id = :hotelId " +
            "AND a.validTo > :checkDate " +
            "AND a.validFrom <= :checkDate " +
            "AND a.active = TRUE " +
            "AND a.hidden = FALSE";

    public static final String FIND_BY_CREATOR_AND_HOTEL_QUERY = "SELECT a " +
            "FROM HotelActivity a " +
            "WHERE a.hotel.id = :hotelId " +
            "AND a.sender.id = :creatorId " +
            "AND a.active = TRUE";

    public static final String FIND_ALL_ACTIVE_QUERY = "SELECT a " +
            "FROM HotelActivity a " +
            "WHERE a.active = TRUE " +
            "AND a.hotel.active = TRUE";

    public static final String FIND_ALL_TIME_VALID_ACTIVE_QUERY = "SELECT a " +
            "FROM HotelActivity a " +
            "WHERE a.validTo > :checkDate " +
            "AND a.validFrom <= :checkDate " +
            "AND a.active = TRUE " +
            "AND a.hidden = FALSE " +
            "AND a.hotel.active = TRUE";

    private ActivityQueries() {
        // Utility class
    }
}
