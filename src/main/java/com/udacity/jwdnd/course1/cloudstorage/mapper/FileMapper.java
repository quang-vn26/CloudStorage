package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE USERID = #{userid}")
    List<File> getAllFileByUser(Integer userid);

    @Select("SELECT * FROM FILES WHERE FILEID = #{fileid}")
    File getDetailFile(Integer fileid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE FILEID = #{fileid}")
    int deleteFile(Integer fileId);
}

