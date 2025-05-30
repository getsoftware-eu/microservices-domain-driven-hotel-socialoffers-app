package hotelico.service.booking.adapter.out.persistence.customer.portServiceImpl;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.repository.CustomerRepository;
import hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import hotelico.service.booking.application.customer.port.in.iPortService.SocialService;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SocialPortServiceImpl implements SocialService {

    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public CustomerDTO addLinkedInCustomer(CustomerDTO customerDto, String linkedInId){

        CustomerDBEntity customerEntity = modelMapper.map(customerDto, CustomerDBEntity.class);

//        customerEntity.setLinkedInId(linkedInId);
//        customerEntity.setLogged(true);
//        customerEntity.setProfileImageUrl(customerDto.getProfileImageUrl());

        //TODO eugen: get Languages from linkedIn

        long virtualHotelId = -1;

//        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customerEntity), virtualHotelId);
//
//        dto = loginService.checkBeforeLoginProperties(customerDto, dto);
//
//        if(!dto.isHotelStaff() && !dto.isAdmin())
//        {
//            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
//        }

        return CustomerDTO.builder().build();
    }

    @Transactional
    @Override
    public CustomerDTO addFacebookCustomer(CustomerDTO customerDto, String facebookId){
//        CustomerDBEntity customerEntity = modelMapper.map(customerDto, CustomerDBEntity.class);
//        customerEntity.getEntityAggregate().setFacebookId(facebookId);
//        customerEntity.getEntityAggregate().setProfileImageUrl(customerDto.getProfileImageUrl());
//
//        //TODO eugen: get Languages from linkedIn
//        long virtualHotelId = lastMessagesService.getInitHotelId();
//
//        CustomerDTO dto = convertCustomerToDto(customerRepository.saveAndFlush(customerEntity), virtualHotelId);
//
//        dto = loginService.checkBeforeLoginProperties(customerDto, dto);
//
//        if(!dto.isHotelStaff() && !dto.isAdmin())
//        {
//            notificationService.notificateAboutEntityEvent(dto, HotelEvent.EVENT_REGISTER, "Now Registered!", dto.getId());
//        }
//
        return CustomerDTO.builder().build();
    }

    @Override
    public CustomerDTO getByLinkedInId(String linkedInId){
        List<CustomerDBEntity> customerEntities = customerRepository.findByLinkedInIdAndActive(linkedInId, true);

        if(customerEntities.isEmpty())
        {
            return null;
        }

        //TODO eugen: get Langguages from LinkedIn
//        CustomerDTO dto = convertMyCustomerToFullDto(customerEntities.get(0));

        return CustomerDTO.builder().build();
    }

    @Override
    public Optional<CustomerDTO> getByFacebookId(String facebookId){

        //TODO eugen: get Langguages from LinkedIn

        return customerRepository.findByFacebookIdAndActive(Objects.requireNonNull(facebookId), true)
                .stream()
                .findFirst().map(c -> Optional.of(modelMapper.map(c, CustomerDTO.class)))
                .orElse(Optional.empty());
    }
}
