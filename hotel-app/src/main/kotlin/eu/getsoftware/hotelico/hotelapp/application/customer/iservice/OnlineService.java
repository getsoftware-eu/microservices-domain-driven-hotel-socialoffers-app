package eu.getsoftware.hotelico.hotelapp.application.customer.iservice;

import eu.getsoftware.hotelico.hotelapp.application.customer.common.iEntity.ICustomerEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * All methods of this class throws NullPoinrerException if a required argument is null
 */
public interface OnlineService
{
    List<ICustomerEntity> getAllOnline();
	
    List<ICustomerEntity> getAllIn24hOnline();

    @Transactional
    void setCustomerPing(long sessionCustomerId);
}
