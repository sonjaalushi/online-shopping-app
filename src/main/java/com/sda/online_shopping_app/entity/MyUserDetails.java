package com.sda.online_shopping_app.entity;

import com.sda.online_shopping_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sda.online_shopping_app.entity.Enum.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepo.getByName(name);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();

            // Get roles as GrantedAuthority objects
            Collection<GrantedAuthority> authorities = getAuthorities(userEntity.getRole());

            return org.springframework.security.core.userdetails.User
                    .withUsername(userEntity.getName())
                    .password(userEntity.getPassword())
                    .authorities(authorities)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + name);
        }
    }

    private Collection<GrantedAuthority> getAuthorities(Role role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name())); // Assuming roles are prefixed with "ROLE_"
        return authorities;
    }
}