package com.nsl.auguest.core.service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.nsl.persistence.mapper.LitemallPermissionMapper;
import com.nsl.persistence.model.LitemallPermission;
import com.nsl.persistence.model.LitemallPermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class AdminPermServiceImpl implements IAdminPermService {

    @Autowired
    private LitemallPermissionMapper permissionMapper;


    @Override
    public Set<String> qryPermByRoleIds(Integer[] roleIds) {
        LitemallPermissionExample example = new LitemallPermissionExample();

        example.or().andRoleIdIn(Arrays.asList(roleIds));
        List<LitemallPermission> litemallPermissions = permissionMapper.selectByExample(example);

        Iterable<String> transform = Iterables.transform(litemallPermissions, LitemallPermission::getPermission);

        return Sets.newHashSet(transform);
    }
}
