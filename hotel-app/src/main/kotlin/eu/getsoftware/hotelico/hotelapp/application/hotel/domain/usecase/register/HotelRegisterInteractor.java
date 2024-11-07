package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.usecase.register;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl.HotelServiceImpl;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.usecase.register.dto.HotelRegisterRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HotelRegisterInteractor {

    private final IHotelService iHotelService;
    private final HotelServiceImpl hotelServiceImpl;

    /**
     * Eugen:
     * Слой of "application rules" (Use Cases). 
     *
     * The system receives the (session)user name and password, 
     * A1: validates if the user doesn't exist,  (check DTO)
     * A3: and saves the new user ENTITY along with the creation time
     * (Use Cases be in different formats: as use cases or stories. We'll use a storytelling phrase:)
     *
     * @param requestModel (session requester)
     * @return
     */
    @Override
    public HotelDTO create(HotelRegisterRequestDTO requestModel) {
        //A1 - ONLY DTOs
//        if (iHotelService.existsHotelByCode(requestModel.hotelCode())) {
//            return userOutputApplicationPresenter.prepareFailView("Hotel already exists.");
//        }
//        //Domain creation
//        HotelRootEntity hotelEntity = hotelFactoryAggregate.create(requestModel.name(), requestModel.hotelCode());
//        if (!hotelEntity.hotelcodeIsValid()) {
//            return userOutputApplicationPresenter.prepareFailView("Hotelcode must have more than 5 characters.");
//        }
//        //A3
//        LocalDateTime now = LocalDateTime.now();
//        //eu: if Onion needs upperDTO : UserDsRequestApplicationModelDTO userDsModelDTO = new UserDsRequestApplicationModelDTO(hotelEntity.getName(), hotelEntity.hotelcode(), now);
//
//        iHotelService.save(hotelEntity);
//
//        // ResponseModel != userDTO (UserDsRequestApplicationModelDTO)
//        HotelDTO accountResponseModel = new HotelDTO(hotelEntity.name(), LocalDateTime.now().toString());
//        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    
        return HotelDTO.builder().build();
    }


    public HotelRootEntity createHotelFromProjection()
    {
        return null;
    }
}
