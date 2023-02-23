package eu.getsoftware.hotelico.hotel.infrastructure.service.impl;//package de.hotelico.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.mapper.Mapper;

import eu.getsoftware.hotelico.customer.domain.User;
import eu.getsoftware.hotelico.customer.infrastructure.dto.UserDto;
import eu.getsoftware.hotelico.hotel.infrastructure.repository.UserDtoRepository;
import eu.getsoftware.hotelico.hotel.infrastructure.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig(cacheNames = {"userCache"})
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDtoRepository userDtoRepository; 
    
    @Autowired
    private Mapper mapper;
	
    public List<UserDto> getUsers() {
        List<User> list = userDtoRepository.findAll();
        List<UserDto> out = new ArrayList<UserDto>();
        for (User dto : list) {
            out.add(mapper.map(dto, UserDto.class));
        }
        return out;
    }

    public UserDto getById(int userId) {
        User dto = userDtoRepository.getOne(userId);
        UserDto out = dto==null? null : (mapper.map(dto, UserDto.class));
        return out;
    }

    public List<UserDto> getByHotelId(int hotelId) {
        List<User> dtoList = userDtoRepository.findByHotelId(hotelId);
        List<UserDto> out = new ArrayList<UserDto>();
        for (User dto : dtoList) {
            out.add(mapper.map(dto, UserDto.class));
        }
        return out;
    }

    @Transactional
    @Override
    public UserDto addUser(UserDto userDto, String password) {
        User dto = mapper.map(userDto, User.class);
        dto.setPassword(password);
        return mapper.map(userDtoRepository.saveAndFlush(dto), UserDto.class);
    }

    @Override
    public UserDto checkLogin(String email, String password){
        User dto = userDtoRepository.findByEMail(email);

        if(dto != null && dto.getPassword().equals(password))
        {
            UserDto out = (mapper.map(dto, UserDto.class));
            return out;
        }
        else
            return null;
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        User dto = userDtoRepository.getOne(userDto.id);
        if(dto!=null)
        {
//            dto.setHotelId(userDto.getHotelId());
//            dto.setFirstName(userDto.getFirstName());
//            dto.setLastName(userDto.getLastName());
//            dto.setCompany(userDto.getCompany());
//            dto.setEmail(userDto.getEmail());
        }
        return mapper.map(userDtoRepository.saveAndFlush(dto), UserDto.class);
    }

    @Transactional
    @Override
    public void deleteUser(UserDto userDto) {
        userDtoRepository.delete(userDto.getId());
    }

    @Override
    public boolean isEmailExists(String email)
    {
        //TODO extra query
       return userDtoRepository.findByEMail(email)!=null;
    }
	
	@Cacheable
	public User getByUserName(String username) {
	
		log.info("getByUserName: username=$username");
	
		if(username == null)
		{
			return null;
		}
        
        List<User> findByUserName = userDtoRepository.findByUserName(username);
		
		if(findByUserName.size > 1)
		{
			log.error("$username: findByUserName.size > 1 ");
		}
		
		return findByUserName.lastOrNull();
	}
}
