package com.vidor.mapper;

import com.vidor.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> getRoleList();

    List<Role> getRoleListById(@Param("id") int id);


    List<Role> getRoleListByUserId(@Param("uid") int uid);
}
