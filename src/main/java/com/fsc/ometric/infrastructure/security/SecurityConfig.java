package com.fsc.ometric.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final JwtConverter jwtConverter;

  public SecurityConfig(JwtConverter jwtConverter) {
    this.jwtConverter = jwtConverter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authz) ->
        authz.requestMatchers(HttpMethod.GET, "/api/hello").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/admin/**").hasRole(Roles.ADMIN.getRoleName())
            .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole(Roles.USER.getRoleName())
            .requestMatchers(HttpMethod.GET, "/api/admin-and-user/**").hasAnyRole(Roles.ADMIN.getRoleName(),Roles.USER.getRoleName())
            .anyRequest().authenticated());

    http.sessionManagement(sess -> sess.sessionCreationPolicy(
        SessionCreationPolicy.STATELESS));
    http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

    return http.build();
  }
}
