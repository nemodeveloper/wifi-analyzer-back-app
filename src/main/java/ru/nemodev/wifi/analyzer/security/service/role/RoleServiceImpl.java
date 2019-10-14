package ru.nemodev.wifi.analyzer.security.service.role;

import ru.nemodev.wifi.analyzer.security.repository.role.RoleRepository;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
