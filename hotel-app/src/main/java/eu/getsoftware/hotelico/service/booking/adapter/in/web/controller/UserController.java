package eu.getsoftware.hotelico.service.booking.adapter.in.web.controller;//package de.hotelico.controller;
//
//import NotifyClients;
//import de.hotelico.dto.UserDto;
//import UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.List;
//
///**
// * RESTful webservice
// * REST Methoden with params for service execution
// */
//
//@Controller
////@RestController
//@RequestMapping("/users")
//public class UserController
//{
//
//    @Autowired
//    private UserService service;
//    
//    /**
//     * jsp file
//     */
//    @RequestMapping(method = RequestMethod.GET)
//    public String viewUsers() {
//        return "users";
//    }
//
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public @ResponseBody List<UserDto> getUsers() {
//        return service.getUsers();
//    }
//
//
//    @RequestMapping(value = "/hotelId/{hotelId}", method = RequestMethod.GET)
//    public @ResponseBody List<UserDto> getByHotelId(@PathVariable long hotelId) {
//        List<UserDto> out = service.getByHotelId(hotelId);
//        return out;
//    }
//
//
//    // @NotifyClients
//    @RequestMapping(value = "/users/{id}", method = HttpMethod.PUT)
//    public @ResponseBody
//    UserDto update(@PathVariable int id, @RequestBody UserDto userDto) {
//        userDto.setId(id);
//        UserDto out = service.updateUser(userDto);
//        return out;
//    }
//
//    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
//    public @ResponseBody
//    UserDto getById(@PathVariable int id) {
//        UserDto out = service.getById(id);
//        return out;
//    }
//
//    @RequestMapping(value = "/email/{email}/password/{password}", method = RequestMethod.GET)
//    public/* @ResponseBody*/
//    UserDto checkLogin(@PathVariable String email, @PathVariable String password) {
//        UserDto out = service.checkLogin(email, password);
//        return out;
//    } 
//    
////    @RequestMapping(value = "/register/email/{email}/password/{password}"/*/firstName/{firstName}/lastName/{lastName}"*/, method = RequestMethod.GET)
////    public/* @ResponseBody*/
////    User addGet(@PathVariable String email, @PathVariable String password /*, @PathVariable String firstName, @PathVariable String lastName*/) {
////
////        if(service.isEmailExists(email)) 
////        {
////            //TODO send error!!!!
////            return null;
////        };
////        
////        User newUser = new User();
////        
////        newUser.setEmail(email);
//////        newUser.setFirstName(firstName);
//////        newUser.setLastName(lastName);
////        
////        User out = service.addUser(newUser, password);
////
////        return out;
////    } 
//
//    //@NotifyClients
//    @RequestMapping(value = "/users", method = HttpMethod.POST)
//    public @ResponseBody
//    UserDto add(@RequestBody UserDto userDto) {
//        UserDto out = service.addUser(userDto, "password");
//        return out;
//    }
//    
//    @NotifyClients
//    @RequestMapping(value = "/users", method = HttpMethod.PUT)
//    public/* @ResponseBody*/
//    UserDto put(@RequestBody UserDto userDto) {
//        UserDto out = service.addUser(userDto, "password");
//        return out;
//    }
//
//    @NotifyClients
//    @RequestMapping(value = "/users/{id}", method = HttpMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable int id) {
//        UserDto task = new UserDto();
//        task.setId(id);
//        service.deleteUser(task);
//    }
//}
