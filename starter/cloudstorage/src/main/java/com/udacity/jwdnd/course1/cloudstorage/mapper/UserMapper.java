package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM  USERS WHERE username = #{username}")
    User getUserByUserName(String username);

    @Insert("INSERT INTO USERS (username, salt, password,lastname,firstname) VALUES(#{userName}, #{salt}, #{password}, #{lastName}, #{firstName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Update("UPDATE USERS WHERE set username= #{userName}, state = #{state}, password = #{password} , lastname= #{lastName} , firstname= #{firstname} WHERE userid= #{userId}")
    User updateUser(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{id}")
    void delete(Integer id);

    @Select("SELECT * FROM  USERS")
    User[] returnUsers();
}
