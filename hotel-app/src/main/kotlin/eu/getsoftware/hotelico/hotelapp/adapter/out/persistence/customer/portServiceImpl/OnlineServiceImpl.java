package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.portServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.in.iPortService.OnlineService;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.out.iPortService.CustomerPortService;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.convertToDate;

@RequiredArgsConstructor
public class OnlineServiceImpl implements OnlineService {

    private CustomerPortService customerPortService;
    
    @Override
    public List<CustomerDTO> getAllOnline()
    {
        LocalDateTime ldt = LocalDateTime.now().minusMinutes(25);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Los_Angeles"));
        Long milis  = zdt.toInstant().toEpochMilli();
        return customerPortService.findAllOnline(new Timestamp(milis));
    }

    @Override
    public List<CustomerDTO> getAllIn24hOnline()
    {
        LocalDateTime ldt = LocalDateTime.now().minusDays(1);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Los_Angeles"));
        Long milis  = zdt.toInstant().toEpochMilli();
        return customerPortService.findAllOnline(new Timestamp(milis));
    }

    public static boolean isCustomerOnline(ICustomerRootEntity customerEntity)
    {
        return customerEntity !=null && (customerEntity.isHotelStaff() || customerEntity.getLastSeenOnline()!=null && customerEntity.getLastSeenOnline().after(convertToDate(LocalDateTime.now().minusMinutes(25))));
    }

    @Override
    public void setCustomerPing(long sessionCustomerId)
    {
//        Customer onlineCustomer = customerRepository.getOne(sessionCustomerId);

        //TODO EUGEN: java memory o
        if(sessionCustomerId>0)
        {
            boolean isReadyForNextNotification = lastMessagesService.isNotificationDelayReady(sessionCustomerId);

            lastMessagesService.checkCustomerOnline(sessionCustomerId);

            //TODO EUGEN: always response on ping? or only if something changes?
            if(isReadyForNextNotification)
            {
                notificationService.createAndSendNotification(sessionCustomerId, HotelEvent.EVENT_PING);
            }
            else{
                notificationService.createAndSendNotification(sessionCustomerId, HotelEvent.EVENT_ONLINE_CUSTOMERS);
            }
        }

    }
}
