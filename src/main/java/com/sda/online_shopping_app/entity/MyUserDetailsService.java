package com.sda.online_shopping_app.entity;


import com.sda.online_shopping_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {


        Optional<UserEntity> user = userRepo.getByName(name);
        if (user.isPresent()) {
            var userBoj = user.get();
            return User.builder()
                    .username(userBoj.getName())
                    .password(userBoj.getPassword())
                    .roles(getRoles(userBoj))
                    .build();
        } else {
            throw new UsernameNotFoundException(name);
        }
    }

    private String[] getRoles(UserEntity user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

}