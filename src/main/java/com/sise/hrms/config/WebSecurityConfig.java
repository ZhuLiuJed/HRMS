package com.sise.hrms.config;

import com.sise.hrms.constant.AuthConstant;
import com.sise.hrms.po.User;
import com.sise.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by holyfrans on 2017/3/1.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                User user = userRepository.findOneByUsername(s);
                return user;
            }
        }).passwordEncoder(new Md5PasswordEncoder());
        //auth.inMemoryAuthentication().withUser("test").password("123456").authorities(AuthConstant.AUTH_DEPTM);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/personal-profile/**").hasAuthority(AuthConstant.AUTH_PPRO)
                .antMatchers("/dept-mana/**").hasAuthority(AuthConstant.AUTH_DEPTM)
                .antMatchers("/training/**").hasAuthority(AuthConstant.AUTH_EDTRA)
                .antMatchers("/personnel-deployment/**").hasAuthority(AuthConstant.AUTH_PDEP)
                .antMatchers("/sys-manager/**").hasAuthority(AuthConstant.AUTH_ADMIN)
                .antMatchers("/sys-manager/change-pwd").authenticated()
                .antMatchers("/index", "/main").authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .and().csrf().disable()
                .headers().frameOptions().sameOrigin();
    }
}
