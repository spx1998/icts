package com.icts.controller;


import com.alibaba.fastjson2.JSON;
import com.icts.model.Article;
import com.icts.model.Response;
import com.icts.model.request.ArticleQueryRequest;
import com.icts.model.request.ArticleUpdateRequest;
import com.icts.repository.ArticleRepository;
import com.icts.service.HtmlService;
import com.icts.utils.SecretUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private HtmlService htmlService;

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public Response<Article> query(ArticleQueryRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getCode())) {
            log.error("param error {}", JSON.toJSONString(request));
            return Response.failed("param error");
        }
        Article article = articleRepository.queryByCode(request.getCode());
        // 提取子标题
        if (Objects.isNull(article)) {
            return Response.failed("article not exist");
        }
        List<String> subtitle = htmlService.extractTitleFromHtml(article.getContent());
        article.setSubtitle(subtitle);
        return Response.success(article);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Response<String> update(@RequestBody ArticleUpdateRequest request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getCode()) || StringUtils.isBlank(request.getTitle()) || StringUtils.isBlank(request.getContent())) {
            log.error("param error {}", JSON.toJSONString(request));
            return Response.failed("param error");
        }
//        if (!SecretUtils.check(request.getSecret())) {
//            return Response.failed(String.format("wrong secret:%s", request.getSecret()));
//        }
        Article article = articleRepository.queryByCode(request.getCode());
        if (Objects.isNull(article)) {
            log.warn("insert article:{}", request.getTitle());
            articleRepository.insert(request.getCode(), request.getTitle(), request.getContent());
            return Response.success("insert");
        } else {
            log.warn("update article:{}", request.getTitle());
            articleRepository.updateByCode(request.getCode(), request.getTitle(), request.getContent());
            return Response.success("update");
        }
    }
}
