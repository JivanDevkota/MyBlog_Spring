package com.nsu.thmeleafproject.config;


import com.nsu.thmeleafproject.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth->auth
                       .requestMatchers("users/register/**","users/login/*","/home","/posts/**", "/resources/**", "/css/**" ,"/img/**").permitAll()
                       .requestMatchers("/users/**").hasRole("USER")
                       .anyRequest().authenticated())
               .formLogin(form->form
                       .loginPage("/login")
                       .defaultSuccessUrl("/login",true)
                       .permitAll())
               .logout(logout->logout
                       .logoutUrl("/logout")
                       .logoutSuccessUrl("/home")
                       .invalidateHttpSession(true)
                       .deleteCookies("JSESSIONID")
                       .permitAll())
               .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder builder= http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return builder.build();
    }
}
