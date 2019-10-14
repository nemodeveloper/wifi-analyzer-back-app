package ru.nemodev.wifi.analyzer.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;
import ru.nemodev.wifi.analyzer.security.service.user.UserServiceImpl;

@Configuration
public class SecurityServiceConfig
{
    private final UserRepository userRepository;

    public SecurityServiceConfig(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService()
    {
        return new UserServiceImpl(userRepository);
    }

}
