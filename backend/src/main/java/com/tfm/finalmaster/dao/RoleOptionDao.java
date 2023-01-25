package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.RoleOption;

import java.util.List;

public interface RoleOptionDao {

    List<RoleOption> getRoleOptions(Long roleid);

    boolean deleteRoleOption(Long roleid,Long optionmenuid);

    RoleOption addRoleOption(RoleOption roleoption);

}