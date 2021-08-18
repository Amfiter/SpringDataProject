package com.syncretis.SpringDataProject.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import static com.syncretis.SpringDataProject.auth.ApplicationUserPermission.*;
import static com.syncretis.SpringDataProject.auth.ApplicationUserRole.*;

@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final TokenStore tokenStore;

    public OAuth2ResourceServerConfig(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        return defaultTokenServices;
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/token/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/departments/**").hasAnyRole(USER.name(),ADMIN.name())
                .antMatchers(HttpMethod.GET,"/api/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/api/**").hasAuthority(ADMIN_WRITE.getPermission())
                .anyRequest().authenticated();
    }

}
