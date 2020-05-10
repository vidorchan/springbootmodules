package com.vidor.mapper;

import com.vidor.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    @Select("select * from role")
    List<Role> getRoleList();

    @Select("select * from role where role_id = #{id}")
    List<Role> getRoleListById(@Param("id") int id);


    @Select("select * from role where role_id in (select role_id from user_role_relation where user_id=#{uid})")
    List<Role> getRoleListByUserId(@Param("uid") int uid);
}
