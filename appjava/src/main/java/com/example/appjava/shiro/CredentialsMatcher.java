package com.example.appjava.shiro;

import com.example.appjava.Dao.UserDao;
import com.example.appjava.domain.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/*密码匹配  realm会调用这个来进行密码匹配*/
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    private UserDao userDao;

    public CredentialsMatcher(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        User user = userDao.getUserByName(username);
        if (null == user) {
            throw new UnknownAccountException("用户不存在");
        }
        if (token.getCredentials() == null) {
            throw new IncorrectCredentialsException("密码不能为空");
        }
        String password = "";
        for (char c : (char[]) token.getCredentials()) {
            password += c;
        }
        if (user.getPassword() != password) {
            throw new IncorrectCredentialsException("密码错误");
        }
        return true;
    }
}
