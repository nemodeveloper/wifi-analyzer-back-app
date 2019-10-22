package ru.nemodev.wifi.analyzer.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.nemodev.wifi.analyzer.security.config.service.SecurityServiceConfig;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    public static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/api/v2/api-docs**",
            "/swagger-resources**",
            "/swagger-resources/**",
            "/configuration/ui**",
            "/configuration/security**",
            "/swagger-ui.html**",
            "/webjars/**",
            // h2-console
            "/h2-console**"
    };

    private final SecurityServiceConfig securityServiceConfig;

    public SecurityConfig(SecurityServiceConfig securityServiceConfig)
    {
        this.securityServiceConfig = securityServiceConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .anonymous().disable()
            .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/oauth/token/**").permitAll()
            .and().authorizeRequests().anyRequest().authenticated()
            .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityServiceConfig.userService())
            .passwordEncoder(securityServiceConfig.passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
