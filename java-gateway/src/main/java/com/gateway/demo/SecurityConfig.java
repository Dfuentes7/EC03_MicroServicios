package com.gateway.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/Java1/api/**",
                                "/Java2/api/**",
                                "/Java3/api/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/Java1/api/**",
                                "/Java2/api/**",
                                "/Java3/api/**"
                        ).authenticated()

                        .requestMatchers(HttpMethod.PUT,
                                "/Java1/api/**",
                                "/Java2/api/**",
                                "/Java3/api/**"
                        ).authenticated()

                        .requestMatchers(HttpMethod.DELETE,
                                "/Java1/api/**",
                                "/Java2/api/**",
                                "/Java3/api/**"
                        ).authenticated()

                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }
}