package eu.getsoftware.hotelico.service.booking.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@ComponentScan(basePackages = { "eu.getsoftware.hotelico" }, excludeFilters = { 
//        @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION), 
//        @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
//})

@EnableAspectJAutoProxy
public class AppConfig {
    
    
    
    
//    @Bean
//    public RabbitMQNotifyAspect notifyAspect() {
//        return new RabbitMQNotifyAspect();
//    }
    
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
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
