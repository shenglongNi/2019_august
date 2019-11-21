package com.nsl.august.web.controller;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.nsl.august.web.util.ResponseUtil;
import com.nsl.persistence.model.LitemallAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class AdminAuthController {

    private static Logger logger = LoggerFactory.getLogger(AdminAuthController.class);


    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public Object loginSubmit(HttpServletRequest request, HttpServletResponse response, @RequestBody String reqBody) {

        String username = JSONUtil.parseObj(reqBody).getStr("username");
        String password = JSONUtil.parseObj(reqBody).getStr("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject currentUser = SecurityUtils.getSubject();

        try {

            currentUser.login(token);


        } catch (UnknownAccountException ukae) {
            logger.error(ukae.getMessage(), ukae);
            return ResponseUtil.fail();
        } catch (LockedAccountException lace) {
            logger.error(lace.getMessage(), lace);
            return ResponseUtil.fail();
        } catch (AuthenticationException ae) {
            logger.error(ae.getMessage(), ae);
            return ResponseUtil.fail();
        }

        currentUser = SecurityUtils.getSubject();
        LitemallAdmin admin = (LitemallAdmin) currentUser.getPrincipal();

        Map<String, Object> adminInfo = Maps.newHashMap();
        adminInfo.put("nickname", admin.getUsername());
        adminInfo.put("avatar", admin.getAvatar());

        adminInfo.put("token", request.getSession().getId());
        return ResponseUtil.success(adminInfo);

    }

}
