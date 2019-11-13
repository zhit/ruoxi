package com.springboot.ruoxi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
// 授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    public static final String RESOURCE_ID = "api";
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Resource
    private DataSource dataSource;
    
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());
        
        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
        endpoints.tokenServices(tokenServices);
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // oauthServer.checkTokenAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll()");
        // 允许表单认证
        security.allowFormAuthenticationForClients();
    }
    
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
        //    clients
        //        .inMemory()
        //        .withClient("client_1")
        //        .secret("123456")
        //                    .authorizedGrantTypes("client_credentials", "password", "refresh_token")
        //        .scopes("app");
    }
    //  @Autowired private AuthenticationManager authenticationManager;
    //
    //  @Override
    //  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //    clients
    //        .inMemory()
    //        .withClient("client_1")
    //        .resourceIds(RESOURCE_ID)
    //        .authorizedGrantTypes("client_credentials", "password", "refresh_token")
    //        .scopes("select")
    //        .authorities("client")
    //        .secret("123456");
    //  }
    //
    //  @Override
    //  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    //    endpoints.authenticationManager(authenticationManager);
    //  }
    //
    //  @Override
    //  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    //    security.allowFormAuthenticationForClients();
    //    security.checkTokenAccess("permitAll()");
    //  }
}
