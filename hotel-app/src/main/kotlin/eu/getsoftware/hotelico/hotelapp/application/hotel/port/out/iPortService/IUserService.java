package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.common.domain.IDomainEntity;
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
    
    IDomainEntity getByUserName(@NotNull String username);
}