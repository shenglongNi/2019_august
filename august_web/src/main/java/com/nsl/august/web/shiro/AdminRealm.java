package com.nsl.august.web.shiro;

import com.nsl.auguest.core.service.IAdminPermService;
import com.nsl.auguest.core.service.IAdminRoleService;
import com.nsl.auguest.core.service.IAdminUserService;
import com.nsl.persistence.model.LitemallAdmin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class AdminRealm extends AuthorizingRealm{

    private static Logger logger = LoggerFactory.getLogger(AdminRealm.class);

    @Autowired
    private IAdminUserService userService;

    @Autowired
    private IAdminRoleService roleService;

    @Autowired
    private IAdminPermService permService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("开始查询用户授权信息...");

        LitemallAdmin admin = (LitemallAdmin) getAvailablePrincipal(principalCollection);
        Set<String> roles = roleService.qryRolesByRoleIds(admin.getRoleIds());

        Set<String> perms = permService.qryPermByRoleIds(admin.getRoleIds());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(perms);

        logger.info("用户授权信息查询完成，【roles】:[{}] ; 【perms】: [{}]", roles, perms);

        // TODO: 2019/8/28 可以将此处的roles 和 perms 缓存起来，方便后面获取用户的roles 和perms，避免再次从数据库获取

        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("开始用户校验。。");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        LitemallAdmin litemallAdmin = userService.queryAdminUserByName(username);

        if (litemallAdmin == null) {
            throw new UnknownAccountException("用户[" + username + "]不存在！");
        }

        return new SimpleAuthenticationInfo(litemallAdmin, litemallAdmin.getPassword(), getName());
    }
}
