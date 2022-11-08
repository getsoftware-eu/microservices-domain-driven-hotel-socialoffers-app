package eu.getsoftware.hotelico.main.hotel.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import eu.getsoftware.hotelico.infrastructure.hotel.interceptor.CustomerInterceptor;

@Configuration
@ComponentScan(basePackages = { "eu.getsoftware.hotelico.infrastructure.hotel.controller" })
public class WebAppConfig implements WebMvcConfigurer
{
        //adds four view controllers.
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/login-error").setViewName("login-error");
//        registry.addResourceHandler("/libs/**").addResourceLocations("/libs/");
//        registry.addResourceHandler("/app/**").addResourceLocations("/app/");
//        registry.addResourceHandler("/angulr/**").addResourceLocations("/angulr/");
//        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
//        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//        registry.addResourceHandler("/docs/**").addResourceLocations("/docs/");
    }
        
//        @Bean
//        public ModelMapper modelMapper(){
//    
//            ModelMapper modelMapper = new ModelMapper();
//            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//            return modelMapper;
//        }
        
        // ### internationalization ###
        
        @Bean
        LocaleResolver localeResolver() {
            SessionLocaleResolver slr = new SessionLocaleResolver();
            slr.setDefaultLocale(Locale.GERMAN);
            return slr;
        }
        
        @Bean
        LocaleChangeInterceptor localeChangeInterceptor() {
        var localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
        }
        
        @Override public void addInterceptors(InterceptorRegistry registry){
            LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
            interceptor.setParamName("my-locale");
            registry.addInterceptor(interceptor);
        }

//    @Bean
//    public InternalResourceViewResolver getInternalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
    
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
    
    @Bean
    public WebContentInterceptor webContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheSeconds(0);
        interceptor.setUseExpiresHeader(true);
        interceptor.setUseCacheControlHeader(true);
        interceptor.setUseCacheControlNoStore(true);
        
        return interceptor;
    }
    
    @Bean
    public CustomerInterceptor customerInterceptor() {
        CustomerInterceptor customerInterceptor = new CustomerInterceptor(/*usersConnectionRepository*/);
        
        return customerInterceptor;
    }

    /**
     * static content???
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/libs/**").addResourceLocations("/libs/");
        registry.addResourceHandler("/app/**").addResourceLocations("/app/");
        registry.addResourceHandler("/angulr/**").addResourceLocations("/angulr/");
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/docs/**").addResourceLocations("/docs/");
    }
    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(webContentInterceptor());  
//       
//        registry.addInterceptor(customerInterceptor());  
//    }
//	
//    //##############social
//
//   	public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/signin");
//        registry.addViewController("/signout");
//    }

//    @Bean
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        return viewResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        templateEngine.addDialect(new SpringSocialDialect());
//        return templateEngine;
//    }
//
//    @Bean
//    public TemplateResolver templateResolver() {
//        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("/views/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        return templateResolver;
//    }    @Bean
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        return viewResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        templateEngine.addDialect(new SpringSocialDialect());
//        return templateEngine;
//    }
//
//    @Bean
//    public TemplateResolver templateResolver() {
//        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("/views/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        return templateResolver;
//    }

    private static final String facebookClientSecret = "f492cdd7e0d9aee36ce8446c5caa440d";

    private static final String facebookClientId = "383626641847356";
    
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new SignedRequestArgumentResolver(facebookClientSecret));
//    }
//    
//    private @Inject UsersConnectionRepository usersConnectionRepository;

//    private @Inject Environment environment;


}
