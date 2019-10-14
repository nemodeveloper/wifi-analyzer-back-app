package ru.nemodev.wifi.analyzer.security.config;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import ru.nemodev.wifi.analyzer.security.config.property.SecurityProperty;
import ru.nemodev.wifi.analyzer.security.config.service.SecurityServiceConfig;

import javax.sql.DataSource;
import java.io.IOException;

import static org.apache.commons.codec.Charsets.UTF_8;


@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(SecurityProperty.class)
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final SecurityServiceConfig securityServiceConfig;
    private final SecurityProperty securityProperty;
    private final DataSource dataSource;

    public OAuth2AuthorizationConfig(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                                     SecurityServiceConfig securityServiceConfig, SecurityProperty securityProperty,
                                     DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.securityServiceConfig = securityServiceConfig;
        this.securityProperty = securityProperty;
        this.dataSource = dataSource;
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setClientDetailsService(securityServiceConfig.authClientDetailService());
        tokenServices.setAuthenticationManager(authenticationManager);
        return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        try {
            jwtAccessTokenConverter.setSigningKey(IOUtils.toString(securityProperty.getJwt().getPrivateKey().getInputStream(), UTF_8));
            jwtAccessTokenConverter.setVerifierKey(IOUtils.toString(securityProperty.getJwt().getPublicKey().getInputStream(), UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        // TODO понять почему не работает с JdbcTokenStore
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(securityServiceConfig.authClientDetailService());
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .reuseRefreshTokens(false)
            .tokenStore(tokenStore())
            .accessTokenConverter(accessTokenConverter())
            .authenticationManager(authenticationManager)
            .userDetailsService(securityServiceConfig.userService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .passwordEncoder(passwordEncoder)
            .allowFormAuthenticationForClients();
    }

}
