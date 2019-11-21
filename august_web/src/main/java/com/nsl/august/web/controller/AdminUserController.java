package com.nsl.august.web.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nsl.auguest.core.service.IAdminPermService;
import com.nsl.auguest.core.service.IAdminRoleService;
import com.nsl.auguest.core.service.IAdminUserService;
import com.nsl.august.web.util.ResponseUtil;
import com.nsl.persistence.model.LitemallAdmin;
import com.nsl.persistence.model.LitemallRole;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AdminUserController {

    private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);


    @Autowired
    private IAdminUserService adminUserService;

    @Autowired
    private IAdminRoleService roleService;

    @Autowired
    private IAdminPermService permService;

    @RequestMapping("/user/info")
    public Object getUserInfo() {
        LitemallAdmin currentUser = (LitemallAdmin) SecurityUtils.getSubject().getPrincipal();

        Map<String, Object> data = Maps.newHashMap();


        Map<String, Object> infoMap = Maps.newHashMap();
        infoMap.put("roles", roleService.qryRolesByRoleIds(currentUser.getRoleIds()));
        infoMap.put("perms", permService.qryPermByRoleIds(currentUser.getRoleIds()));
        infoMap.put("name", currentUser.getUsername());
        infoMap.put("avatar", currentUser.getAvatar());

        logger.info("登录用户信息：{}", infoMap);
        return ResponseUtil.success(infoMap);



    }


    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public Object getAdminRoleList(String name) throws InterruptedException {
        Thread.currentThread().sleep(1000);
        List<LitemallRole> roleList = roleService.qrySelective(name);

        return ResponseUtil.success(roleList);
    }

    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public Object updateRole(@RequestBody LitemallRole role) {
        logger.info("role:{}", role);
        roleService.updateRole(role);
        return ResponseUtil.success();
    }

    @RequestMapping("/role/delete")
    public Object deleteRole(@RequestBody LitemallRole role) {

        this.roleService.deleteRole(role);

        return ResponseUtil.success();
    }

}
