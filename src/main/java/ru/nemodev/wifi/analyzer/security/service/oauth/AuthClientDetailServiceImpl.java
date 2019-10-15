package ru.nemodev.wifi.analyzer.security.service.oauth;

import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;
import ru.nemodev.wifi.analyzer.security.repository.oauth.AuthClientDetailRepository;

import java.util.Optional;

public class AuthClientDetailServiceImpl implements AuthClientDetailService {

    private final AuthClientDetailRepository authClientDetailRepository;

    public AuthClientDetailServiceImpl(AuthClientDetailRepository authClientDetailRepository) {
        this.authClientDetailRepository = authClientDetailRepository;
    }

    @Override
    public AuthClientDetail loadClientByClientId(String clientId) {
        return findByClientId(clientId).orElse(null);
    }

    @Override
    public AuthClientDetail create(AuthClientDetail authClientDetail) {
        return authClientDetailRepository.saveAndFlush(authClientDetail);
    }

    @Override
    public Optional<AuthClientDetail> findByClientId(String clientId) {
        return authClientDetailRepository.findByClientId(clientId);
    }
}
