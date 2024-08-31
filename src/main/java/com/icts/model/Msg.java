package com.icts.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Msg implements Serializable {
    private static final long serialVersionUID = -8531204228192289031L;
    private Integer id;
    private String code;
    private String content;
    private Boolean isDisplay; // 如果 tinyint 对应 Java 的 byte 类型，可根据实际情况调整
    private Date gmtCreate;
    private Date gmtModified;
}
