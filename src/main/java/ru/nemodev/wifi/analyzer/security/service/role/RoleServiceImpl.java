package ru.nemodev.wifi.analyzer.security.service.role;

import ru.nemodev.wifi.analyzer.security.entity.role.Role;
import ru.nemodev.wifi.analyzer.security.repository.role.RoleRepository;

import java.util.Optional;
import java.util.UUID;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {

        role.setId(UUID.randomUUID().toString());

        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
