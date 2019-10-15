package ru.nemodev.wifi.analyzer.security.service.role;

import ru.nemodev.wifi.analyzer.security.entity.role.Role;

import java.util.Optional;

public interface RoleService {

    Role create(Role role);
    Optional<Role> findByName(String name);
}
