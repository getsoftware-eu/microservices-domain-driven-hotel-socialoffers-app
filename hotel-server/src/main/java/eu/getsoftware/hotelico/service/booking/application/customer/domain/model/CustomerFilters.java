package eu.getsoftware.hotelico.service.booking.application.customer.domain.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CustomerFilters {
    private String name;
    private String email;
    private LocalDate registeredAfter;
}
