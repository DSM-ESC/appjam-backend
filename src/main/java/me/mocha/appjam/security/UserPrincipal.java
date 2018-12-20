package me.mocha.appjam.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.mocha.appjam.model.entiity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    @Getter
    private String username;

    @Getter
    private String password;

    @Getter
    private Collection<GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return UserPrincipal.builder().username(user.getUsername()).password(user.getPassword()).authorities(authorities).build();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
