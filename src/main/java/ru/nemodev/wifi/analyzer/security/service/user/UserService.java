package ru.nemodev.wifi.analyzer.security.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.nemodev.wifi.analyzer.security.entity.user.User;

import java.util.Optional;


public interface UserService extends UserDetailsService {

    User create(User user);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
}
