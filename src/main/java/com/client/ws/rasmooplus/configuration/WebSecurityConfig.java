package com.client.ws.rasmooplus.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //responsável pela configuração de autorizacao -> Acesso a URL's
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/subscription-type").permitAll()
                .antMatchers(HttpMethod.GET,"/subscription-type/*").permitAll().anyRequest().authenticated();

    }


    //responsável pela configuração de autenticação -> login e senha
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    //responsável pelo acesso à arquivos estáticos -> html, css, js, imagens
    @Override
    public void configure(WebSecurity web) throws Exception {
    }


}
