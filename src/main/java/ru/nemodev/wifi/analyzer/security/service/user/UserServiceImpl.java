package ru.nemodev.wifi.analyzer.security.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;

import java.util.Optional;
import java.util.UUID;


public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isEmpty())
            throw new UsernameNotFoundException(String.format("Пользователь с login=%s не найден!", login));

        return userOptional.get();
    }

    @Override
    public User create(User user) {

        user.setId(UUID.randomUUID().toString());

        return userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
