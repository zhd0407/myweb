package com.base.myweb.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/","/index","/resource/**","/userlogin","/ignore/**","/main").permitAll()
             .antMatchers("/level1/**").hasRole("VIP1")
            .antMatchers("/level2/**").hasRole("VIP2")
            .antMatchers("/level3/**").hasRole("VIP3")
            .anyRequest()        // 任何请求,登录后可以访问
            .authenticated()
        ;

        http.formLogin().loginPage("/userlogin").usernameParameter("user").passwordParameter("pwd").defaultSuccessUrl("/");
        http.logout().logoutSuccessUrl("/");
        http.rememberMe().rememberMeParameter("rb");
                /*.antMatchers("/user/**").hasRole("VIP1")*/
        /*.antMatchers("/question/**").hasRole("USER")
                .and()
                .formLogin().//..failureUrl("/login-error")
                .and()
                .exceptionHandling().accessDeniedPage("/401")*/

        http.headers().frameOptions().sameOrigin(); //解决iframe嵌入子页面时跨域问题
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("1234")).roles("VIP1","VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("1234")).roles("VIP2","VIP3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("1234")).roles("VIP3","VIP1");
               /* .and()
                .withUser("angle").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("god").password("123456").roles("VIP1", "VIP2", "VIP3")*/

        //auth.userDetailsService(userDetailsService);
    }

}
