package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles();

    boolean deleteRole(Long roleid);

    Role getRole(Long roleid);

    Role addRole(Role role);

    void updateRole(Role role);
}