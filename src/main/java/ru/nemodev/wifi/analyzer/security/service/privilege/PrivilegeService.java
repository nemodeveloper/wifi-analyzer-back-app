package ru.nemodev.wifi.analyzer.security.service.privilege;

import ru.nemodev.wifi.analyzer.security.entity.privilege.Privilege;

import java.util.Optional;

public interface PrivilegeService {

    Privilege create(Privilege privilege);
    Optional<Privilege> findByName(String name);
}
