package ru.nemodev.wifi.analyzer.security.service.privilege;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.security.entity.privilege.Privilege;
import ru.nemodev.wifi.analyzer.security.repository.privilege.PrivilegeRepository;

import java.util.Optional;

public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    @Transactional
    public Privilege create(Privilege privilege) {
        return privilegeRepository.saveAndFlush(privilege);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Privilege> findByName(String name) {
        return privilegeRepository.findByName(name);
    }
}
