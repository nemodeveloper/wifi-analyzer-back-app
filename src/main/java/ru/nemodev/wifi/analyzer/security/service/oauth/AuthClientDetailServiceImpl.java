package ru.nemodev.wifi.analyzer.security.service.oauth;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;
import ru.nemodev.wifi.analyzer.security.repository.oauth.AuthClientDetailRepository;

import java.util.Optional;

public class AuthClientDetailServiceImpl implements AuthClientDetailService {

    private final AuthClientDetailRepository authClientDetailRepository;

    public AuthClientDetailServiceImpl(AuthClientDetailRepository authClientDetailRepository) {
        this.authClientDetailRepository = authClientDetailRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public AuthClientDetail loadClientByClientId(String clientId) {
        return findByClientId(clientId).orElse(null);
    }

    @Override
    @Transactional
    public AuthClientDetail create(AuthClientDetail authClientDetail) {
        return authClientDetailRepository.saveAndFlush(authClientDetail);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<AuthClientDetail> findByClientId(String clientId) {
        return authClientDetailRepository.findByClientId(clientId);
    }
}
