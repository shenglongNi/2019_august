package com.nsl.auguest.core.service;

import com.nsl.persistence.model.LitemallAdmin;

import java.util.List;

public interface IAdminUserService {

    List<LitemallAdmin> all();

    LitemallAdmin queryAdminUserById(Integer id);

    LitemallAdmin queryAdminUserByName(String adminName);
    
    void createAdminUser(LitemallAdmin litemallAdmin);



}
