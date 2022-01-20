package de.hotelico.service;


import de.hotelico.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService
{

    List<UserDto> getUsers();

    @Transactional
    UserDto addUser(UserDto userDto, String password);

    @Transactional
    UserDto updateUser(UserDto userDto);

    @Transactional
    UserDto getById(int userId);

    @Transactional
    List<UserDto> getByHotelId(int userId);

    @Transactional
    UserDto checkLogin(String email, String password);

    @Transactional
    void deleteUser(UserDto userDto);
    
    @Transactional
    boolean isEmailExists(String email);
}