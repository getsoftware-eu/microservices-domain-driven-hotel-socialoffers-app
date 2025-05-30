package eu.getsoftware.hotelico.service.booking.adapter.in.web.controller.customer;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import hotelico.service.booking.adapter.in.web.controller.customer.CustomerController;
import hotelico.service.booking.application.customer.port.out.iPortService.CustomerPortService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerPortService customerService;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateCustomer_updatesCustomerSuccessfully() {
        long id = 1L;
        int requesterId = 1;
        CustomerDTO customerDTO = CustomerDTO.builder().id(id).build();
        CustomerDTO updatedCustomer = CustomerDTO.builder().build();
        when(customerService.updateCustomer(customerDTO, requesterId)).thenReturn(updatedCustomer);

        CustomerDTO result = customerController.update(id, requesterId, customerDTO, httpSession);

        assertEquals(updatedCustomer, result);
        verify(httpSession).setAttribute(AppConfigProperties.SESSION_CUSTOMER, updatedCustomer);
    }

    @Test
    void updateCustomer_handlesInvalidCustomer() {
        long id = 1L;
        int requesterId = 1;
        CustomerDTO customerDTO = CustomerDTO.builder().id(id).build();
        when(customerService.updateCustomer(customerDTO, requesterId)).thenThrow(new RuntimeException("Invalid customer"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            customerController.update(id, requesterId, customerDTO, httpSession);
        });

        assertEquals("Invalid customer", exception.getMessage());
        verify(httpSession, never()).setAttribute(AppConfigProperties.SESSION_CUSTOMER, customerDTO);
    }

    @Test
    void updateCustomer_handlesNullCustomer() {
        long id = 1L;
        int requesterId = 1;
        CustomerDTO customerDTO = CustomerDTO.builder().id(id).build();
        when(customerService.updateCustomer(customerDTO, requesterId)).thenReturn(null);

        CustomerDTO result = customerController.update(id, requesterId, customerDTO, httpSession);

        assertNull(result);
        verify(httpSession, never()).setAttribute(AppConfigProperties.SESSION_CUSTOMER, null);
    }
}