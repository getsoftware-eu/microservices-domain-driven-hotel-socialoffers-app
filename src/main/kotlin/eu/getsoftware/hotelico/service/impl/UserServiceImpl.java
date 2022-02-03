//package de.hotelico.service.impl;
//
//import eu.getsoftware.hotelico.model.User;
//import de.hotelico.dto.UserDto;
//import de.hotelico.repository.UserDtoRepository;
//import UserService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService
//{
//
//    @Autowired
//    private UserDtoRepository userDtoRepository;
//
//    @Autowired
//    private Mapper mapper;
//
//    public List<UserDto> getUsers() {
//        List<User> list = userDtoRepository.findAll();
//        List<UserDto> out = new ArrayList<UserDto>();
//        for (User dto : list) {
//            out.add(mapper.map(dto, UserDto.class));
//        }
//        return out;
//    }
//
//    public UserDto getById(int userId) {
//        User dto = userDtoRepository.getOne(userId);
//        UserDto out = dto==null? null : (mapper.map(dto, UserDto.class));
//        return out;
//    }
//
//    //TODO eugen: Transactional????
//    @Override
//    public List<UserDto> getByHotelId(int hotelId) {
//        List<User> dtoList = userDtoRepository.findByHotelId(hotelId);
//        List<UserDto> out = new ArrayList<UserDto>();
//        for (User dto : dtoList) {
//            out.add(mapper.map(dto, UserDto.class));
//        }
//        return out;
//    }
//
//    @Transactional
//    @Override
//    public UserDto addUser(UserDto userDto, String password) {
//        User dto = mapper.map(userDto, User.class);
//        dto.setPassword(password);
//        return mapper.map(userDtoRepository.saveAndFlush(dto), UserDto.class);
//    }
//
//    @Transactional
//    @Override
//    public UserDto checkLogin(String email, String password){
//        User dto = userDtoRepository.findByEMail(email);
//
//        if(dto != null && dto.getPassword().equals(password))
//        {
//            UserDto out = (mapper.map(dto, UserDto.class));
//            return out;
//        }
//        else
//            return null;
//    }
//
//    @Transactional
//    @Override
//    public UserDto updateUser(UserDto userDto) {
//        User dto = userDtoRepository.getOne(userDto.getId());
//        if(dto!=null)
//        {
//            dto.setHotelId(userDto.getHotelId());
//            dto.setFirstName(userDto.getFirstName());
//            dto.setLastName(userDto.getLastName());
//            dto.setCompany(userDto.getCompany());
//            dto.setEmail(userDto.getEmail());
//        }
//        return mapper.map(userDtoRepository.saveAndFlush(dto), UserDto.class);
//    }
//
//    @Transactional
//    @Override
//    public void deleteUser(UserDto userDto) {
//        userDtoRepository.delete(userDto.getId());
//    }
//    
//    @Transactional
//    @Override
//    public boolean isEmailExists(String email)
//    {
//        //TODO extra query
//       return userDtoRepository.findByEMail(email)!=null;
//    }
//}
