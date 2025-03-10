package com.exe212.tutormind.config;

import com.exe212.tutormind.common.Common;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@Data
public class SecurityConfiguration {

    @Autowired
    private final JwtAuthFilter jwtAuthFilter;
    @Autowired
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests( auth ->
                        auth
                                .requestMatchers("/v3/**")
                                .permitAll()
                                .requestMatchers("/swagger-ui/**")
                                .permitAll()
                                .requestMatchers("/swagger-ui.html")
                                .permitAll()
                                .requestMatchers("api/auth/login")
                                .permitAll()
                                .requestMatchers("api/auth/register")
                                .permitAll()
                                .requestMatchers("/api/test/**")
                                .permitAll()
                                .requestMatchers("/api/public/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
