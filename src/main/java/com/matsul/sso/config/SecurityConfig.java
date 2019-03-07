package com.matsul.sso.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()//security configuration pre-fix
                .antMatchers("/login", "/oauth/authorize")//해당 url을 지정
                .and()
                .authorizeRequests()//제한적인 접근을 허용한다.(HttpServletRequest가 사용되는)
                .anyRequest().authenticated()//어떠한 요청이라도 인증되었다면
                .and()
                .formLogin().permitAll();//로그인폼은 모두 허용한다.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()//메모리에 기반한 인증을위함
                .withUser("john")
                .password(passwordEncoder().encode("123"))
                .roles("USER");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
