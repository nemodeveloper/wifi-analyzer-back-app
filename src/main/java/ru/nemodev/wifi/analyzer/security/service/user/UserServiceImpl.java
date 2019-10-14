package ru.nemodev.wifi.analyzer.security.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;

import java.util.Optional;


public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isEmpty())
            throw new UsernameNotFoundException("USER NOT FOUND!");

        return userOptional.get();
    }
}
