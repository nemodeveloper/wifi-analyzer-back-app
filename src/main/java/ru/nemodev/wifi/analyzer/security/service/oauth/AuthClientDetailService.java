package ru.nemodev.wifi.analyzer.security.service.oauth;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;

import java.util.Optional;

public interface AuthClientDetailService extends ClientDetailsService {

    AuthClientDetail create(AuthClientDetail authClientDetail);
    Optional<AuthClientDetail> findByClientId(String clientId);
}
