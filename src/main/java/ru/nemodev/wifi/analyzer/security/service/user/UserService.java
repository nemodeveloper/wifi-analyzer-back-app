package ru.nemodev.wifi.analyzer.security.service.user;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.nemodev.wifi.analyzer.security.entity.user.User;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {

    User create(User user, String password, boolean isAdmin);
    User save(User user, String password, boolean isAdmin);
    Optional<User> findById(String id);
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    List<User> findBy(Pageable pageable);
    void deleteById(String id);
}
