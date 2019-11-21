package com.nsl.auguest.core.service;

import java.util.Set;

public interface IAdminPermService {

    Set<String> qryPermByRoleIds(Integer[] roleIds);
}
