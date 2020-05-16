package cz.fi.muni.pa165.service.config;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Radim Sasinka
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = {"cz.fi.muni.pa165"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        return new DozerBeanMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
