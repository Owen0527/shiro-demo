package com.jc.springboot.shirodemo.shiro;

import com.jc.springboot.shirodemo.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * MyShiroRealm
 *
 * @author OwenZhu
 * @date 18/7/25
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 设置权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        CachedUser cachedUser = (CachedUser) principalCollection.getPrimaryPrincipal();
        if(cachedUser.getUserId() == 1L) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("admin");
            info.addStringPermission("*");
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        log.info("MyShiroRealm.doGetAuthenticationInfo(), name={}, pw={}", token.getPrincipal(), token.getCredentials());
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        String credentials = String.copyValueOf(token.getPassword());

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        CachedUser cachedUser = new CachedUser();
        cachedUser.setLoginName(username);
        cachedUser.setUserId(2L);
        String salt = "111111";
        String password = PasswordUtils.simpleHashString(credentials, salt);
        log.info("credientials:{}, saltPw:{}", credentials, password);
        return new SimpleAuthenticationInfo(
            //用户缓存信息
            cachedUser,
            //密码
            password,
            //salt
            ByteSource.Util.bytes(salt),
            //realm name
            getName()
        );
        /*return new SimpleAuthenticationInfo(
            new SimplePrincipalCollection(cachedUser, username),
            credentials
        );*/
    }
}
