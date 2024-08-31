package com.icts.model;

import lombok.Data;

import java.util.Date;

@Data
public class File {
    private int id;
    private String fileName;
    private Date gmtCreate;
    private Date gmtModified;
}