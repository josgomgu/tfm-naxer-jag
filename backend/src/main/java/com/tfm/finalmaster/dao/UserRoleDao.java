package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.UserRole;

import java.util.List;

public interface UserRoleDao {

    List<UserRole> getUserRoles(Long userid);

    boolean deleteUserRole(Long userid,Long roleid);

    UserRole addRoleOption(UserRole userRole);

}