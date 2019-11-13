package com.springboot.ruoxi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private DataSource dataSource;
    //  @Resource private UserService userService;
    //  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    //  @Override
    //  public AuthenticationManager authenticationManagerBean() throws Exception {
    //    return super.authenticationManagerBean();
    //  }
    
    //  @Override
    //  protected UserDetailsService userDetailsService() {
    ////    return super.userDetailsService();
    //      InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //
    // manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
    //      return manager;
    //  }
    // password 方案一：明文存储，用于测试，不能用于生产
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    // password 方案二：用 BCrypt 对密码编码
    //    @Bean
    //    PasswordEncoder passwordEncoder(){
    //        return new BCryptPasswordEncoder();
    //    }
    
    // password 方案三：支持多种编码，通过密码的前缀区分编码方式,推荐
    //  @Bean
    //  PasswordEncoder passwordEncoder() {
    //    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //  }
    
    //  @Override
    //  protected void configure(HttpSecurity http) throws Exception {
    //    //    super.configure(http);
    //    http.authorizeRequests()
    //        .antMatchers("/oauth/*")
    //        .permitAll()
    //        .anyRequest()
    //        .authenticated()
    //        .and()
    //        .csrf()
    //        .disable();
    //  }
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
        //    AuthenticationManager authenticationManager = super.authenticationManagerBean();
        //    return authenticationManager;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**", "/login", "/users")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
    
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        //
        // userDetailsManager.createUser(User.withUsername("qq").password("789").authorities("USER").build());
        //    return userDetailsManager;
        //    获取系统中用户
        return username -> userDetailsManager.loadUserByUsername(username);
        //   return (UserDetailsService) username -> userService.findOne(Integer.parseInt(username));
    }
    
    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    ////      auth.jdbcAuthentication().getUserDetailsService().setDataSource(dataSource);
    ////      userDetailsService(userDetailsService())//配置自定义UserDetails
    ////              .and().jdbcAuthentication()
    //  //            .passwordEncoder(passwordEncoder())//启用密码加密功能
    ////              .dataSource(dataSource);
    //
    //    }
    
}
