package ru.nemodev.wifi.analyzer.config.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.nemodev.wifi.analyzer.config.service.ServiceConfig;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "ru.nemodev.wifi.analyzer.api.endpoint" })
public class EndpointConfig {
    private final ServiceConfig serviceConfig;

    public EndpointConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }
}
