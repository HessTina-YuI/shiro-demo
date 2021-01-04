package yui.hesstina.shirodemo.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;
import yui.hesstina.shirodemo.bean.StatelessToken;
import yui.hesstina.shirodemo.constant.UserInfo;
import yui.hesstina.shirodemo.pojo.User;

import java.util.Set;

/**
 * 自定义 realm
 *
 * @package yui.hesstina.shirodemo.config
 * @class CustomRealm
 * @author YuI
 * @create 2021/1/4 23:01 
 * @since
 **/
public class CustomRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();

        Set<String> roles = UserInfo.ROLES.get(userName);
        if (ObjectUtils.isEmpty(roles)) {
            return null;
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken token = (StatelessToken) authenticationToken;
        User user = UserInfo.USER_MAP.get(token.getToken());
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }

        token.setUserName(user.getName());

        return new SimpleAuthenticationInfo(user.getName(), token.getToken(), this.getName());
    }
}
