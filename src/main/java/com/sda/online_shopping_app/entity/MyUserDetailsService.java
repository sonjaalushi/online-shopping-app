package com.sda.online_shopping_app.entity;

import com.sda.online_shopping_app.entity.Enum.Role;
import com.sda.online_shopping_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.getByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + name));

        Collection<GrantedAuthority> authorities = getAuthorities(userEntity.getRole());

        return org.springframework.security.core.userdetails.User
                .withUsername(userEntity.getName())
                .password(userEntity.getPassword())
                .authorities(authorities)
                .build();
    }
    private Collection<GrantedAuthority> getAuthorities(Role role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }


}