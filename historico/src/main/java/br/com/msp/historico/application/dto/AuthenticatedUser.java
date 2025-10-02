package br.com.msp.historico.application.dto;

import java.util.Collection;
import java.util.Set;

public class AuthenticatedUser {
    private final Long id;
    private final String userEmail;
    private final Set<String> roles;

    public AuthenticatedUser(Long id, String userEmail, Collection<String> roles) {
        this.id = id;
        this.userEmail = userEmail;
        this.roles = Set.copyOf(roles);
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean hasRole(String role) {
        return roles.contains("ROLE_" + role);
    }

    public String getPrimaryRole() {
        return roles.stream()
                .findFirst()
                .orElse("ROLE_UNKNOWN");
    }

    public Set<String> getRoles() {
        return Set.copyOf(roles);
    }
}