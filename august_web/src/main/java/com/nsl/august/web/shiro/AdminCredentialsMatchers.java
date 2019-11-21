package com.nsl.august.web.shiro;

import com.nsl.august.web.bcrypt.BCryptPasswordEncoder;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class AdminCredentialsMatchers implements CredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        String tokenCredentials = new String((char[]) authenticationToken.getCredentials());
        String infoCredentials = authenticationInfo.getCredentials().toString();
        return new BCryptPasswordEncoder().matches(tokenCredentials, infoCredentials);
    }
}
