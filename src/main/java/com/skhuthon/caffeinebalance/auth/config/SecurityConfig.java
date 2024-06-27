package com.skhuthon.caffeinebalance.auth.config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import com.skhuthon.caffeinebalance.auth.jwt.JWTFilter;
import com.skhuthon.caffeinebalance.auth.jwt.JWTUtil;
import com.skhuthon.caffeinebalance.auth.oauth2.CustomSuccessHandler;
import com.skhuthon.caffeinebalance.auth.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PERMITTED_URLS = {
            "/"
    };

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler,
                          JWTUtil jwtUtil) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //configureCors(http);
        configureHttpSecurity(http);
        configureJwtFilter(http);
        configureOAuth2Login(http);
        configureAuthorization(http);
        configureSessionManagement(http);
        return http.build();
    }

    private void configureHttpSecurity(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);
    }

    private void configureJwtFilter(HttpSecurity http) throws Exception {
        http.addFilterAfter(new JWTFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);
    }

    private void configureOAuth2Login(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService))
                .successHandler(customSuccessHandler)
        );
    }

    private void configureAuthorization(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            for (String url : PERMITTED_URLS) {
                auth.requestMatchers(antMatcher(url)).permitAll();
            }
            auth.anyRequest().authenticated();
        });
    }

    private void configureSessionManagement(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }
}
