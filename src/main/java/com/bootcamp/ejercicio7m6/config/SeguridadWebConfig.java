package com.bootcamp.ejercicio7m6.config;

import com.bootcamp.ejercicio7m6.servicios.DetallesUsuarioServicio;
import com.bootcamp.ejercicio7m6.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer.*;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWebConfig {

    @Autowired
    DetallesUsuarioServicio userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login").permitAll()
                                .requestMatchers(antMatcher("/usuario**")).hasRole("ADMINISTRATIVO")
                                .requestMatchers(antMatcher("/capacitacion**")).hasRole("CLIENTE")
                                .requestMatchers(antMatcher("/contacto**")).hasRole("CLIENTE")
                                .requestMatchers(antMatcher("/visita**")).hasRole("PROFESIONAL")
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                //.formLogin(Customizer.withDefaults())

                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                                .logoutUrl("/logout")  // Ruta para realizar el logout
                                .logoutSuccessUrl("/login")  // Ruta a la que redirigir después del logout
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    SecurityFilterChain authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return http.build();
    }
}
