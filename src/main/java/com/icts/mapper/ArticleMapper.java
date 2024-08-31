package com.icts.mapper;

import com.icts.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;
@Mapper
public interface ArticleMapper {

    // 插入文章
    int insertArticle(Article article);

    // 删除文章
    int deleteArticleByCode(String code);

    // 根据 code 查询文章
    Article selectArticleByCode(String code);

    // 根据 code 更新文章
    int updateArticleByCode(Article article);
}