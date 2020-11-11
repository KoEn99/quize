package com.koen.quize.config;

import com.koen.quize.security.JwtConfigurer;
import com.koen.quize.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .httpBasic().disable().
                csrf().disable().
                cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/registration").permitAll()
                .antMatchers("/admin/profile/update").hasRole("ADMIN")
                .antMatchers("/admin/subject/create", "/admin/group/create").hasRole("ADMIN")
                .antMatchers("/quiz/create",
                                        "/question/create",
                        "                /answer/create").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers("/teacher/bind/group",
                                        "/info/subject",
                                        "/info/groups").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers("/quiz/get/{id}").hasAnyRole("TEACHER", "STUDENT", "ADMIN")
                .antMatchers("/quiz/result").hasAnyRole("TEACHER", "STUDENT", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
