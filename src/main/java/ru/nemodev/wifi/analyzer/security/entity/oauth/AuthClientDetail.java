package ru.nemodev.wifi.analyzer.security.entity.oauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Entity
@Table(name = "OAUTH_CLIENT_DETAILS")
public class AuthClientDetail implements ClientDetails {

    @Id
    @Column(name = "CLIENT_ID", updatable = false, nullable = false)
    private String clientId;

    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;

    @Column(name = "SCOPE", nullable = false)
    private String scope;

    @Column(name = "AUTHORIZED_GRANT_TYPES", nullable = false)
    private String authorizedGrantTypes;

    @Column(name = "AUTHORITIES", nullable = false)
    private String authorities;

    @Column(name = "ACCESS_TOKEN_VALIDITY", nullable = false)
    private Integer accessTokenValidity;

    @Column(name = "REFRESH_TOKEN_VALIDITY", nullable = false)
    private Integer refreshTokenValidity;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return Set.of();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return Set.of(scope.split(","));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return Set.of(authorizedGrantTypes.split(","));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Set.of();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Map.of();
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }
}
