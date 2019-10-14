package ru.nemodev.wifi.analyzer.security.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;


@Component
public class InitialUserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialUserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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

        userRepository.save(user);
    }
}
