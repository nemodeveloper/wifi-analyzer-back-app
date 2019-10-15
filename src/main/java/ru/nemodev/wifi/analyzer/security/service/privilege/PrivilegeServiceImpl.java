package ru.nemodev.wifi.analyzer.security.service.privilege;

import ru.nemodev.wifi.analyzer.security.entity.privilege.Privilege;
import ru.nemodev.wifi.analyzer.security.repository.privilege.PrivilegeRepository;

import java.util.Optional;
import java.util.UUID;

public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Privilege create(Privilege privilege) {

        privilege.setId(UUID.randomUUID().toString());

        return privilegeRepository.saveAndFlush(privilege);
    }

    @Override
    public Optional<Privilege> findByName(String name) {
        return privilegeRepository.findByName(name);
    }
}
