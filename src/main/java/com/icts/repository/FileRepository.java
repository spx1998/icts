package com.icts.repository;

import com.icts.mapper.FileMapper;
import com.icts.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepository {
    @Autowired
    private FileMapper fileMapper;

    public List<File> query() {
        return fileMapper.selectAllFiles();
    }

    public void insert(String fileName) {
        File file = new File();
        file.setFileName(fileName);

        fileMapper.insertFile(file);
    }
}
