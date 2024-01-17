package eu.getsoftware.hotelico.hotel.application.iservice;

import eu.getsoftware.hotelico.customer.domain.User;
import eu.getsoftware.hotelico.customer.infrastructure.dto.UserDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    
    User getByUserName(@NotNull String username);
}