package ru.nemodev.wifi.analyzer.config.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.nemodev.wifi.analyzer.api.processor.WifiAnalyzeReportProcessor;
import ru.nemodev.wifi.analyzer.config.service.ServiceConfig;
import ru.nemodev.wifi.analyzer.core.api.SerializerConfiguration;

import java.util.List;


@EnableWebMvc
@Configuration
@Import(SerializerConfiguration.class)
@ComponentScan(basePackages = { "ru.nemodev.wifi.analyzer.api.endpoint" })
public class EndpointConfig implements WebMvcConfigurer {
    private final ServiceConfig serviceConfig;
    private final ObjectMapper objectMapper;

    public EndpointConfig(ServiceConfig serviceConfig, ObjectMapper objectMapper) {
        this.serviceConfig = serviceConfig;
        this.objectMapper = objectMapper;
    }

    @Bean
    public WifiAnalyzeReportProcessor wifiAnalyzeReportProcessor() {
        return new WifiAnalyzeReportProcessor(serviceConfig.wifiAnalyzeReportService());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonMessageConverter.setObjectMapper(objectMapper);
                return;
            }
        }
    }
}
