package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE USERID = #{userId}")
    List<File> getFilesByUserName(Integer userId);
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFile(Integer fileId);

    @Insert("INSERT INTO FILES (fileName, contentType, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Update("UPDATE FILES WHERE set fileName = #{fileName}, contentType = #{contentType}, fileSize = #{fileSize}, userId= #{userId}, fileDate = #{fileDate} WHERE fileId= #{fileId}")
    File updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{id}")
    void delete(Integer id);

    @Select("SELECT * FROM FILES WHERE fileName = #{fileNameToCheck}")
    File checkDuplicateFileName(String fileNameToCheck);
}
