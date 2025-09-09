package com.example.WSM.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Setter
@Builder
@AllArgsConstructor
@Document(collection = "user")
public class User implements UserDetails {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }




    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true; // Compte non expiré
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Compte non bloqué
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Identifiants non expirés
    }

    @Override
    public boolean isEnabled() {
        return true; // Compte activé
    }

    @Override
    public String getUsername() {
        return "";
    }

    public static class Builder {
        private User user;

        public Builder() {
            this.user = new User();
        }

        public Builder id(String id) {
            user.setId(id);
            return this;
        }

        public Builder name(String name) {
            user.setName(name);
            return this;
        }

        public Builder email(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder password(String password) {
            user.setPassword(password);
            return this;
        }

        public User build() {
            return user;
        }
    }

    // Méthode statique pour obtenir le Builder
    public static Builder builder() {
        return new Builder();
    }
}

