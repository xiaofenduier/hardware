package com.sunshine.hardware.dao;

import com.sunshine.hardware.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);
}
