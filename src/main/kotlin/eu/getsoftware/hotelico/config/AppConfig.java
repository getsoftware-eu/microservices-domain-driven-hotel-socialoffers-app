package eu.getsoftware.hotelico.config;

import eu.getsoftware.hotelico.aspects.NotifyAspect;

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
