package ru.nemodev.wifi.analyzer.security.config.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.wifi.analyzer.security.repository.oauth.AuthClientDetailRepository;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;
import ru.nemodev.wifi.analyzer.security.service.oauth.AuthClientDetailService;
import ru.nemodev.wifi.analyzer.security.service.oauth.AuthClientDetailServiceImpl;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;
import ru.nemodev.wifi.analyzer.security.service.user.UserServiceImpl;

@Configuration
public class SecurityServiceConfig
{
    private final UserRepository userRepository;
    private final AuthClientDetailRepository authClientDetailRepository;

    public SecurityServiceConfig(UserRepository userRepository, AuthClientDetailRepository authClientDetailRepository)
    {
        this.userRepository = userRepository;
        this.authClientDetailRepository = authClientDetailRepository;
    }

    @Bean
    public UserService userService()
    {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public AuthClientDetailService authClientDetailService() {
        return new AuthClientDetailServiceImpl(authClientDetailRepository);
    }
}
