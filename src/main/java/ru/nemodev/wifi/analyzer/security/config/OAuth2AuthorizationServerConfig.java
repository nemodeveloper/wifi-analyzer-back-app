package ru.nemodev.wifi.analyzer.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;

import static org.springframework.security.oauth2.common.OAuth2AccessToken.REFRESH_TOKEN;


@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public OAuth2AuthorizationServerConfig(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserService userService)
    {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("privateKey");
        converter.setVerifierKey("publicKey");
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            .withClient("test")
            .secret("$2a$10$Qh9Cjo0w4nMBSsrjML0n0OLLwcBxRGg2mrWs403kKE4sIorqJHq5y")
            .authorizedGrantTypes("password", REFRESH_TOKEN)
            .scopes("browser", "mobile")
            .accessTokenValiditySeconds(600)
            .refreshTokenValiditySeconds(3600);
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .tokenStore(tokenStore())
            .authenticationManager(authenticationManager)
            .userDetailsService(userService);
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
