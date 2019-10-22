package ru.nemodev.wifi.analyzer.security.entity.role;

/**
 * created by simanov-an
 */
public enum RoleType {

    ADMIN("ADMIN"),
    ANALYZER("ANALYZER");

    private final String role;

    RoleType(String role) {
        this.role = Role.NAME_PREF + role;
    }

    public String getRole() {
        return role;
    }
}
