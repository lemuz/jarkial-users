package com.jarkial.users.model.dto.sgd;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class CustomUser extends User{

    @Getter @Setter
    private Long userId;

    @Getter @Setter
    private String user;

    @Getter @Setter
    private String fullName;

    @Getter @Setter
    private String agencia;

    @Getter @Setter
    private String subTipoAgencia;

    @Getter @Setter
    private String tipoAgencia;

    @Getter @Setter
    private String requiredChangePassword;

    @Getter @Setter
    private Long agenciaId;

    @Getter @Setter
    private Long subTipoAgenciaId;

    @Getter @Setter
    private Long tipoAgenciaId;
    
    @Getter @Setter
    private boolean state;

    @Getter @Setter
    private String token;

    public CustomUser(String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.user = username;
        this.state = true;
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = username;
        this.state = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}