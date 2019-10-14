package ru.nemodev.wifi.analyzer.security.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.wifi.analyzer.security.entity.role.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>
{
    Optional<Role> findByName(String name);
}
