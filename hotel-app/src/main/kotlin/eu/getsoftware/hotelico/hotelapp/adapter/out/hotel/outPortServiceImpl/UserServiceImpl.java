package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;//package de.hotelico.service.impl;

import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.UserEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.UserDtoRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.common.dto.UserDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IUserService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private ModelMapper mapper;

    UserServiceImpl(
            UserDtoRepository userDtoRepository,
            ModelMapper mapper
    ){
        this.userDtoRepository = userDtoRepository;
        this.mapper = mapper;
    }
    
    public List<UserDTO> getUsers() {
        List<UserEntity> list = userDtoRepository.findAll();
        List<UserDTO> out = new ArrayList<UserDTO>();
        for (UserEntity dto : list) {
            out.add(mapper.map(dto, UserDTO.class));
        }
        return out;
    }

    public UserDTO getById(int userId) {
        UserEntity dto = userDtoRepository.getOne(userId);
        UserDTO out = dto==null? null : (mapper.map(dto, UserDTO.class));
        return out;
    }

    @Override
    public List<UserDTO> getByHotelId(int userId) {
        return List.of();
    }

    public List<UserDTO> getByHotelId(long hotelId) {
        List<UserEntity> dtoList = userDtoRepository.findByHotelId(hotelId);
        List<UserDTO> out = new ArrayList<UserDTO>();
        for (UserEntity dto : dtoList) {
            out.add(mapper.map(dto, UserDTO.class));
        }
        return out;
    }

    @Transactional
    @Override
    public UserDTO addUser(UserDTO userDto, String password) {
        UserEntity dto = mapper.map(userDto, UserEntity.class);
//        dto.setPassword(password);
        return mapper.map(userDtoRepository.saveAndFlush(dto), UserDTO.class);
    }

    @Override
    public UserDTO checkLogin(String email, String password){
        UserEntity dto = userDtoRepository.findByEMail(email);

//        if(dto != null && dto.getPassword().equals(password))
        {
            UserDTO out = (mapper.map(dto, UserDTO.class));
            return out;
        }
//        else
//            return null;
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDto) {
        UserEntity dto = userDtoRepository.findBy(userDto.id).or;
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
    public UserEntity getByUserName(@NotNull String username) {
	
		log.info("getByUserName: username=$username");
	
		if(username == null)
		{
			return null;
		}
        
        List<UserEntity> findByUserName = userDtoRepository.findByUserName(username);
		
		if(findByUserName.size > 1)
		{
			log.error("$username: findByUserName.size > 1 ");
		}
		
		return findByUserName.lastOrNull();
	}
}
