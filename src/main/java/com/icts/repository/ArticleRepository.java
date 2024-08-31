package com.icts.repository;

import com.icts.mapper.ArticleMapper;
import com.icts.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

    @Mapper
    private ArticleMapper articleMapper;

    public Article queryByCode(String code) {
        return articleMapper.selectArticleByCode(code);
    }

    public void updateByCode(String code, String title, String content) {
        articleMapper.updateArticleByCode(buildArticle(code, title, content));

    }

    private Article buildArticle(String code, String title, String content) {
        Article article = new Article();
        article.setCode(code);
        article.setTitle(title);
        article.setContent(content);
        return article;
    }

    public void insert(String code, String title, String content) {
        articleMapper.insertArticle(buildArticle(code, title, content));

    }

}


