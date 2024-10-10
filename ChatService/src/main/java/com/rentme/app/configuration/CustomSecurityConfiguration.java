//package com.rentme.app.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class CustomSecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .authorizeHttpRequests(
//                        auth -> {
//                            auth
//                                    .requestMatchers("/error","/favicon.ico","/public")
//                                    .permitAll()
//                                    .anyRequest()
//                                    .authenticated();
//                        }
//                )
//                .formLogin(Customizer.withDefaults())
//                .build();
//
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User
//                        .builder()
//                        .username("rupesh")
//                        .password("{noop}rupesh")
//                        .authorities("ROLE_USER")
//                        .build(),
//                User
//                        .builder()
//                        .username("admin")
//                        .password("{noop}admin")
//                        .authorities("ROLE_ADMIN")
//                        .build()
//        );
//    }
//
//}
