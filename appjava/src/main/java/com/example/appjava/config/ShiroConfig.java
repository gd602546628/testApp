package com.example.appjava.config;

import com.example.appjava.Dao.UserDao;
import com.example.appjava.domain.User;
import com.example.appjava.filters.TokenFilter;
import com.example.appjava.shiro.CredentialsMatcher;
import com.example.appjava.shiro.UserRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean(name = "sessionDAO")
    public EnterpriseCacheSessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return enterpriseCacheSessionDAO;
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("JSESSIONID");
        simpleCookie.setMaxAge(180000);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    @Bean(name = "remeberMeCookie")
    public SimpleCookie remeberMeCookie() {
        SimpleCookie remeberMeCookie = new SimpleCookie("rememberMe");
        remeberMeCookie.setMaxAge(2592000);
        remeberMeCookie.setHttpOnly(true);
        return remeberMeCookie;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        return defaultWebSessionManager;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(remeberMeCookie());
        return rememberMeManager;
    }

    @Bean("credentialsMatcher")
    /*密码匹配类*/
    public CredentialsMatcher credentialsMatcher(UserDao userDao) {
        return new CredentialsMatcher(userDao);
    }

    @Bean(name = "userRealm")
    public UserRealm userRealm(UserDao userDao) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher(userDao));
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthenticationCacheName("authenticationCache");
        userRealm.setAuthorizationCacheName("authorizationCache");
        userRealm.setAuthorizationCachingEnabled(true);
        /*TODO  没有用到授权，如果用到，需要配置授权解析器*/
        return userRealm;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(UserDao userDao) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(userRealm(userDao));
        // securityManager.setCacheManager();
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(UserDao userDao) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager(userDao));

        shiroFilterFactoryBean.setLoginUrl("/public/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/public/unauth");
        shiroFilterFactoryBean.setSuccessUrl("/public/index");

        Map<String, Filter> filters = new HashMap<>();
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setLoginUrl("/public/login");
        formAuthenticationFilter.setUsernameParam("userName");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setSuccessUrl("/public/index");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        LogoutFilter logoutFilter = new LogoutFilter();
        TokenFilter tokenFilter = new TokenFilter(userDao);
        // logoutFilter.setRedirectUrl("/public/login");
        filters.put("user", new UserFilter());
        filters.put("login", formAuthenticationFilter);
        filters.put("logout", logoutFilter);
        filters.put("token", tokenFilter);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/public/login", "login");
        filterChainDefinitionMap.put("/public/api/login", "anon");
        filterChainDefinitionMap.put("/**/api/**", "token");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


}
