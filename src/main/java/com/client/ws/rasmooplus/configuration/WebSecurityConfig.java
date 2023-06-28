package com.client.ws.rasmooplus.configuration;

import com.client.ws.rasmooplus.filter.AuthenticationFilter;
import com.client.ws.rasmooplus.repositoy.jpa.UserDetailsRepository;
import com.client.ws.rasmooplus.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private static final String[] AUTH_SWAGGER_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/v2/api-docs/**",
            "/swagger-resources/**"
    };

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
            web.ignoring()
                    .requestMatchers(AUTH_SWAGGER_LIST)
                    .requestMatchers(HttpMethod.GET, "/subscription-type")
                    .requestMatchers(HttpMethod.POST, "/user")
                    .requestMatchers(HttpMethod.POST, "/payment/process")
                    .requestMatchers(HttpMethod.POST, "/auth")
                    .requestMatchers( "/auth/recovery-code/*");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationFilter(tokenService, userDetailsRepository), UsernamePasswordAuthenticationFilter.class).build();

    }

}
