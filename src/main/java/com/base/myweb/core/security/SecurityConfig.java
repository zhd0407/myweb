package com.base.myweb.core.security;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;*/

//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig // extends WebSecurityConfigurerAdapter
 {

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
      *//*  http
                .authorizeRequests()
                .antMatchers("/css/**", "/index","/images/**","/js/**","/layui/**","/mods/**","/plugins/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/question/**").hasRole("USER")
                .and()
                .formLogin().usernameParameter("email").passwordParameter("pass")//.loginPage("/login").failureUrl("/login-error")
                .and()
                .exceptionHandling().accessDeniedPage("/401");

        http.logout().logoutSuccessUrl("/");*//*
        http.headers().frameOptions().sameOrigin(); //解决iframe嵌入子页面时跨域问题
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      *//*  //auth.userDetailsService(userDetailsService);
        auth.inMemoryAuthentication().withUser("272754994@qq.com").password("1234").roles("VIP1")
                .and()
                .withUser("angle").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("god").password("123456").roles("VIP1", "VIP2", "VIP3");*//*
    }
*/

}
