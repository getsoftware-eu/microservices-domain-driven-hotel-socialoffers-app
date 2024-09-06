package eu.getsoftware.hotelico.customer.application.port.in.iservice;

import eu.getsoftware.hotelico.customer.common.iEntity.ICustomerEntity;
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
