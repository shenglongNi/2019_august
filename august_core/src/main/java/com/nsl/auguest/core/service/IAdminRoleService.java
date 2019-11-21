package com.nsl.auguest.core.service;

import com.nsl.persistence.model.LitemallRole;

import java.util.List;
import java.util.Set;

public interface IAdminRoleService {

    Set<String> qryRolesByRoleIds(Integer[] roleIds);

    LitemallRole qryRoleByRoleName(String name);

    List<LitemallRole> all();

    List<LitemallRole> qrySelective(String name);

    void updateRole(LitemallRole role);

    void deleteRole(LitemallRole role);

}
