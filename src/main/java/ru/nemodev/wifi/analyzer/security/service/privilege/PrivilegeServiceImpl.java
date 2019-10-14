package ru.nemodev.wifi.analyzer.security.service.privilege;

import ru.nemodev.wifi.analyzer.security.repository.privilege.PrivilegeRepository;

public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }
}
