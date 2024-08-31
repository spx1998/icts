package com.icts.model;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 5542018985052668308L;

    private Integer id;
    private String code;
    private String title;
    private String content;
    private List<String> subtitle;
    private Date gmtCreate;
    private Date gmtModified;
}
