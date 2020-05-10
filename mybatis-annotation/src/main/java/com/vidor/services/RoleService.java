package com.vidor.services;

import com.vidor.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoleList();
    Role getARole(int id);
    int addARole(Role role);
    void deleteARole(int id);
//    List<Role> search(Role Role);
}
