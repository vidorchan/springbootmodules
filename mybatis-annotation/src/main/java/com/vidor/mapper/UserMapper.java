package com.vidor.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vidor.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface UserMapper {

    @Select("select * from user")
    @Results(id="Key1", value = {
            @Result(id = true, column = "user_id", property = "userId"),
//            @Result(column = "", property = "", one = @One(
//                select = "", fetchType = FetchType.EAGER
//            )),
            @Result(column = "user_id", property = "roles", many = @Many(
                select = "com.vidor.mapper.RoleMapper.getRoleListByUserId", fetchType = FetchType.LAZY
            )
            )
    })
    List<User> getUserList();

    @Select("select * from user where user_id=#{id}")
    @ResultMap("Key1")//@ResultMap(value={"Key1"})
    User getAUser(@Param("id") int id);

    @Insert("insert into user values(null,#{user.userName},#{user.sex},#{user.age},#{user.dob})")
    @Options(useGeneratedKeys = true, keyProperty = "user.userId")//这里永远返回1-成功/0-失败
    int addAUser(@Param("user") User user);

    @Delete("delete from user where user_id=#{id}")
    @Update("")
    void deleteUser(@Param("id") int id);

//    @Select("select * from user where ")
//    List<User> search(User user);

//    @Options(useCache = false, flushCache = Options.FlushCachePolicy.FALSE)
//    @Select("select * from di_topic LIMIT #{start,jdbcType=INTEGER}, #{length,jdbcType=INTEGER};")
//    List<User> selectTopics(@Param("start") int start, @Param("length") int length);
}
