package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller.customer;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.utils.ControllerUtils;
import eu.getsoftware.hotelico.hotel.infrastructure.controller.BasicController;
import eu.getsoftware.hotelico.hotel.usecase.checkin.app.usecases.impl.CheckinService;
import eu.getsoftware.hotelico.hotel.usecase.notification.app.usecases.impl.NotificationService;
import eu.getsoftware.hotelico.hotelapp.application.customer.iservice.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService.LastMessagesService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService.LoginHotelicoService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.infrastructure.aspects.NotifyClients;
import eu.getsoftware.hotelico.hotelapp.application.hotel.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.infrastructure.dto.ResponseDTO;
import io.swagger.annotations.Api;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Api(value = "CustomerController") //add to swagger
@RestController
@RequestMapping("/customers")
//@SessionAttributes(ControllerUtils.SESSION_CUSTOMER)
public class CustomerController extends BasicController
{
    
    private final CustomerPortService customerService;
    private final CheckinService checkinService;
    private final NotificationService notificationService;
    private final LastMessagesService lastMessagesService;
    private final LoginHotelicoService loginService;
    
    public CustomerController(CustomerPortService customerService, CheckinService checkinService, NotificationService notificationService, LastMessagesService lastMessagesService, LoginHotelicoService loginService)
    {
        this.customerService = customerService;
        this.checkinService = checkinService;
        this.notificationService = notificationService;
        this.lastMessagesService = lastMessagesService;
        this.loginService = loginService;
    }
    
    //INIT Session values
    @ModelAttribute(ControllerUtils.SESSION_CUSTOMER)
    public CustomerDTO initSessionCustomer(HttpSession httpSession) {
        return httpSession.getAttribute(ControllerUtils.SESSION_CUSTOMER)!=null? (CustomerDTO) httpSession.getAttribute(ControllerUtils.SESSION_CUSTOMER) : new CustomerDTO(0); // populates form for the first time if its null
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewCustomers() {
        return "customers";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public @ResponseBody List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/customers/{customerId}/cities", method = RequestMethod.GET)
    public @ResponseBody
    Set<CustomerDTO> getCustomerCities(@PathVariable int customerId) {
        return customerService.getCustomerCities(customerId);
    }
    
    @RequestMapping(value = "/customers/{customerId}/hotel/{hotelId}/addStaff/{addStaff}", method = RequestMethod.GET)
    public @ResponseBody
    Set<CustomerDTO> getByHotelId(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable boolean addStaff) {
        return customerService.getByHotelId(customerId, hotelId, addStaff);
    }
    
    @RequestMapping(value = "/customers/{customerId}/city/{city}", method = RequestMethod.GET)
    public @ResponseBody
    Set<CustomerDTO> getByCity(@PathVariable long customerId, @PathVariable String city) {
        return customerService.getByCity(customerId, city);
    }
    
    
    // @NotifyClients
    @RequestMapping(value = "/customers/{id}/requesterId/{requesterId}", method = RequestMethod.PUT)
    public @ResponseBody CustomerDTO update(@PathVariable long id, @PathVariable int requesterId, @RequestBody @Valid CustomerDTO customerDTO, HttpSession httpSession) {
        customerDTO.setId(id);
        CustomerDTO out = customerService.updateCustomer(customerDTO, requesterId);
        
        //update in session
        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        
        return out;
    }
    
    @RequestMapping(value = "/customers/{id}/requesterId/{requesterId}", method = RequestMethod.GET)
    public @ResponseBody CustomerDTO getById(@PathVariable int id, @PathVariable int requesterId) {
        CustomerDTO out;
        
        try
        {
//            int sessionId = sessionCustomer!=null && sessionCustomer.getId()>0? sessionCustomer.getId() : 0;
            out = customerService.getById(id, requesterId);
        }
        catch (Exception e)
        {
            out = new CustomerDTO(id);
            out.setErrorResponse("Connection error:" + e.getMessage());
        }
        return out;
    }

    @RequestMapping(value = "/customerwithmessage/{getId}/sender/{senderId}", method = RequestMethod.GET)
    public @ResponseBody CustomerDTO getCustomerWithLastMessageById(@PathVariable int getId, @PathVariable int senderId) {
        CustomerDTO out;

        try
        {
            //int sessionId = sessionCustomer!=null && sessionCustomer.getId()>0? sessionCustomer.getId() : 0;
            out = customerService.getById(getId, senderId);
        }
        catch (Exception e)
        {
            out = new CustomerDTO(0);
            out.setErrorResponse("Connection error:" + e.getMessage());
        }
        return out;
    }
    
// 
    //@NotifyClients
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public @ResponseBody
    //eugen: @ModelAttribute(ControllerUtils.SESSION_CUSTOMER) makes new object empty
    CustomerDTO add(@RequestBody /*@ModelAttribute(ControllerUtils.SESSION_CUSTOMER)*/ CustomerDTO customerDto, HttpSession httpSession, HttpServletResponse response) {

        if(customerDto.isGuestAccount())
        {
            customerDto.setPassword(customerDto.getFirstName() + "_tempPassword_" + new Random().nextInt());
        }


        CustomerDTO out;
        
        try
        {
            out = customerService.addCustomer(customerDto, customerDto.getPassword());
        }catch (Exception e)
        {
            out = new CustomerDTO(0);
            out.setErrorResponse("Connection error: " + e.getMessage());
        }
        //set in session
        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        response.addCookie(new Cookie(ControllerUtils.SESSION_CUSTOMER_ID, out.getId() + ""));

        return out;
    }
    
    @NotifyClients
    @RequestMapping(value = "/customers/{id}/requesterId/{requesterId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id, @PathVariable int requesterId, HttpSession httpSession) {
        CustomerDTO task = new CustomerDTO(id);
        task.setId(id);
        customerService.deleteCustomer(task);

        httpSession.removeAttribute(ControllerUtils.SESSION_CUSTOMER);
    }

    @NotifyClients
    @RequestMapping(value = "/customers/{password}", method = RequestMethod.POST)
    public @ResponseBody CustomerDTO register(@PathVariable("password") String password, @RequestBody CustomerDTO customerDto, HttpSession httpSession,  HttpServletResponse response)
    {

        CustomerDTO out = customerService.addCustomer(customerDto, password);

        if(out!=null)
        {
            out = loginService.checkBeforeLoginProperties(customerDto, out);
        }
        
        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        response.addCookie(new Cookie(ControllerUtils.SESSION_CUSTOMER_ID, out.getId() + ""));

        return out;
    }
    

    @RequestMapping(value = "/login/email/{email:.+}/password/{password}", method = RequestMethod.GET)
    public @ResponseBody CustomerDTO checkLogin(@ModelAttribute(ControllerUtils.SESSION_CUSTOMER) CustomerDTO sessionCustomer, @PathVariable String email, @PathVariable String password, SessionStatus sessionStatus, HttpSession httpSession/*, HttpServletResponse response*/) {

        sessionStatus.setComplete();
        
        CustomerDTO out = loginService.checkLogin(email, password);

		if(out!=null)
		{
			lastMessagesService.setLastFullNotification(out.getId(), null);
		}
		
        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER+"2", out);
        sessionCustomer = out;
//        response.addCookie(new Cookie(ControllerUtils.SESSION_CUSTOMER_ID, out.getId() + ""));

        return out;
    } 
    

    @RequestMapping(value = "/login/email/{email}/password/{password}", method = RequestMethod.PUT)
    public @ResponseBody CustomerDTO checkPutLogin(@PathVariable String email, @PathVariable String password, @RequestBody /*@ModelAttribute(ControllerUtils.SESSION_CUSTOMER)*/ CustomerDTO loginCustomer, HttpSession httpSession) {

//        sessionStatus.setComplete();
        
        CustomerDTO out = loginService.checkLogin(email, password);

        if(out!=null)
        {
            out = loginService.checkBeforeLoginProperties(loginCustomer, out);
			lastMessagesService.setLastFullNotification(out.getId(), null);
		}
       
        
        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER+"2", out);
//        sessionCustomer = out;
//        response.addCookie(new Cookie(ControllerUtils.SESSION_CUSTOMER_ID, out.getId() + ""));

        return out;
    }
    
    //NOTE eugen: everywhere if POST: @RequestBody! Sonst wird ModelAttribute wert von Session liefern :(( 
    
    @RequestMapping(value = "/updateCheckin", method = RequestMethod.POST,  produces = "application/json")
    public @ResponseBody CustomerDTO updateCheckin(@RequestBody /*@ModelAttribute(ControllerUtils.SESSION_CUSTOMER)*/ CustomerDTO sessionCustomer, SessionStatus sessionStatus, HttpSession httpSession/*, HttpServletResponse response*/) {

        sessionStatus.setComplete();
        
        CustomerDTO out = checkinService.updateCheckin(sessionCustomer);

        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
    
        return out;
    }

    @RequestMapping(value = "/validSessionCustomer", method = RequestMethod.GET)
    public @ResponseBody CustomerDTO getValidSessionCustomer(/*@CookieValue(value = ControllerUtils.SESSION_CUSTOMER_ID, required = false) String sessionCustomerCookie ,*/ @ModelAttribute(ControllerUtils.SESSION_CUSTOMER) CustomerDTO sessionCustomer,  BindingResult result, SessionStatus sessionStatus, HttpSession httpSession) {
        sessionStatus.setComplete();

        if (result.hasErrors()) {
            result.getTarget();
        }
        
        if(sessionCustomer!=null && sessionCustomer.getId()>0 && sessionCustomer.getFirstName()==null)
        {
            CustomerDTO out = customerService.getById(sessionCustomer.getId(), sessionCustomer.getId());
			lastMessagesService.setLastFullNotification(out.getId(), null);
			httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        }
        else if(sessionCustomer!=null && sessionCustomer.getId()>0)
        {
            CustomerDTO out = customerService.synchronizeCustomerToDto(sessionCustomer);
			lastMessagesService.setLastFullNotification(out.getId(), null);
			httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);

            return out;
        }
        
        return null;
    }
    
    @RequestMapping(value = "/customerLastNotification/{customerId}/push/{pushRequest}", method = RequestMethod.GET)
    public @ResponseBody
    CustomerNotificationDTO getCustomerLastNotification(@PathVariable("customerId") long customerId, @PathVariable("pushRequest") boolean pushRequest) {
        
        return notificationService.getLastNotification(customerId, pushRequest);
    }
    
    @RequestMapping(value = "/validSessionCustomer", method = RequestMethod.POST)
    public @ResponseBody CustomerDTO setValidSessionCustomer(/*@CookieValue(value = ControllerUtils.SESSION_CUSTOMER_ID, required = false) String sessionCustomerCookie ,*/ @ModelAttribute(ControllerUtils.SESSION_CUSTOMER) CustomerDTO sessionCustomer,  BindingResult result, SessionStatus sessionStatus, HttpSession httpSession) {
        sessionStatus.setComplete();

        if (result.hasErrors()) {
            result.getTarget();
        }
        if(sessionCustomer!=null && sessionCustomer.getId()>0 && sessionCustomer.getFirstName()==null)
        {
            CustomerDTO out = customerService.getById(sessionCustomer.getId(), sessionCustomer.getId());
			lastMessagesService.setLastFullNotification(out.getId(), null);
			httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        }
        else 
        if(sessionCustomer!=null && sessionCustomer.getId()>0)
        {
            //TODO EUGEN? CHECK HIER SYNCHRONOSATION??? UPDATE consistencyId ?????
            sessionCustomer.setLogged(true);
            lastMessagesService.checkCustomerOnline(sessionCustomer.getId());
			lastMessagesService.setLastFullNotification(sessionCustomer.getId(), null);
			httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, sessionCustomer);

        }
        
        return null;
    }
    
//    @RequestMapping(value = "/customerNotification", method = RequestMethod.GET)
//    public @ResponseBody
//    CustomerNotificationDto getCustomerNotification(@ModelAttribute(ControllerUtils.SESSION_CUSTOMER) CustomerDto sessionCustomer,  BindingResult result, SessionStatus sessionStatus, HttpSession httpSession) {
//        sessionStatus.setComplete();
//
//        if (result.hasErrors()) {
//            result.getTarget();
//        }
//                
//        if(sessionCustomer!=null && sessionCustomer.getId()>0 && sessionCustomer.getHotelId()>0)
//        {
//            CustomerNotificationDto out = customerService.getCustomerNotification(sessionCustomer);
//
//            return out;
//        }
//        
//        return null;
//    } 
    
    @RequestMapping(value = "/{customerId}/customerPing", method = RequestMethod.GET)
    public @ResponseBody
    CustomerNotificationDTO getCustomerPing(@PathVariable("customerId") long customerId, @ModelAttribute(ControllerUtils.SESSION_CUSTOMER) CustomerDTO sessionCustomer,  BindingResult result, SessionStatus sessionStatus, HttpSession httpSession) {
        sessionStatus.setComplete();

        if (result.hasErrors()) {
            result.getTarget();
        }
                
        if(sessionCustomer!=null && sessionCustomer.getId()>0 && sessionCustomer.getId()==customerId)// && sessionCustomer.getHotelId()>0)
        {
            //TODO Eugen: socket ConnectException: Connection timed out: connect
            customerService.setCustomerPing(sessionCustomer.getId());//, httpSession.getId());
        }
        else{
//            CustomerDto dto = customerService.getById(customerId, 0);

            customerService.setCustomerPing(customerId);//, httpSession.getId());
        }
        
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody CustomerDTO logout(@ModelAttribute(ControllerUtils.SESSION_CUSTOMER) CustomerDTO customerDto, HttpSession httpSession, WebRequest request, SessionStatus status, HttpServletResponse response) {
        
        if(customerDto != null)
        {
            loginService.logoutCustomer(customerDto);
            httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, customerDto);
            lastMessagesService.setLastFullNotification(customerDto.getId(), null);
    
            status.setComplete();
            response.addCookie(new Cookie(ControllerUtils.SESSION_CUSTOMER_ID, null));
            httpSession.removeAttribute(ControllerUtils.SESSION_CUSTOMER);
            request.removeAttribute(ControllerUtils.SESSION_CUSTOMER, WebRequest.SCOPE_SESSION);
            //            store.cleanupAttribute(request, ControllerUtils.SESSION_CUSTOMER_ID);
            status.setComplete();
		}

        return null;
    }

    @RequestMapping(value = "/requestPasswordReset/email/{email:.+}", method = RequestMethod.GET, produces ="application/json;charset=UTF-8", headers="Accept=*")
    public @ResponseBody CustomerDTO requestPasswordReset(@PathVariable String email) {

//        sessionStatus.setComplete();

        ResponseDTO response = loginService.requestPasswordReset(email);

        //        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, out);
        //        httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER+"2", out);
        //        sessionCustomer = out;
        //        //        response.addCookie(new Cookie(ControllerUtils.SESSION_CUSTOMER_ID, out.getId() + ""));

        CustomerDTO test = new CustomerDTO(0);

        if(response.isError())
        {
            test.setErrorResponse(response.getMessage());
        }
        else {
            test.setErrorResponse("ok");
        }
        
        return test;
    }
    
    @RequestMapping(value = "/resetPassword/email/{email:.+}/resetcode/{resetcode}", method = RequestMethod.GET)
    public @ResponseBody CustomerDTO resetPassword(@PathVariable String email, @PathVariable String resetcode, HttpSession httpSession) {

        CustomerDTO dto = loginService.resetPassword(email, resetcode);

        if(dto!=null)
        {
            httpSession.setAttribute(ControllerUtils.SESSION_CUSTOMER, dto);
        }

        return dto;
    } 
    
}
