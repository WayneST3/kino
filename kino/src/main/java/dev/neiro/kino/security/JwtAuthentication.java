package dev.neiro.kino.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

import static java.lang.String.valueOf;

/**
 * @author Wayne Stark
 * @since 17.10.2022
 */
@AllArgsConstructor
public class JwtAuthentication implements Authentication {

    private static final long serialVersionUID = 9024928164697283546L;

    private boolean authenticated;
    private Long userId;
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() { return authorities; }

    @Override
    public Object getDetails() { return null; }

    @Override
    public Object getPrincipal() { return userId; }

    @Override
    public boolean isAuthenticated() { return authenticated; }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() { return valueOf(userId); }

}
