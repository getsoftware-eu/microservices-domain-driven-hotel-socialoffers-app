package eu.getsoftware.hotelico.hotel.application.usecases;

import eu.getsoftware.hotelico.hotel.application.iService.IUserService;

import java.time.LocalDateTime;

public class UserRegisterInteractor {
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
    public UserResponseApplicationModelDTO create(UserRequestApplicationModelDTO requestModel) {
        //A1 - ONLY DTOs
        if (IUserService.getByUserName(requestModel.name())) {
            return userOutputApplicationPresenter.prepareFailView("User already exists.");
        }
        //Domain creation
        UserEntity userEntity = userFactoryAggregate.create(requestModel.name(), requestModel.password());
        if (!userEntity.passwordIsValid()) {
            return userOutputApplicationPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //A3
        LocalDateTime now = LocalDateTime.now();
        UserDsRequestApplicationModelDTO userDsModelDTO = new UserDsRequestApplicationModelDTO(userEntity.getName(), userEntity.getPassword(), now);

        userDsGateway.save(userDsModelDTO);

        // ResponseModel != userDTO (UserDsRequestApplicationModelDTO)
        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userDsModelDTO.name(), LocalDateTime.now().toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }

}
