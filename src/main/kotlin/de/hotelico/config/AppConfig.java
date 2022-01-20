package de.hotelico.config;

import de.hotelico.aspects.NotifyAspect;
import de.hotelico.dto.ChatMessageDTO;
import de.hotelico.dto.CustomerDealDto;
import de.hotelico.dto.CustomerDTO;
import de.hotelico.dto.HotelActivityDto;
import de.hotelico.dto.MenuItemDto;
import de.hotelico.dto.MenuOrderDTO;
import de.hotelico.dto.WallPostDto;
import de.hotelico.model.ChatMessage;
import de.hotelico.model.Customer;
import de.hotelico.model.CustomerDeal;
import de.hotelico.model.Hotel;
import de.hotelico.model.HotelActivity;
import de.hotelico.model.HotelWallPost;
import de.hotelico.dto.HotelDTO;
import de.hotelico.model.MenuItem;
import de.hotelico.model.MenuOrder;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "de.hotelico" }, excludeFilters = { 
        @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION), 
        @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
})

@EnableAspectJAutoProxy
public class AppConfig {
    
    
    @Bean
    public ModelMapper modelMapper() {
    
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
    
    @Bean
    public NotifyAspect notifyAspect() {
        return new NotifyAspect();
    }
    
    
//    //#### session management
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//        servletContext.addListener(new SessionListener());
//    }

//    @Autowired
//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//        
//        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//        
//        sessionBuilder.addAnnotatedClasses(CustomerDto.class);
//        
//        return sessionBuilder.buildSessionFactory();
//    }
    
}
