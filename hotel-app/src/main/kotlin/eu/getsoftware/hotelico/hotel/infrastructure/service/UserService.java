package eu.getsoftware.hotelico.hotel.infrastructure.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.customer.domain.User;
import eu.getsoftware.hotelico.customer.infrastructure.dto.UserDto;

public interface UserService
{
    List<UserDto> getUsers();

    @Transactional
    UserDto addUser(UserDto userDto, String password);

    @Transactional
    UserDto updateUser(UserDto userDto);

    UserDto getById(int userId);

    List<UserDto> getByHotelId(int userId);

    @Transactional
    UserDto checkLogin(String email, String password);

    @Transactional
    void deleteUser(UserDto userDto);
    
    boolean isEmailExists(String email);
    
    @NotNull 
    User getByUserName(@NotNull String username);
}