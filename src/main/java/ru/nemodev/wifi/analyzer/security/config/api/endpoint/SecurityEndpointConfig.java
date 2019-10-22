package ru.nemodev.wifi.analyzer.security.config.api.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.nemodev.wifi.analyzer.security.api.processor.UserProcessor;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;

/**
 * created by simanov-an
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "ru.nemodev.wifi.analyzer.security.api.endpoint" })
public class SecurityEndpointConfig {

    private final UserService userService;

    public SecurityEndpointConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserProcessor userProcessor() {
        return new UserProcessor(userService);
    }
}
