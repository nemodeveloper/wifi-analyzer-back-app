package ru.nemodev.wifi.analyzer.security.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.wifi.analyzer.security.entity.user.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    Optional<User> findByLogin(String username);
    Optional<User> findByEmail(String email);
}
