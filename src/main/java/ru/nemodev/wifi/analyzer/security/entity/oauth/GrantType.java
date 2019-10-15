package ru.nemodev.wifi.analyzer.security.entity.oauth;

import java.util.ArrayList;
import java.util.List;

/**
 * created by simanov-an
 */
public enum  GrantType {

    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    private String value;
    GrantType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getAllGrantTypes() {
        List<String> allGrantTypes = new ArrayList<>();
        for (GrantType grantType : values()) {
            allGrantTypes.add(grantType.getValue());
        }

        return String.join(",", allGrantTypes);
    }

}
