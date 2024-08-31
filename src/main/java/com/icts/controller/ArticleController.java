package com.icts.controller;


import com.alibaba.fastjson2.JSON;
import com.icts.model.Article;
import com.icts.model.Response;
import com.icts.model.request.ArticleQueryRequest;
import com.icts.model.request.ArticleUpdateRequest;
import com.icts.repository.ArticleRepository;
import com.icts.utils.SecretUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public Response<Article> query(ArticleQueryRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getCode())) {
            log.error("param error {}", JSON.toJSONString(request));
            return Response.failed("param error");
        }
        Article article = articleRepository.queryByCode(request.getCode());
        if (Objects.isNull(article)) {
            return Response.failed("article not exist");
        }
        return Response.success(article);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Response<String> update(ArticleUpdateRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getCode()) || Objects.isNull(request.getTitle()) || Objects.isNull(request.getContent())) {
            log.error("param error {}", JSON.toJSONString(request));
            return Response.failed("param error");
        }
        if (!SecretUtils.check(request.getSecret())) {
            return Response.failed(String.format("wrong secret:%s", request.getSecret()));
        }
//        Article article = articleRepository.queryByCode(code);
//        if (Objects.isNull(article)) {
//            return Response.failed("article not exist");
//        }
//        articleRepository.updateByCode(code);
//        return Response.success();
        throw new RuntimeException();
    }
}
