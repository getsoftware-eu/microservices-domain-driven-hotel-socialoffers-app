package eu.getsoftware.hotelico.customer.application.domain.infrastructure.portService;

import eu.getsoftware.hotelico.customer.application.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.customer.application.port.in.iservice.OnlineService;
import eu.getsoftware.hotelico.customer.common.iEntity.ICustomerEntity;
import eu.getsoftware.hotelico.hotel.common.utils.HotelEvent;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static eu.getsoftware.hotelico.common.utils.ControllerUtils.convertToDate;

public class OnlineServiceImpl implements OnlineService {

    @Override
    public List<ICustomerEntity> getAllOnline()
    {
        LocalDateTime ldt = LocalDateTime.now().minusMinutes(25);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Los_Angeles"));
        Long milis  = zdt.toInstant().toEpochMilli();
        return customerRepository.findAllOnline(new Timestamp(milis));
    }

    @Override
    public List<ICustomerEntity> getAllIn24hOnline()
    {
        LocalDateTime ldt = LocalDateTime.now().minusDays(1);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Los_Angeles"));
        Long milis  = zdt.toInstant().toEpochMilli();
        return customerRepository.findAllOnline(new Timestamp(milis));
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
