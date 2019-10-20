package ru.nemodev.wifi.analyzer.security.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;


public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOptional = checkValidUser(userRepository.findByLogin(login));
        if (userOptional.isEmpty())
            throw new UsernameNotFoundException(String.format("Пользователь с login=%s не найден!", login));

        return userOptional.get();
    }

    @Override
    @Transactional
    public User create(User user) {
        user.setCreationDate(LocalDateTime.now());
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<User> findByLogin(String login) {
        return checkValidUser(userRepository.findByLogin(login));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<User> findByEmail(String email) {
        return checkValidUser(userRepository.findByEmail(email));
    }

    private Optional<User> checkValidUser(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            return userOptional;
        }

        User user = userOptional.get();
        if (!user.isEnabled()) {
            log.info(String.format("Пользователь с id=%s не активен!", user.getId()));
            return Optional.empty();
        }

        return userOptional;
    }
}
