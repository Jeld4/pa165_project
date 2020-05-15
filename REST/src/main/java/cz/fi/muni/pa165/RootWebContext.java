package cz.fi.muni.pa165;

        import cz.fi.muni.pa165.dto.UserDTO;
        import cz.fi.muni.pa165.service.config.ServiceConfiguration;
        import org.springframework.context.annotation.*;
        import org.springframework.http.converter.HttpMessageConverter;
        import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
        import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
        import com.fasterxml.jackson.databind.DeserializationFeature;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.fasterxml.jackson.databind.SerializationFeature;
        import com.fasterxml.jackson.databind.MapperFeature;

        import java.text.SimpleDateFormat;
        import java.util.List;
        import java.util.Locale;

/**
 * @author Radim Sasinka, 456315
 */

@EnableWebMvc
@Configuration
@Import({ServiceConfiguration.class})
@ComponentScan(basePackages = {"cz.fi.muni.pa165"})
public class RootWebContext implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginInterceptor());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    @Primary
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));

        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }

}
