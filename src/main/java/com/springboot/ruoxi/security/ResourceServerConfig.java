package com.springboot.ruoxi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
//资源服务器
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(HttpSecurity http) throws Exception {
      /*
      * api请求下的所有资源都需要授权
      * */
    http.authorizeRequests()
        .antMatchers("/api/**")
//        .permitAll()
//        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      // 如果关闭 stateless，则 accessToken 使用时的 session id 会被记录，后续请求不携带 accessToken 也可以正常响应
    resources.resourceId(AuthorizationServerConfig.RESOURCE_ID).stateless(true);
  }
}
