package ru.nemodev.wifi.analyzer.security.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.security.entity.role.Role;
import ru.nemodev.wifi.analyzer.security.entity.role.RoleType;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;
import ru.nemodev.wifi.analyzer.security.service.role.RoleService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
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
    public User create(User user, String password, boolean isAdmin) {
        if (existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException(String.format("Пользователь с Login = %s уже зарегистирован!", user.getLogin()));
        }

        String roleName = isAdmin ? RoleType.ADMIN.getRole() : RoleType.ANALYZER.getRole();
        Optional<Role> roleOptional = roleService.findByName(roleName);
        if (roleOptional.isEmpty()) {
            throw new IllegalStateException(String.format("Не удалось найти схему прав с названием = %s", roleName));
        }

        user.setCreationDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(List.of(roleOptional.get()));
        user.setEnabled(true);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User save(User user, String password, boolean isAdmin) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<User> findByLogin(String login) {
        return checkValidUser(userRepository.findByLogin(login));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> findBy(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Optional<User> userOptional = findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException(String.format("Не удалось найти пользователя по id=%s", id));
        }

        User user = userOptional.get();
        user.setEnabled(false);

        userRepository.saveAndFlush(user);
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
