package eu.getsoftware.hotelico.customer.application.domain.infrastructure.portService;

import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.CustomerRootEntity;
import eu.getsoftware.hotelico.customer.application.domain.CustomerAggregate;
import eu.getsoftware.hotelico.customer.application.port.in.iservice.SocialService;
import eu.getsoftware.hotelico.hotel.common.utils.HotelEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SocialPortServiceImpl implements SocialService {
    
    @Transactional
    @Override
    public CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedInId){

        CustomerRootEntity customerEntity = modelMapper.map(customerDto, CustomerRootEntity.class);

        CustomerAggregate aggregate = customerEntity.getEntityAggregate();

        aggregate.setLinkedInId(linkedInId);
        aggregate.setLogged(true);
        aggregate.setProfileImageUrl(customerDto.getProfileImageUrl());

        //TODO eugen: get Languages from linkedIn

        long virtualHotelId = lastMessagesService.getInitHotelId();

        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customerEntity), virtualHotelId);

        dto = loginService.checkBeforeLoginProperties(customerDto, dto);

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }

        return dto;
//        return updateOwnDtoCheckinInfo(dto);
    }

    @Transactional
    @Override
    public CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId){
        CustomerRootEntity customerEntity = modelMapper.map(customerDto, CustomerRootEntity.class);
        customerEntity.getEntityAggregate().setFacebookId(facebookId);
        customerEntity.getEntityAggregate().setProfileImageUrl(customerDto.getProfileImageUrl());

        //TODO eugen: get Languages from linkedIn

//        CustomerDto dto = modelMapper.map(customerRepository.saveAndFlush(customer), CustomerDto.class);
//        dto = fillDtoFromCustomer(customer, dto);

        long virtualHotelId = lastMessagesService.getInitHotelId();

        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customerEntity), virtualHotelId);

        dto = loginService.checkBeforeLoginProperties(customerDto, dto);

        if(!dto.isHotelStaff() && !dto.isAdmin())
        {
            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
        }

//        return updateOwnDtoCheckinInfo(dto);
        return dto;
    }

    @Override
    public CustomerDTO getByLinkedInId(String linkedInId){
        List<CustomerRootEntity> customerEntities = customerRepository.findByLinkedInIdAndActive(linkedInId, true);

        if(customerEntities.isEmpty())
        {
            return null;
        }

        //TODO eugen: get Langguages from LinkedIn
//        int hotelId = getCustomerHotelId(customer.getId());
        CustomerDTO dto = convertMyCustomerToFullDto(customerEntities.get(0));

        return dto;
    }

    @Override
    public Optional<CustomerDTO> getByFacebookId(String facebookId){

        //TODO eugen: get Langguages from LinkedIn

        return customerRepository.findByFacebookIdAndActive(Objects.requireNonNull(facebookId), true)
                .stream()
                .findFirst().map(c -> Optional.of(convertMyCustomerToFullDto(c)))
                .orElse(Optional.empty());
    }
}
