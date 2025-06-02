package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.outPortServiceImpl;//package de.hotelico.service.impl;

import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.mapper.UserDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.UserEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.repository.UserRepository;
import eu.getsoftware.hotelico.service.booking.application.customer.common.dto.UserDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IUserService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
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
    private UserRepository userRepository; //eu ask: How to use adapter.repository in serviceImpl??? wo soll serviceImpl???
    private UserDtoMapper userEntityMapper;

    UserServiceImpl(
            UserRepository userRepository,
            UserDtoMapper userEntityMapper
    ){
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }
    
    public List<UserDTO> getUsers() {
        List<UserEntity> list = userRepository.findAll();
        List<UserDTO> out = new ArrayList<UserDTO>();
        for (UserEntity nextEntity : list) {
            out.add(userEntityMapper.toDto(nextEntity));
        }
        return out;
    }

    public UserDTO getById(int userId) {
        UserEntity entity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        UserDTO out = entity==null? null : (userEntityMapper.toDto(entity));
        return out;
    }

    @Override
    public List<UserDTO> getByHotelId(int userId) {
        return List.of();
    }

    public List<UserDTO> getByHotelId(long hotelId) {
        List<UserEntity> entityList = userRepository.findByHotelId(hotelId);
        List<UserDTO> out = new ArrayList<UserDTO>();
        for (UserEntity next : entityList) {
            out.add(userEntityMapper.toDto(next));
        }
        return out;
    }

    @Transactional
    @Override
    public UserDTO addUser(UserDTO userDto, String password) {
        UserEntity entity = userEntityMapper.toEntity(userDto);
//        dto.setPassword(password);
        return userEntityMapper.toDto(userRepository.saveAndFlush(entity));
    }

    @Override
    public UserDTO checkLogin(String email, String password){
        UserEntity dto = userRepository.findByEMail(email);

//        if(dto != null && dto.getPassword().equals(password))
        {
            UserDTO out = (userEntityMapper.toDto(dto));
            return out;
        }
//        else
//            return null;
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDto) {
        UserEntity entity = userRepository.findByDomainId(userDto.getDomainEntityId());
        if(entity!=null)
        {
//            entity.setHotelId(userDto.getHotelId());
//            entity.setFirstName(userDto.getFirstName());
//            entity.setLastName(userDto.getLastName());
//            entity.setCompany(userDto.getCompany());
//            entity.setEmail(userDto.getEmail());
        }
        return userEntityMapper.toDto(userRepository.saveAndFlush(entity));
    }

    @Transactional
    @Override
    public void deleteUser(UserDTO userDto) {
        userRepository.deleteByDomainEntityIdValue(userDto.getDomainEntityId());
    }

    @Override
    public boolean isEmailExists(String email)
    {
        //TODO extra query
       return userRepository.findByEMail(email)!=null;
    }
	
	@Cacheable
    @NotNull
    public UserEntity getByUserName(@NotNull String username) {
	
		log.info("getByUserName: username=$username");
	
		if(username == null)
		{
			return null;
		}
        
        List<UserEntity> findByUserName = userRepository.findByUserName(username);
		
		if(findByUserName.size() > 1)
		{
			log.error("$username: findByUserName.size > 1 ");
		}
		
		return findByUserName.stream().findAny().orElse(null);
	}
}
