package eu.getsoftware.hotelico.hotel.infrastructure.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.customer.domain.User;
import eu.getsoftware.hotelico.customer.infrastructure.dto.UserDTO;

public interface UserService
{
    List<UserDTO> getUsers();

    @Transactional
    UserDTO addUser(UserDTO userDto, String password);

    @Transactional
    UserDTO updateUser(UserDTO userDto);

    UserDTO getById(int userId);

    List<UserDTO> getByHotelId(int userId);

    @Transactional
    UserDTO checkLogin(String email, String password);

    @Transactional
    void deleteUser(UserDTO userDto);
    
    boolean isEmailExists(String email);
    
    @NotNull 
    User getByUserName(@NotNull String username);
}