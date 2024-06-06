package com.sda.online_shopping_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return  httpSecurity
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.anyRequest().permitAll();
        })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails adminuser= User.builder()
                .username("admin")
                .password("$2a$12$B64C1UvMRisEMDnPWEE0ZOIQc77Xb3tDnZZ16rFDfg.SLV2CkIrj6")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(adminuser);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}