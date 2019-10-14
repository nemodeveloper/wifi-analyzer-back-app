package ru.nemodev.wifi.analyzer.security.config.populator;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.repository.oauth.AuthClientDetailRepository;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;


@Component
public class InitialUserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final AuthClientDetailRepository authClientDetailRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialUserLoader(UserRepository userRepository,
                             AuthClientDetailRepository authClientDetailRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authClientDetailRepository = authClientDetailRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        User user = new User();
        user.setId("123");
        user.setEmail("test");
        user.setEnabled(true);
        user.setLogin("admin");
        user.setPassword(passwordEncoder.encode("1234"));

        userRepository.saveAndFlush(user);

        AuthClientDetail authClientDetail = new AuthClientDetail();
        authClientDetail.setClientId("mobile");
        authClientDetail.setClientSecret("$2a$10$Qh9Cjo0w4nMBSsrjML0n0OLLwcBxRGg2mrWs403kKE4sIorqJHq5y");
        authClientDetail.setAuthorizedGrantTypes("password,client_credentials,refresh_token");
        authClientDetail.setScope("read,write");
        authClientDetail.setAccessTokenValidity(600);
        authClientDetail.setRefreshTokenValidity(3600);

        authClientDetailRepository.saveAndFlush(authClientDetail);
    }
}
