package org.wxshan.springboot.shiro.realm;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
public class StatelessRealm extends AuthorizingRealm{

    @Autowired
    //private UserService userService;

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Session session = SecurityUtils.getSubject().getSession();
        String role = (String) session.getAttribute("role");
        List<String> permissions = ((List<String>) session.getAttribute("permission"));
        authorizationInfo.addRole(role);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //User user = userService.findByUsername(token.getUsername());
        //if (user != null) {
            //return new SimpleAuthenticationInfo(user.getUsername(),
                    //user.getPwd(), getName());
        //}
        return null;
    }
}
