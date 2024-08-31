package com.icts.mapper;

import com.icts.model.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FileMapper {

    // 插入文件信息
    int insertFile(File file);

    // 根据 ID 删除文件信息
    int deleteFileById(int id);

    // 根据 ID 查询文件信息
    File selectFileById(int id);

    // 更新文件信息
    int updateFile(File file);

    // 查询所有文件信息
    List<File> selectAllFiles();
}