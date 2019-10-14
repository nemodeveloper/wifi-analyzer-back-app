package ru.nemodev.wifi.analyzer.security.repository.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.wifi.analyzer.security.entity.privilege.Privilege;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, String>
{
    Optional<Privilege> findByName(String name);
}
