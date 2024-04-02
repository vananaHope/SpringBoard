package com.subproject.board.common.security;

import com.subproject.board.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {

    private final String userCode;
    private final String userId;
    private final String userEmail;
    private final String userNickname;
    private final String userPassword;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String userCode, String userId, String userEmail, String userNickname, String userPassword, Collection<? extends GrantedAuthority> authorities) {
        this.userCode = userCode;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserEntity user){

        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority().getValue()));

        return new UserPrincipal(
                user.getUserCode().toString(),
                user.getUserId(),
                user.getUserEmail(),
                user.getUserNickname(),
                user.getUserPassword(),
                authorities
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
