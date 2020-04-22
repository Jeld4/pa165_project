package cz.fi.muni.pa165.service.config;



import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        return new DozerBeanMapper();
    }

}
