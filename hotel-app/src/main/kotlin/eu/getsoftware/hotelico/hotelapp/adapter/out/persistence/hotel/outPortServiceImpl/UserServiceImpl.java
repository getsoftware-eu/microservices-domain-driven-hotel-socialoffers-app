package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.outPortServiceImpl;//package de.hotelico.service.impl;

import com.thoughtworks.xstream.mapper.Mapper;
import eu.getsoftware.hotelico.clients.api.clients.domain.user.IUserDomain;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.User;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.repository.UserDtoRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.common.dto.UserDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = {"userCache"})
public class UserServiceImpl implements IUserService
{
    private UserDtoRepository userDtoRepository; //eu ask: How to use adapter.repository in serviceImpl??? wo soll serviceImpl???
    private Mapper mapper;

    UserServiceImpl(
            UserDtoRepository userDtoRepository,
            Mapper mapper
    ){
        this.userDtoRepository = userDtoRepository;
        this.mapper = mapper;
    }
    
    public List<UserDTO> getUsers() {
        List<User> list = userDtoRepository.findAll();
        List<UserDTO> out = new ArrayList<UserDTO>();
        for (User dto : list) {
            out.add(mapper.map(dto, UserDTO.class));
        }
        return out;
    }

    public UserDTO getById(int userId) {
        User dto = userDtoRepository.getOne(userId);
        UserDTO out = dto==null? null : (mapper.map(dto, UserDTO.class));
        return out;
    }

    public List<UserDTO> getByHotelId(int hotelId) {
        List<User> dtoList = userDtoRepository.findByHotelId(hotelId);
        List<UserDTO> out = new ArrayList<UserDTO>();
        for (User dto : dtoList) {
            out.add(mapper.map(dto, UserDTO.class));
        }
        return out;
    }

    @Transactional
    @Override
    public UserDTO addUser(UserDTO userDto, String password) {
        User dto = mapper.map(userDto, User.class);
        dto.setPassword(password);
        return mapper.map(userDtoRepository.saveAndFlush(dto), UserDTO.class);
    }

    @Override
    public UserDTO checkLogin(String email, String password){
        User dto = userDtoRepository.findByEMail(email);

        if(dto != null && dto.getPassword().equals(password))
        {
            UserDTO out = (mapper.map(dto, UserDTO.class));
            return out;
        }
        else
            return null;
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDto) {
        User dto = userDtoRepository.getOne(userDto.id);
        if(dto!=null)
        {
//            dto.setHotelId(userDto.getHotelId());
//            dto.setFirstName(userDto.getFirstName());
//            dto.setLastName(userDto.getLastName());
//            dto.setCompany(userDto.getCompany());
//            dto.setEmail(userDto.getEmail());
        }
        return mapper.map(userDtoRepository.saveAndFlush(dto), UserDTO.class);
    }

    @Transactional
    @Override
    public void deleteUser(UserDTO userDto) {
        userDtoRepository.delete(userDto.getId());
    }

    @Override
    public boolean isEmailExists(String email)
    {
        //TODO extra query
       return userDtoRepository.findByEMail(email)!=null;
    }
	
	@Cacheable
    @NotNull
    public IUserDomain getByUserName(@NotNull String username) {
	
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
