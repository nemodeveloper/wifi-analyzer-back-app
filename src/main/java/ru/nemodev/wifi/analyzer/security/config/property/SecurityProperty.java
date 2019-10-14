package ru.nemodev.wifi.analyzer.security.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties("security")
public class SecurityProperty {

    private JwtProperty jwt;

    public JwtProperty getJwt() {
        return jwt;
    }

    public void setJwt(JwtProperty jwt) {
        this.jwt = jwt;
    }

    public static class JwtProperty {

        private Resource privateKey;
        private Resource publicKey;

        public Resource getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(Resource privateKey) {
            this.privateKey = privateKey;
        }

        public Resource getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(Resource publicKey) {
            this.publicKey = publicKey;
        }
    }

}
