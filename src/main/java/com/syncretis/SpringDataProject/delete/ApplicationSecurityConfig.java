//package com.syncretis.SpringDataProject.auth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import static com.syncretis.SpringDataProject.auth.ApplicationUserPermission.*;
//import static com.syncretis.SpringDataProject.auth.ApplicationUserRole.*;
//
//@Configuration
//@EnableWebSecurity
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()// TODO: "Stavitskii Vladimir"  16.08.2021 => изменить
//                .authorizeRequests()
//                .antMatchers("/","index")
//                .permitAll()
//                .antMatchers("/api/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.GET,"/api/departments/**").hasAnyRole(ADMIN.name(),PERSON.name())
//                .antMatchers(HttpMethod.POST,"/api/departments/**").hasAuthority(ADMIN_WRITE.getPermission())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    @Bean
//    public UserDetailsService userDetailsServiceBean(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles(ADMIN.name())
//                .build();
//        UserDetails person = User.builder()
//                .username("person")
//                .password(passwordEncoder.encode("person"))
//                .roles(PERSON.name())
//                .build();
//        return new InMemoryUserDetailsManager(
//                admin,
//                person
//        );
//    }
//}
