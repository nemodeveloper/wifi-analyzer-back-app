package ru.nemodev.wifi.analyzer.security.repository.oauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;

import java.util.Optional;

@Repository
public interface AuthClientDetailRepository extends JpaRepository<AuthClientDetail, String> {

    Optional<AuthClientDetail> findByClientId(String clientId);
}
