package ru.nemodev.wifi.analyzer.security.service.oauth;

import org.springframework.security.oauth2.provider.ClientRegistrationException;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;
import ru.nemodev.wifi.analyzer.security.repository.oauth.AuthClientDetailRepository;

public class AuthClientDetailServiceImpl implements AuthClientDetailService {

    private final AuthClientDetailRepository authClientDetailRepository;

    public AuthClientDetailServiceImpl(AuthClientDetailRepository authClientDetailRepository) {
        this.authClientDetailRepository = authClientDetailRepository;
    }

    @Override
    public AuthClientDetail loadClientByClientId(String clientId) throws ClientRegistrationException {
        return authClientDetailRepository.findByClientId(clientId).orElse(null);
    }
}
