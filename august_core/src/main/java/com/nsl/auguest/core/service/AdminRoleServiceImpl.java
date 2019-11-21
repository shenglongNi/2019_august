package com.nsl.auguest.core.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.nsl.persistence.mapper.LitemallRoleMapper;
import com.nsl.persistence.model.LitemallRole;
import com.nsl.persistence.model.LitemallRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class
AdminRoleServiceImpl implements IAdminRoleService {

    @Autowired
    private LitemallRoleMapper roleMapper;

    @Override
    public Set<String> qryRolesByRoleIds(Integer[] roleIds) {

        LitemallRoleExample roleExample = new LitemallRoleExample();
        roleExample.or().andIdIn(Arrays.asList(roleIds));
        List<LitemallRole> litemallRoles = roleMapper.selectByExample(roleExample);
        Iterable<String> roleIdsIter = Iterables.transform(litemallRoles, LitemallRole::getName);

        return Sets.newHashSet(roleIdsIter);
    }

    @Override
    public LitemallRole qryRoleByRoleName(String name) {
        LitemallRoleExample example = new LitemallRoleExample();
        example.or().andNameEqualTo(name);
        List<LitemallRole> litemallRoles = roleMapper.selectByExample(example);
        if (litemallRoles == null) {
            return null;
        }
        return litemallRoles.get(0);
    }

    @Override
    public List<LitemallRole> all() {
        LitemallRoleExample example = new LitemallRoleExample();
        example.or().andDeletedEqualTo(false);

        return roleMapper.selectByExample(example);
    }

    @Override
    public List<LitemallRole> qrySelective(String name) {

        LitemallRoleExample example = new LitemallRoleExample();

        LitemallRoleExample.Criteria criteria = example.or();

        if (StrUtil.isNotBlank(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 3000)
    public void updateRole(LitemallRole role) {
        role.setUpdateTime(new Date());
        this.roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(LitemallRole role) {
        role.setUpdateTime(new Date());
        role.setDeleted(true);
        this.roleMapper.updateByPrimaryKeySelective(role);
    }
}
