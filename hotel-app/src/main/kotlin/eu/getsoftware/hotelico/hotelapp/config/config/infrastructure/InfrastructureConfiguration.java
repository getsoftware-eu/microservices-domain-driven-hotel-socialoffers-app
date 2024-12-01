package eu.getsoftware.hotelico.hotelapp.config.config.infrastructure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "eu.getsoftware.hotelico.hotelapp.adapter.out" }) //TODO Eugen: all domains as array?
@EntityScan(basePackages = {"eu.getsoftware.hotelico.hotelapp.adapter.out"}) //TODO Eugen: all domains as array?
public class InfrastructureConfiguration
{
	
	//eu: not moving Beans to external folders...
//	@Bean
//	BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
//		return beanFactory -> {
//			genericApplicationContext((BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry).getBeanFactory());
//		};
//	}
//	
//	void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
//		ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
//		beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
//		beanDefinitionScanner.scan("eu.getsoftware.hotelico"); //TODO Eugen: all domains as array?
//	}
//	
//	static TypeFilter removeModelAndEntitiesFilter() {
//		return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
//				.getClassName()
//				.endsWith("Model");
//	}
}
