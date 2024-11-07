package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.UserEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.common.dto.UserDTO;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * похож на in.port 
 */
public interface IUserService
{
    List<UserDTO> getUsers();
    
    UserDTO addUser(UserDTO userDto, String password);

    UserDTO updateUser(UserDTO userDto);

    UserDTO getById(int userId);

    List<UserDTO> getByHotelId(int userId);

    @Transactional
    UserDTO checkLogin(String email, String password);

    @Transactional
    void deleteUser(UserDTO userDto);
    
    boolean isEmailExists(String email);
    
    UserEntity getByUserName(@NotNull String username);
}