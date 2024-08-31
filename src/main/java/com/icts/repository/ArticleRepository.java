package com.icts.repository;

import com.icts.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
    public Article queryByCode(String code) {
        throw new RuntimeException();
    }

    public void updateByCode(String code, String title, String content) {

    }

    public void insert(String code, String title, String content) {

    }

}


