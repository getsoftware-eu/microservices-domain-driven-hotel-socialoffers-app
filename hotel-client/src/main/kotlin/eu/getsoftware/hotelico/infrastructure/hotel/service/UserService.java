package eu.getsoftware.hotelico.infrastructure.hotel.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.infrastructure.hotel.dto.UserDto;

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